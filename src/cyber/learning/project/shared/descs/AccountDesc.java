package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class AccountDesc extends BaseDesc
                               implements Serializable
{
  public AccountDesc() {}


  public AccountDesc(int id, String username)
  {
    super(id);
    username_ = username;
  }


  public String getUsername()
  {
    return username_;
  }


  private String username_;
}