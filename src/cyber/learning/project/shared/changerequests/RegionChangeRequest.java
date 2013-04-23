package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.ComponentChangeRequest;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;



public interface RegionChangeRequest
{
  int getRegionID();


  int getContainingPageID();


  String getLocation();


  int getRegionType();


  boolean isCanonical();


  Iterable<? extends ComponentChangeRequest> getModifiedComponents();


  RegionDesc getRegion();


  PageDesc getContainingPage();
}