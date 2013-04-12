package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class BookDesc extends BaseDesc
                            implements Serializable
{
  public BookDesc() {}


  public BookDesc(int id,
                  AccountDesc owner,
                  RegionDesc[] regions)
  {
    super(id);
    owner_ = owner;
  }


  public AccountDesc getOwner()
  {
    return owner_;
  }


  private AccountDesc owner_;
}