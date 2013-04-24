package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.*;
import static cyber.learning.project.shared.CallbackPayload.*;
import static cyber.learning.project.shared.Enumerations.AcceptanceStatus.*;

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
          final int voteCount = changeRequest.getVotes();
          final int acceptance = changeRequest.getAcceptanceStatus();

          command.setInt(1, voteCount);
          command.setInt(2, acceptance);
          command.setInt(3, targetID);

          final ContributionDesc source = changeRequest.getContribution();
          updated.add(new ContributionDesc(targetID,
                                           source.getTargetedComponent(),
                                           source.getProposedComponent(),
                                           source.getContributor(),
                                           source.getChangeComment(),
                                           source.getContributionTime(),
                                           voteCount,
                                           acceptance));

          command.tupleComplete();
        }
        command.executeBatch();
        multiStaged.setAutoCommit(true);
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
                tuple.getInt("contrib_id"),
                new ComponentDesc(tuple.getInt("target_comp_id"),
                                  tuple.getInt("target_type"),
                                  tuple.getString("target_value")),
                new ComponentDesc(tuple.getInt("proposed_comp_id"),
                                  tuple.getInt("proposed_type"),
                                  tuple.getString("proposed_value")),
                new AccountDesc(tuple.getInt("account_id"),
                                tuple.getString("username")),
                tuple.getString("change_comment"),
                tuple.getDate("timestamp"),
                tuple.getInt("votes"),
                tuple.getInt("acceptance_status")));
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
    "SELECT \"tmp3\".\"contrib_id\" as \"contrib_id\", " +
           "\"tmp3\".\"change_comment\" as \"change_comment\", " +
           "\"tmp3\".\"timestamp\" as \"timestamp\", " +
           "\"tmp3\".\"votes\" as \"votes\", " +
           "\"tmp3\".\"acceptance_status\" as \"acceptance_status\", " +
           "\"tmp3\".\"target_comp_id\" as \"target_comp_id\", " +
           "\"tmp3\".\"target_type\" as \"target_type\", " +
           "\"tmp3\".\"target_value\" as \"target_value\", " +
           "\"tmp3\".\"proposed_comp_id\" as \"proposed_comp_id\", " +
           "\"tmp3\".\"proposed_type\" as \"proposed_type\", " +
           "\"tmp3\".\"proposed_value\" as \"proposed_value\", " +
           "\"accounts\".\"account_id\" as \"account_id\", " +
           "\"accounts\".\"username\" as \"username\" " +
    "FROM \"accounts\" " +
      "INNER JOIN " +
      "(" +
        "SELECT \"tmp2\".\"contrib_id\" as \"contrib_id\", " +
               "\"tmp2\".\"change_comment\" as \"change_comment\", " +
               "\"tmp2\".\"timestamp\" as \"timestamp\", " +
               "\"tmp2\".\"votes\" as \"votes\", " +
               "\"tmp2\".\"acceptance_status\" as \"acceptance_status\", " +
               "\"tmp2\".\"account_id\" as \"account_id\", " +
               "\"tmp2\".\"target_comp_id\" as \"target_comp_id\", " +
               "\"tmp2\".\"target_type\" as \"target_type\", " +
               "\"tmp2\".\"target_value\" as \"target_value\", " +
               "\"components\".\"comp_id\" as \"proposed_comp_id\", " +
               "\"components\".\"type\" as \"proposed_type\", " +
               "\"components\".\"value\" as \"proposed_value\" " +
        "FROM \"components\" " +
          "INNER JOIN " +
          "(" +
            "SELECT \"tmp1\".\"contrib_id\" as \"contrib_id\", " +
                   "\"tmp1\".\"change_comment\" as \"change_comment\", " +
                   "\"tmp1\".\"timestamp\" as \"timestamp\", " +
                   "\"tmp1\".\"votes\" as \"votes\", " +
                   "\"tmp1\".\"acceptance_status\" as \"acceptance_status\", " +
                   "\"tmp1\".\"account_id\" as \"account_id\", " +
                   "\"tmp1\".\"proposed_comp_id\" as \"proposed_comp_id\", " +
                   "\"components\".\"comp_id\" as \"target_comp_id\", " +
                   "\"components\".\"type\" as \"target_type\", " +
                   "\"components\".\"value\" as \"target_value\" " +
            "FROM \"components\" " +
            "INNER JOIN " +
            "(" +
              "SELECT * " +
              "FROM \"contributions\" " +
              "WHERE target_comp_id=?1 AND acceptance_status=?2 " +
            ") \"tmp1\" ON \"tmp1\".\"target_comp_id\"=\"components\".\"comp_id\" " +
          ") \"tmp2\" ON \"tmp2\".\"proposed_comp_id\"=\"components\".\"comp_id\" " +
      ") \"tmp3\" ON \"tmp3\".\"account_id\"=\"accounts\".\"account_id\"";

  private static final String UPDATE =
    "UPDATE \"contributions\" " +
    "SET \"votes\" = ?1, " +
        "\"acceptance_status\" = ?2 " +
    "WHERE \"contrib_id\" = ?3";
}