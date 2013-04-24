package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.*;
import static cyber.learning.project.server.providers.ModificationCommands.*;

import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.changerequests.PageChangeRequest;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.ComponentChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;



@SuppressWarnings("serial")
public final class BookServiceProvider extends RemoteServiceServlet
{
  private static void persist(BookChangeRequest update)
    throws SQLException
  {
    final ConstructionContext ctx = new ConstructionContext(update);

    try
    {
      for (PageChangeRequest pageCR : update.getModifiedPages())
      {
        ctx.addPageDesc(processPageRequest(pageCR, ctx));
      }
    }
    catch (SQLException ex)
    {
      ctx.rollbackAllChanges();
    }
  }


  private static PageDesc processPageRequest(PageChangeRequest request,
                                             ConstructionContext context)
    throws SQLException
  {
    boolean isContribution = context.isContribution();
    if (!isContribution)
    {
      final SQLCommand command = context.getCommandFor(CREATE_PAGE);
      try
      {
        command.setInt(1, context.getContainingBook().getID());
        command.setBinary(2, !isContribution);

        final PageDesc newPage =
          new PageDesc(insertTupleWithAutoIncrementingID(command),
                       context.getContainingBook(),
                       !isContribution);

        for (RegionChangeRequest regionCR : request.getModifiedRegions())
        {
          context.addRegionDesc(processRegionRequest(regionCR,
                                                     newPage,
                                                     context));
        }

        return newPage;
      }
      finally
      {
        command.close();
      }
    }
    else
    {
      /* Not going to happen because we can't create proposed pages. */
      return null;
    }
  }


  private static RegionDesc processRegionRequest(RegionChangeRequest request,
                                                 PageDesc container,
                                                 ConstructionContext context)
    throws SQLException
  {
    boolean isContribution = context.isContribution();
    if (!isContribution)
    {
      final SQLCommand command = context.getCommandFor(CREATE_REGION);
      try
      {
        final int regionType = request.getRegionID();
        final String location = request.getLocation();

        command.setInt(1, container.getID());
        command.setString(2, location);
        command.setInt(3, regionType);
        command.setBinary(4, !isContribution);

        final RegionDesc newRegion =
          new RegionDesc(insertTupleWithAutoIncrementingID(command),
                         container,
                         location,
                         regionType,
                         !isContribution);

        final ComponentDesc newComp =
          processComponentRequest(request.getModifiedComponent(),
                                  newRegion,
                                  context);
        context.addComponentDesc(newComp);
        return newRegion.setComponent(newComp);
      }
      finally
      {
        command.close();
      }
    }
    else
    {
      /* Not going to happen because we can't create propose new regions. */
      return null;
    }
  }


  private static
  ComponentDesc processComponentRequest(ComponentChangeRequest request,
                                         RegionDesc container,
                                         ConstructionContext context)
    throws SQLException
  {
    final SQLCommand command = context.getCommandFor(CREATE_COMPONENT);
    try
    {
      final int componentType = request.getComponentType();
      final String value = request.getValue();

      command.setInt(1, container.getID());
      command.setInt(2, componentType);
      command.setString(3, value);

      final ComponentDesc newComp =
        new ComponentDesc(insertTupleWithAutoIncrementingID(command),
                          componentType,
                          value);
      return newComp;
    }
    finally
    {
      command.close();
    }
  }
}