package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@SuppressWarnings("serial")
public final class UpdateRegionChangeRequest implements RegionChangeRequest,
                                                        Serializable
{
  public UpdateRegionChangeRequest() {}


  public UpdateRegionChangeRequest(RegionDesc target,
                                   String newLocation,
                                   ComponentChangeRequest componentRequest)
  {
    target_ = target;
    newLocation_ = newLocation;
    componentRequest_ = componentRequest;
  }


  @Override
  public int getRegionID()
  {
    return target_.getID();
  }


  @Override
  public int getContainingPageID()
  {
    return target_.getContainingPage().getID();
  }


  @Override
  public String getLocation()
  {
    return newLocation_;
  }


  @Override
  public int getRegionType()
  {
    return target_.getRegionType().ordinal();
  }


  @Override
  public boolean isCanonical()
  {
    return target_.isCanonical();
  }


  @Override
  public ComponentChangeRequest getModifiedComponent()
  {
    return componentRequest_;
  }


  @Override
  public RegionDesc getRegion()
  {
    return target_;
  }


  @Override
  public PageDesc getContainingPage()
  {
    return target_.getContainingPage();
  }

  private RegionDesc target_;
  private String newLocation_;
  private ComponentChangeRequest componentRequest_;
}