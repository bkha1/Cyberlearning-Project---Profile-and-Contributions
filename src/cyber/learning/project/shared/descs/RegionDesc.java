package cyber.learning.project.shared.descs;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.RegionType;



@SuppressWarnings("serial")
public final class RegionDesc extends BaseDesc
                              implements Serializable
{
  public RegionDesc() {}


  public RegionDesc(int id,
                    BookDesc container,
                    ComponentDesc content,
                    String location,
                    int type)
  {
    super(id);
    container_ = container;
    content_ = content;
    location_ = location;
    type_ = type;
  }


  public BookDesc getContainingBook()
  {
    return container_;
  }


  public ComponentDesc getContent()
  {
    return content_;
  }


  public String location()
  {
    return location_;
  }


  public RegionType getRegionType()
  {
    return RegionType.toEnum(type_);
  }


  private BookDesc container_;
  private ComponentDesc content_;
  private String location_;
  private int type_;
}