package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@SuppressWarnings("serial")
public final class UpdateComponentChangeRequest
  implements ComponentChangeRequest,
             Serializable
{
  public UpdateComponentChangeRequest() {}


  public UpdateComponentChangeRequest(ComponentDesc target,
                                      String newValue)
  {
    target_ = target;
    newValue_ = newValue;
  }



  @Override
  public int getComponentID()
  {
    return target_.getID();
  }


  @Override
  public int getContainingRegionID()
  {
    return target_.getContainingRegion().getID();
  }


  @Override
  public int getComponentType()
  {
    return target_.getComponentType().ordinal();
  }


  @Override
  public String getValue()
  {
    return newValue_;
  }


  @Override
  public ComponentDesc getComponent()
  {
    return target_;
  }


  @Override
  public RegionDesc getContainingRegion()
  {
    return target_.getContainingRegion();
  }


  private ComponentDesc target_;
  private String newValue_;
}