package cyber.learning.project.shared.changerequests;

public interface BookChangeRequest
{
  int getComponentID();


  int getContainingBookID();


  int getContainingBookOwnerID();


  int getContributorID();


  String getChangeComment();


  int getRegionType();


  String getLocation();



  int getContentType();


  String getContentValue();
}