package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ComponentChangeRequest;


public interface RegionChangeRequest
{
  int getRegionID();


  BookDesc getContainingBook();


  String getLocation();


  int getType();


  ComponentChangeRequest getComponentChangeRequest();
}