package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class BookDesc extends BaseDesc
                            implements Serializable
{
  public BookDesc() {}


  public BookDesc(int id,
                  AccountDesc owner,
                  String subject)
  {
    super(id);
    owner_ = owner;
    subject_ = subject;
  }


  public AccountDesc getOwner()
  {
    return owner_;
  }


  public String getSubject()
  {
    return subject_;
  }


  private AccountDesc owner_;
  private String subject_;
}