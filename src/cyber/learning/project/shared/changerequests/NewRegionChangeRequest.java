package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.ComponentChangeRequest;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@SuppressWarnings("serial")
public final class NewRegionChangeRequest implements RegionChangeRequest,
                                                     Serializable
{
  public NewRegionChangeRequest() {}


  public NewRegionChangeRequest(
    PageDesc container,
    int regionType,
    String location,
    boolean isCanonical,
    Iterable<? extends ComponentChangeRequest> modifiedComponents)
  {
    container_ = container;
    location_ = location;
    type_ = regionType;
    isCanonical_ = isCanonical;
    modified_ = modifiedComponents;
  }


  @Override
  public int getRegionID()
  {
    return -1;
  }


  @Override
  public int getContainingPageID()
  {
    return container_.getID();
  }


  @Override
  public String getLocation()
  {
    return location_;
  }


  @Override
  public int getRegionType()
  {
    return type_;
  }


  @Override
  public boolean isCanonical()
  {
    return isCanonical_;
  }


  @Override
  public Iterable<? extends ComponentChangeRequest> getModifiedComponents()
  {
    return modified_;
  }


  @Override
  public RegionDesc getRegion()
  {
    return null;
  }


  @Override
  public PageDesc getContainingPage()
  {
    return container_;
  }


  private String location_;
  private int type_;
  private boolean isCanonical_;
  private Iterable<? extends ComponentChangeRequest> modified_;
  private PageDesc container_;
}