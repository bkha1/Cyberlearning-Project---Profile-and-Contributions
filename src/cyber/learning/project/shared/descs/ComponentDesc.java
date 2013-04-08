package cyber.learning.project.shared.descs;

import static cyber.learning.project.shared.Enumerations.ComponentType.get;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.ComponentType;


@SuppressWarnings("serial")
public final class ComponentDesc implements Serializable
{
  public ComponentDesc()
  {
    // Appease the GWT compiler with a nullary constructor.
  }


  public ComponentDesc(int id,
                       ComponentType type,
                       String position,
                       BookDesc book,
                       RegionDesc region)
  {
    id_ = id;
    type_ = type.ordinal();
    position_ = position;
    book_ = book;
    region_ = region;
  }


  public int getID()
  {
    return id_;
  }


  public ComponentType getType()
  {
    return get(type_);
  }


  public String getPosition()
  {
    return position_;
  }


  public BookDesc getBook()
  {
    return book_;
  }


  public RegionDesc getRegion()
  {
    return region_;
  }


  private int id_;
  private int type_;
  private String position_;
  private BookDesc book_;
  private RegionDesc region_;
}