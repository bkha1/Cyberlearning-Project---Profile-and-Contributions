package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.*;
import static cyber.learning.project.shared.CallbackPayload.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.RegionService;
import cyber.learning.project.server.PersistenceManager.QueryTupleResultSink;
import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@SuppressWarnings("serial")
public final class RegionServiceProvider extends RemoteServiceServlet
                                         implements RegionService
{
  @Override
  public
  CallbackPayload<RegionDesc> createCanonical(RegionChangeRequest regionCR)
  {
    return create(regionCR, true);
  }


  @Override
  public
  CallbackPayload<RegionDesc> createProposed(RegionChangeRequest regionCR)
  {
    return create(regionCR, false);
  }


  @Override
  public
  CallbackPayload<RegionDesc[]> updateRegions(RegionChangeRequest[] updates)
  {
    return null;
  }


  @Override
  public
  CallbackPayload<RegionDesc[]> getCanonicalRegionsForBook(PageDesc container)
  {
    return get(container, true);
  }


  @Override
  public
  CallbackPayload<RegionDesc[]> getProposedRegionsForBook(PageDesc container)
  {
    return get(container, true);
  }


  private static
  CallbackPayload<RegionDesc> create(RegionChangeRequest regionCR,
                                     boolean isCanonical)
  {
//    try
//    {
//      final SQLCommand rCommand =
//        makeParameterizedCommandFor(isCanonical ?
//                                    CREATE_CANONICAL : CREATE_PROPOSED);
//      final BookDesc bDesc = regionCR.getContainingBook();
//      final String location = regionCR.getLocation();
//      final int type = regionCR.getType();
//      rCommand.setInt(1, bDesc.getID());
//      rCommand.setString(2, location);
//      rCommand.setInt(3, type);
//      final int regionID = insertTupleWithAutoIncrementingID(rCommand);
//
//      final SQLCommand cCommand = makeParameterizedCommandFor(CREATE_COMPONENT);
//      final int compType = regionCR.getComponentChangeRequest().getType();
//      final String compValue = regionCR.getComponentChangeRequest().getValue();
//      cCommand.setInt(1, regionID);
//      cCommand.setInt(2, compType);
//      cCommand.setString(3, compValue);
//
//      final int compID = insertTupleWithAutoIncrementingID(cCommand);
//      final ComponentDesc compDesc = new ComponentDesc(compID,
//                                                       compType,
//                                                       compValue);
//      final RegionDesc regionDesc = new RegionDesc(regionID,
//                                                   bDesc,
//                                                   compDesc,
//                                                   location,
//                                                   type);
//      compDesc.setRegionDesc(regionDesc);
//
//      return make(regionDesc);
//    }
//    catch (Throwable t)
//    {
//      return make(t, RegionDesc.class);
//    }
    return null;
  }


  private static CallbackPayload<RegionDesc[]> get(final PageDesc container,
                                                   boolean isCanonical)
  {
    try
    {
      final SQLCommand command = makeParameterizedCommandFor(GET_REGIONS);
      command.setInt(1, container.getID());
      command.setBinary(2, isCanonical);

      final Collection<RegionDesc> regions = new LinkedList<>();
      searchFor(
        command, true,
        new QueryTupleResultSink()
        {
          @Override
          public void putTo(ResultSet tuple) throws SQLException
          {
            final ComponentDesc cDesc =
              new ComponentDesc(tuple.getInt("cid"),
                                tuple.getInt("c_type"),
                                tuple.getString("c_value"));
            final RegionDesc rDesc =
              new RegionDesc(tuple.getInt("rid"),
                             container,
                             tuple.getString("loc"),
                             tuple.getInt("r_type"),
                             true);
            cDesc.setRegionDesc(rDesc);
            regions.add(rDesc);
          }
        });
      return make(regions.toArray(new RegionDesc[regions.size()]));
    }
    catch (Throwable t)
    {
      return make(t, RegionDesc[].class);
    }
  }


  private static final String CREATE_CANONICAL =
    "INSERT INTO \"regions\"" +
    "(" +
      "\"book_id\", " +
      "\"location\", " +
      "\"type\", " +
      "\"isCanonical\"" +
    ")" +
    "VALUES (?1,?2,?3,1)";
  private static final String CREATE_PROPOSED =
    "INSERT INTO \"regions\"" +
    "(" +
      "\"book_id\", " +
      "\"location\", " +
      "\"type\", " +
      "\"isCanonical\"" +
    ")" +
    "VALUES (?1,?2,?3,0)";
  private static final String CREATE_COMPONENT =
    "INSERT INTO \"components\" (\"region_id\", \"type\", \"value\") " +
    "VALUES (?1,?2,?3)";
  private static final String GET_REGIONS =
    "SELECT \"tmp1\".*, " +
           "\"comp_id\" as \"cid\", " +
           "\"components\".\"type\" as \"c_type\", " +
           "\"components\".\"value\" as \"c_value\" " +
    "FROM \"components\" " +
      "INNER JOIN " +
      "(" +
        "SELECT \"region_id\" as \"rid\", " +
               "\"book_id\" as \"bid\", " +
               "\"location\" as \"loc\", " +
               "\"type\" as \"r_type\" " +
        "FROM \"regions\" " +
              "WHERE \"book_id\"=?1 AND \"isCanonical\"=1 " +
      ") \"tmp1\" ON \"tmp1\".\"rid\"=\"components\".\"region_id\" ";
}