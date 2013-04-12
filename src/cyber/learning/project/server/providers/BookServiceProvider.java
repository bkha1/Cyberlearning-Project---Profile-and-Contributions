package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.insertTupleWithAutoIncrementingID;
import static cyber.learning.project.server.PersistenceManager.makeParameterizedCommandFor;

import java.sql.Date;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.BookService;
import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ProposedContributions;
import cyber.learning.project.shared.descs.RegionDesc;



@SuppressWarnings("serial")
public final class BookServiceProvider
  extends RemoteServiceServlet
  implements BookService
{
  @Override
  public CallbackPayload<BookDesc> create()
  {
    return null;
  }


  @Override
  public CallbackPayload<RegionDesc[]> getAllRegions()
  {
    return null;
  }


  @Override
  public CallbackPayload<Boolean> contribute(BookChangeRequest[] changeRequests)
  {
    for (BookChangeRequest cr : changeRequests)
    {
      if (cr.getComponentID() == -1)
      {
        try
        {
          final int newRegion =
            insertTupleWithAutoIncrementingID(
              makeInsertRegion(cr.getContainingBookID(),
                               cr.getLocation(),
                               cr.getRegionType(),
                               false));
        }
        catch (Throwable t)
        {

        }
      }
      else
      {

      }
    }

    try
    {

      return new CallbackPayload<>(true);
    }
    catch (Throwable error)
    {
      return new CallbackPayload<>(error);
    }
  }


  @Override
  public
  CallbackPayload<ProposedContributions> getProposedContributions(BookDesc book)
  {
    return null;
  }


  private static SQLCommand makeInsertRegion(int containingBookID,
                                             String location,
                                             int regionType,
                                             boolean isCanonical)
    throws SQLException
  {
    final SQLCommand command =
      makeParameterizedCommandFor(CREATE_REGION_COMMAND);
    command.setInt(1, containingBookID);
    command.setString(2, location);
    command.setInt(3, regionType);
    command.setBinary(4, isCanonical);
    return command;
  }


  private static SQLCommand makeInsertContent(int containingRegionID,
                                              int contentType,
                                              String contentValue)
    throws SQLException
  {
    final SQLCommand command =
      makeParameterizedCommandFor(CREATE_CONTENT_COMMAND);
    command.setInt(1, containingRegionID);
    command.setInt(2, contentType);
    command.setString(3, contentValue);
    return command;
  }


  private static SQLCommand makeInsertContribution(int componentID,
                                                   int contributorID,
                                                   String changeComment,
                                                   int acceptanceStatus)
    throws SQLException
  {
    final SQLCommand command =
      makeParameterizedCommandFor(CREATE_CONTRIBUTION_COMMAND);
    command.setInt(1, componentID);
    command.setInt(2, contributorID);
    command.setString(3, changeComment);
    command.setDate(4, new Date(System.currentTimeMillis()));
    command.setInt(5, acceptanceStatus);
    return command;
  }


  private static SQLCommand makeUpdateRegionCommand(String updatedLocation,
                                                    boolean updatedCanonical,
                                                    int targetedRegionID)
    throws SQLException
  {
    final SQLCommand command =
      makeParameterizedCommandFor(UPDATE_REGION_COMMAND);
    command.setString(1, updatedLocation);
    command.setBinary(2, updatedCanonical);
    command.setInt(3, targetedRegionID);
    return command;
  }


  private static SQLCommand makeUpdateContent(int updatedContentValue,
                                              int componentID)
    throws SQLException
  {
    final SQLCommand command =
      makeParameterizedCommandFor(UPDATE_CONTENT_COMMAND);
    command.setInt(1, updatedContentValue);
    command.setInt(2, componentID);
    return command;
  }



  private static String CREATE_REGION_COMMAND =
    "INSERT INTO \"regions\" (\"book_id\"," +
                             "\"location\"," +
                             "\"type\"," +
                             "\"isCanonical\") " +
    "VALUES (?1,?2,?3)";
  private static String CREATE_CONTENT_COMMAND =
    "INSERT INTO \"components\" (\"region_id\",\"type\",\"value\") " +
    "VALUES (?1,?2,?3)";
  private static String CREATE_CONTRIBUTION_COMMAND =
    "INSERT INTO \"contributions\" (\"comp_id\"," +
                                   "\"account_id\"," +
                                   "\"change_comment\"," +
                                   "\"timestamp\"," +
                                   "\"acceptance_status\") " +
    "VALUES (?1,?2,?3,?4,?5)";
  private static String UPDATE_REGION_COMMAND =
    "UPDATE \"regions\" " +
    "SET \"location\" = ?1, \"isCanonical\" = ?2 " +
    "WHERE \"region_id\" = ?3";
  private static String UPDATE_CONTENT_COMMAND =
    "UPDATE \"components\" " +
    "SET \"value\" = ?1 " +
    "WHERE \"comp_id\" = ?2";
}