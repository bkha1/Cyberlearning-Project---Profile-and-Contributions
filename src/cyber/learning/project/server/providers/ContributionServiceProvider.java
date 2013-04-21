package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.getMultistagedDatabaseConnection;
import static cyber.learning.project.server.PersistenceManager.makeBatchParameterizedCommandFor;
import static cyber.learning.project.server.PersistenceManager.makeParameterizedCommandFor;
import static cyber.learning.project.server.PersistenceManager.searchFor;
import static cyber.learning.project.shared.CallbackPayload.make;
import static cyber.learning.project.shared.Enumerations.AcceptanceStatus.ACCEPTED;
import static cyber.learning.project.shared.Enumerations.AcceptanceStatus.PENDING;
import static cyber.learning.project.shared.Enumerations.AcceptanceStatus.REJECTED;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.ContributionService;
import cyber.learning.project.server.PersistenceManager.QueryTupleResultSink;
import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.Enumerations.AcceptanceStatus;
import cyber.learning.project.shared.changerequests.ContributionChangeRequest;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


@SuppressWarnings("serial")
public final class ContributionServiceProvider
  extends RemoteServiceServlet
  implements ContributionService
{
  @Override
  public CallbackPayload<ContributionDesc[]> getProposedContributionsFor(
    ComponentDesc target)
  {
    return getContributions(target, PENDING);
  }


  @Override
  public CallbackPayload<ContributionDesc[]> getHistoricalContributionsFor(
    ComponentDesc target, boolean getAccepted)
  {
    return getContributions(target,
                            getAccepted ? ACCEPTED : REJECTED);
  }


  @Override
  public CallbackPayload<ContributionDesc[]> updateContributionsAcceptance(
    ContributionChangeRequest[] changeRequests)
  {
    try
    {
      final Connection multiStaged = getMultistagedDatabaseConnection();
      final Collection<ContributionDesc> updated = new LinkedList<>();

      synchronized (multiStaged)
      {
        final SQLCommand command = makeBatchParameterizedCommandFor(multiStaged,
                                                                    UPDATE);
        multiStaged.setAutoCommit(false);
        for (ContributionChangeRequest changeRequest : changeRequests)
        {
          final int targetID = changeRequest.getContributionID();
          final int voteCount = changeRequest.getNewVoteCount();
          final int acceptance = changeRequest.getNewAcceptanceStatus();

          command.setInt(1, voteCount);
          command.setInt(2, acceptance);
          command.setInt(3, targetID);

          final ContributionDesc source = changeRequest.getContributionSource();
          updated.add(new ContributionDesc(targetID,
                                           source.getTargetedComponent(),
                                           source.getContributor(),
                                           source.getChangeComment(),
                                           source.getContributionTime(),
                                           voteCount,
                                           acceptance));

          command.tupleComplete();
        }
      }

      return make(updated.toArray(new ContributionDesc[updated.size()]));
    }
    catch (Throwable t)
    {
      return make(t, ContributionDesc[].class);
    }
  }


  private static
  CallbackPayload<ContributionDesc[]> getContributions(ComponentDesc target,
                                                       AcceptanceStatus status)
  {
    try
    {
      final SQLCommand command = makeParameterizedCommandFor(GET);
      command.setInt(1, target.getID());
      command.setInt(2, status.ordinal());

      final Collection<ContributionDesc> proposals = new LinkedList<>();
      searchFor(
        command, true,
        new QueryTupleResultSink()
        {
          @Override
          public void putTo(ResultSet tuple) throws SQLException
          {
            proposals.add(
              new ContributionDesc(
                tuple.getInt("contribID"),
                new ComponentDesc(tuple.getInt("proposedCompID"),
                                  tuple.getInt("compType"),
                                  tuple.getString("compValue")),
                new AccountDesc(tuple.getInt("accountID"),
                                tuple.getString("username")),
                tuple.getString("changeComment"),
                tuple.getDate("timestamp"),
                tuple.getInt("voteCount"),
                tuple.getInt("acceptanceStatus")));
          }
        });
      return make(proposals.toArray(new ContributionDesc[proposals.size()]));
    }
    catch (Throwable t)
    {
      return make(t, ContributionDesc[].class);
    }
  }


  public static final String GET =
    "SELECT \"tmp2\".\"contribID\" as \"contribID\", " +
           "\"tmp2\".\"changeComment\" as \"changeComment\", " +
           "\"tmp2\".\"timestamp\" as \"timestamp\", " +
           "\"tmp2\".\"voteCount\" as \"voteCount\", " +
           "\"tmp2\".\"acceptanceStatus\" as \"acceptanceStatus\", " +
           "\"tmp2\".\"proposedCompID\" as \"proposedCompID\", " +
           "\"tmp2\".\"compType\" as \"compType\", " +
           "\"tmp2\".\"compValue\" as \"compValue\", " +
           "\"accounts\".\"account_id\" as \"accountID\", " +
           "\"accounts\".\"username\" as \"username\" " +
    "FROM \"accounts\" " +
      "INNER JOIN " +
      "( " +
         "SELECT \"tmp1\".\"contribID\" as \"contribID\", " +
                "\"tmp1\".\"changeComment\" as \"changeComment\", " +
                "\"tmp1\".\"ts\" as \"timestamp\", " +
                "\"tmp1\".\"voteCount\" as \"voteCount\", " +
                "\"tmp1\".\"acceptanceStatus\" as \"acceptanceStatus\", " +
                "\"components\".\"comp_id\" as \"proposedCompID\", " +
                "\"components\".\"type\" as \"compType\", " +
                "\"components\".\"value\" as \"compValue\", " +
                "\"tmp1\".\"accountID\" as \"accountID\" " +
         "FROM \"components\" " +
         "INNER JOIN " +
         "(" +
           "SELECT \"contrib_id\" as \"contribID\", " +
                  "\"proposed_comp_id\" as \"proposedCompID\", " +
                  "\"account_id\" as \"accountID\", " +
                  "\"change_comment\" as \"changeComment\", " +
                  "\"timestamp\" as \"ts\", " +
                  "\"votes\" as \"voteCount\", " +
                  "\"acceptance_status\" as \"acceptanceStatus\" " +
           "FROM \"contributions\" " +
           "WHERE \"target_comp_id\"=?1 AND \"acceptance_status\"=?2 " +
         ") \"tmp1\" ON \"tmp1\".\"proposedCompID\" = \"components\".\"comp_id\" " +
      ") \"tmp2\" ON \"tmp2\".\"accountID\" = \"accounts\".\"account_id\"";
  private static final String UPDATE =
    "UPDATE \"contributions\" " +
    "SET \"votes\" = ?1, " +
        "\"acceptance_status\" = ?2 " +
    "WHERE \"contrib_id\" = ?3";
}