package cyber.learning.project.shared.descs;


public interface ComponentChangeRequest
{
  int getComponentID();


  int getContainingRegionID();


  int getComponentType();


  String getValue();


  ComponentDesc getComponent();


  RegionDesc getContainingRegion();
}