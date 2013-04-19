package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.ComponentChangeRequest;


public final class NewComponentChangeRequest implements ComponentChangeRequest
{
  public NewComponentChangeRequest(int componentType, String componentValue)
  {
    type_ = componentType;
    value_ = componentValue;
  }


  @Override
  public int getComponentID()
  {
    return -1;
  }

  @Override
  public int getType()
  {
    return type_;
  }

  @Override
  public String getValue()
  {
    return value_;
  }


  private int type_;
  private String value_;
}