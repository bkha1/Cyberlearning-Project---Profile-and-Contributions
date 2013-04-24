package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.RegionDesc;


public interface ComponentChangeRequest
{
  int getComponentID();


  int getContainingRegionID();


  int getComponentType();


  String getValue();


  ComponentDesc getComponent();


  RegionDesc getContainingRegion();
}