package cyber.learning.project.shared.descs;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.ComponentType;


@SuppressWarnings("serial")
public final class ComponentDesc extends BaseDesc
                                 implements Serializable
{
  public ComponentDesc() {}


  public ComponentDesc(int id,
                       int type,
                       RegionDesc container)
  {
    super(id);
    type_ = type;
    container_ = container;
  }


  public ComponentType getType()
  {
    return ComponentType.toEnum(type_);
  }


  public String getContentValue()
  {
    return value_;
  }


  public RegionDesc getContainingRegion()
  {
    return container_;
  }


  private int type_;
  private String value_;
  private RegionDesc container_;
}