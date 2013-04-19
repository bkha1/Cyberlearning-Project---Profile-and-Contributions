package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ComponentChangeRequest;

public final class NewRegionChangeRequest implements RegionChangeRequest
{
  public NewRegionChangeRequest() {}


  public NewRegionChangeRequest(BookDesc container,
                                int regionType,
                                String location,
                                ComponentChangeRequest compCR)
  {
    container_ = container;
    location_ = location;
    type_ = regionType;
    compCR_ = compCR;
  }


  @Override
  public int getRegionID()
  {
    return -1;
  }


  @Override
  public BookDesc getContainingBook()
  {
    return container_;
  }


  @Override
  public String getLocation()
  {
    return location_;
  }


  @Override
  public int getType()
  {
    return type_;
  }


  @Override
  public ComponentChangeRequest getComponentChangeRequest()
  {
    return compCR_;
  }


  private BookDesc container_;
  private int type_;
  private String location_;
  private ComponentChangeRequest compCR_;
}