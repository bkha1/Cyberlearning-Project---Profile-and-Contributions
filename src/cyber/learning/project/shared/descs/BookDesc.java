package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class BookDesc implements Serializable
{
  public BookDesc()
  {
    // Appease the GWT compiler with a nullary constructor.
  }


  public BookDesc(int id)
  {
    id_ = id;
  }


  public int getID()
  {
    return id_;
  }


  private int id_;
}