package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;
import java.util.LinkedList;

import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;


final class ConstructionContext
{
  public ConstructionContext(BookChangeRequest bookChangeRequest)
    throws SQLException
  {
    containingBook_ = bookChangeRequest.getContaingBook();
    isContribution_ = bookChangeRequest.isContributorTheOwner();

    multiStaged_ = getMultistagedDatabaseConnection();
    multiStaged_.setAutoCommit(false);
    initial_ = multiStaged_.setSavepoint("initial");

    pages_ = new LinkedList<>();
    regions_ = new LinkedList<>();
    components_ = new LinkedList<>();
  }


  public void addComponentDesc(ComponentDesc compDesc)
  {
    components_.add(compDesc);
  }


  public void addPageDesc(PageDesc pageDesc)
  {
    pages_.add(pageDesc);
  }


  public void addRegionDesc(RegionDesc regionDesc)
  {
    regions_.add(regionDesc);
  }


  public SQLCommand getCommandFor(String command) throws SQLException
  {
    return makeBatchParameterizedCommandFor(multiStaged_, command);
  }


  public BookDesc getContainingBook()
  {
    return containingBook_;
  }


  public boolean isContribution()
  {
    return isContribution_;
  }


  public void rollbackAllChanges() throws SQLException
  {
    multiStaged_.rollback(initial_);
    multiStaged_.commit();
    multiStaged_.setAutoCommit(true);
  }

  private final Connection multiStaged_;
  private final Savepoint initial_;
  private final BookDesc containingBook_;
  private final boolean isContribution_;
  private final Collection<PageDesc> pages_;
  private final Collection<RegionDesc> regions_;
  private final Collection<ComponentDesc> components_;
}