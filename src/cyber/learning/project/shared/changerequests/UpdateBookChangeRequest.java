package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.ComponentType;
import cyber.learning.project.shared.Enumerations.RegionType;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;


@SuppressWarnings("serial")
public final class UpdateBookChangeRequest implements BookChangeRequest,
                                                      Serializable
{
  public UpdateBookChangeRequest() {}


  public UpdateBookChangeRequest(ComponentDesc target,
                                 AccountDesc contributor,
                                 String changeComment)
  {
    target_ = target;
    contributor_ = contributor;
    changeComment_ = changeComment;
  }


  @Override
  public int getComponentID()
  {
    return target_.getID();
  }


  @Override
  public int getContainingBookID()
  {
    return target_.getContainingRegion().getContainingBook().getID();
  }


  @Override
  public int getContributorID()
  {
    return contributor_.getID();
  }


  @Override
  public String getChangeComment()
  {
    return changeComment_;
  }


  @Override
  public int getRegionType()
  {
    return RegionType.toInteger(target_.getContainingRegion().getRegionType());
  }


  @Override
  public String getLocation()
  {
    return location_ != null ?
           location_ :
           target_.getContainingRegion().getLocation();
  }


  @Override
  public int getContentType()
  {
    return contentType_ != null ?
           contentType_  :
           ComponentType.toInteger(target_.getComponentType());
  }


  @Override
  public String getContentValue()
  {
    return contentValue_ != null ?
           contentValue_ :
           target_.getContentValue();
  }


  public void setLocation(String location)
  {
    location_ = location;
  }


  public void setNewContent(int contentType, String contentValue)
  {
    contentType_ = contentType;
    contentValue_ = contentValue;
  }


  public void setContentValue(String contentValue)
  {
    contentValue_ = contentValue;
  }


  private ComponentDesc target_;
  private AccountDesc contributor_;
  private String location_;
  private Integer contentType_;
  private String contentValue_;
  private String changeComment_;
}