package cyber.learning.project.shared.descs;

import java.io.Serializable;



@SuppressWarnings("serial")
public final class RegionDesc implements Serializable
{
  public RegionDesc()
  {
    // Appease the GWT compiler with a nullary constructor.
  }


  public RegionDesc(int id, BookDesc book)
  {
    id_ = id;
    book_ = book;
  }


  public int getID()
  {
    return id_;
  }


  public BookDesc getBook()
  {
    return book_;
  }


  private int id_;
  private BookDesc book_;
}