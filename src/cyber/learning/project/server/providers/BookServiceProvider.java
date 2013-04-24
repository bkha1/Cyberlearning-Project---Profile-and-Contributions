package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.*;
import static cyber.learning.project.server.providers.ModificationCommands.*;
import static cyber.learning.project.shared.Enumerations.AcceptanceStatus.*;

import java.sql.Date;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.changerequests.PageChangeRequest;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;



@SuppressWarnings("serial")
public final class BookServiceProvider extends RemoteServiceServlet
{
  @SuppressWarnings("unused")
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
      return request.getPageID() == -1 ?
             processNewPageRequest(request, context) :
             processUpdatedPageRequest(request, context);
    }
    else
    {
      /* Not going to happen because we can't create proposed pages. */
      return null;
    }
  }


  private static PageDesc processNewPageRequest(PageChangeRequest request,
                                                ConstructionContext context)
    throws SQLException
  {
    final SQLCommand command = context.getCommandFor(CREATE_PAGE);
    try
    {
      command.setInt(1, context.getContainingBook().getID());
      command.setBinary(2, false);

      final PageDesc newPage =
        new PageDesc(insertTupleWithAutoIncrementingID(command),
                     context.getContainingBook(),
                     false);

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


  private static PageDesc processUpdatedPageRequest(PageChangeRequest request,
                                                    ConstructionContext context)
    throws SQLException
  {
    final PageDesc alteredPage = request.getPage();
    for (RegionChangeRequest regionCR : request.getModifiedRegions())
    {
      context.addRegionDesc(processRegionRequest(regionCR,
                                                 alteredPage,
                                                 context));
    }

    return alteredPage;
  }


  private static RegionDesc processRegionRequest(RegionChangeRequest request,
                                                 PageDesc container,
                                                 ConstructionContext context)
    throws SQLException
  {
    /* Allow the creation of a contribution region even though we can't
     * visualize it. The rationale is that components in an absolute
     * layout scheme requires a region.
     */
    return request.getRegionID() == -1 ?
           processNewRegionRequest(request, container, context) :
           processUpdatedRegionRequest(request, container, context);
  }


  private static RegionDesc processNewRegionRequest(RegionChangeRequest request,
                                                    PageDesc container,
                                                    ConstructionContext context)
    throws SQLException
  {
    final SQLCommand command = context.getCommandFor(CREATE_REGION);
    try
    {
      final int regionType = request.getRegionID();
      final String location = request.getLocation();

      command.setInt(1, container.getID());
      command.setString(2, location);
      command.setInt(3, regionType);
      command.setBinary(4, false);

      final RegionDesc newRegion =
        new RegionDesc(insertTupleWithAutoIncrementingID(command),
                       container,
                       location,
                       regionType,
                       false);

      final ComponentDesc newComp =
        processComponentRequest(request.getModifiedComponent(),
                                newRegion,
                                context);

      /* The new component may not be canonical and thus will be null. */
      if (newComp != null)
      {
        context.addComponentDesc(newComp);
      }

      return newRegion.setComponent(newComp);
    }
    finally
    {
      command.close();
    }
  }


  private static
  RegionDesc processUpdatedRegionRequest(RegionChangeRequest request,
                                         PageDesc container,
                                         ConstructionContext context)
    throws SQLException
  {
    final ComponentDesc newComp =
      processComponentRequest(request.getModifiedComponent(),
                              request.getRegion(),
                              context);

    /* The new component may not be canonical and thus will be null. */
    if (newComp != null)
    {
      context.addComponentDesc(newComp);
    }

    return request.getRegion();
  }


  private static
  ComponentDesc processComponentRequest(ComponentChangeRequest request,
                                        RegionDesc container,
                                        ConstructionContext context)
    throws SQLException
  {
    return request.getComponentID() == -1 ?
           processNewComponentRequest(request, container, context) :
           processModifiedComponentRequest(request, container, context);
  }


  private static
  ComponentDesc processNewComponentRequest(ComponentChangeRequest request,
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


  private static
  ComponentDesc processModifiedComponentRequest(ComponentChangeRequest request,
                                                RegionDesc container,
                                                ConstructionContext context)
    throws SQLException
  {
    final ComponentDesc proposed = processNewComponentRequest(request,
                                                              container,
                                                              context);
    final SQLCommand command = context.getCommandFor(CREATE_COMPONENT);
    try
    {
      final AccountDesc contributor = context.getContributor();
      final String changeComment = context.getChangeCommand();
      final Date timestamp = context.getTimestamp();
      final int status = PENDING.ordinal();

      /* The change request is targeting an existing component. */
      command.setInt(1, request.getComponentID());
      command.setInt(2, proposed.getID());
      command.setInt(3, contributor.getID());
      command.setString(4, changeComment);
      command.setDate(5, timestamp);
      command.setInt(6, 0);
      command.setInt(7, status);

      context.addContributionDesc(
        new ContributionDesc(insertTupleWithAutoIncrementingID(command),
                             request.getComponent(),
                             proposed,
                             contributor,
                             changeComment,
                             timestamp,
                             0,
                             status));

      return null;
    }
    finally
    {
      command.close();
    }
  }
}