package cyber.learning.project.shared.descs;

abstract class BaseDesc
{
  public BaseDesc() {}


  public BaseDesc(int id)
  {
    id_ = id;
  }


  public int getID()
  {
    return id_;
  }


  public boolean isEqualTo(BaseDesc inspectee)
  {
    return inspectee.id_ == id_;
  }


  private int id_;
}