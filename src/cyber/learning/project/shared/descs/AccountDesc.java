package cyber.learning.project.shared.descs;

import java.io.Serializable;


@SuppressWarnings("serial")
public final class AccountDesc implements Serializable
{
  public AccountDesc()
  {
    // Appease the GWT compiler with a nullary constructor.
  }


  public AccountDesc(int id, String username)
  {
    id_ = id;
    username_ = username;
  }


  public AccountDesc(String username, String password)
  {
    username_ = username;
    password_ = password;
  }


  public int getID()
  {
    return id_;
  }


  public String getUsername()
  {
    return username_;
  }


  public String getPassword()
  {
    return password_;
  }


  private int id_;
  private String username_;
  private String password_;
}