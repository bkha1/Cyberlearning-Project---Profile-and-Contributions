package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class PageDesc extends BaseDesc
                            implements Serializable
{
  public PageDesc() {}


  public PageDesc(int id,
                  BookDesc containingBook,
                  boolean isCanonical)
  {
    containingBook_ = containingBook;
    isCanonical_ = isCanonical;
  }


  public BookDesc getContainingBook()
  {
    return containingBook_;
  }


  public boolean isCanonical()
  {
    return isCanonical_;
  }


  private BookDesc containingBook_;
  private boolean isCanonical_;
}