package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.PageDesc;

public interface PageChangeRequest
{
  int getPageID();


  int getContainingBookID();


  boolean isCanonical();


  Iterable<? extends RegionChangeRequest> getModifiedRegions();


  PageDesc getPage();


  BookDesc getContainingBook();
}