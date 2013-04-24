package cyber.learning.project.shared.descs;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.RegionType;



@SuppressWarnings("serial")
public final class RegionDesc extends BaseDesc
                              implements Serializable
{
  public RegionDesc() {}


  public RegionDesc(int id,
                    PageDesc container,
                    String location,
                    int type,
                    boolean isCanonical)
  {
    super(id);
    container_ = container;
    location_ = location;
    type_ = type;
    isCanonical_ = isCanonical;
  }


  public RegionDesc setComponent(ComponentDesc content)
  {
    content_ = content;
    return this;
  }


  public PageDesc getContainingBook()
  {
    return container_;
  }


  public ComponentDesc getContent()
  {
    return content_;
  }


  public String getLocation()
  {
    return location_;
  }


  public RegionType getRegionType()
  {
    return RegionType.toEnum(type_);
  }


  public boolean isCanonical()
  {
    return isCanonical_;
  }


  private PageDesc container_;
  private ComponentDesc content_;
  private String location_;
  private int type_;
  private boolean isCanonical_;
}