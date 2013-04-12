package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.Enumerations.ComponentType;
import cyber.learning.project.shared.Enumerations.RegionType;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;


@SuppressWarnings("serial")
public final class NewBookChangeRequest implements BookChangeRequest,
                                                   Serializable
{
  public NewBookChangeRequest() {}


  public NewBookChangeRequest(BookDesc containingBook,
                              AccountDesc contributor,
                              String location,
                              RegionType regionType,
                              ComponentType contentType,
                              String contentValue,
                              String changeComment)
  {
    containingBook_ = containingBook;
    contributor_ = contributor;
    location_ = location;
    regionType_ = RegionType.toInteger(regionType);
    contentType_ = ComponentType.toInteger(contentType);
    contentValue_ = contentValue;
    changeComment_ = changeComment;
  }


  @Override
  public int getComponentID()
  {
    return -1;
  }


  @Override
  public int getContainingBookID()
  {
    return containingBook_.getID();
  }


  @Override
  public int getContainingBookOwnerID()
  {
    return containingBook_.getOwner().getID();
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
    return regionType_;
  }


  @Override
  public String getLocation()
  {
    return location_;
  }


  @Override
  public int getContentType()
  {
    return contentType_;
  }


  @Override
  public String getContentValue()
  {
    return contentValue_;
  }


  private BookDesc containingBook_;
  private AccountDesc contributor_;
  private String location_;
  private int regionType_;
  private int contentType_;
  private String contentValue_;
  private String changeComment_;
}