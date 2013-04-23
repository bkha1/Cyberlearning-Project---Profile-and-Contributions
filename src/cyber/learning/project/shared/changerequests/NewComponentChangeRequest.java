package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.ComponentChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@SuppressWarnings("serial")
public final class NewComponentChangeRequest implements ComponentChangeRequest,
                                                        Serializable
{
  public NewComponentChangeRequest() {}


  public NewComponentChangeRequest(int componentType,
                                   String componentValue)
  {
    type_ = componentType;
    value_ = componentValue;
  }


  @Override
  public int getComponentID()
  {
    return -1;
  }


  @Override
  public int getContainingRegionID()
  {
    return -1;
  }


  @Override
  public int getComponentType()
  {
    return type_;
  }


  @Override
  public String getValue()
  {
    return value_;
  }


  @Override
  public ComponentDesc getComponent()
  {
    return null;
  }


  @Override
  public RegionDesc getContainingRegion()
  {
    return null;
  }


  private int type_;
  private String value_;
}