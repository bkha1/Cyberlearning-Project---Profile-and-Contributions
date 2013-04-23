package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;

public interface BookChangeRequest
{
  int getContainingBookID();


  int getContributorID();


  boolean isContributorTheOwner();


  Iterable<? extends PageChangeRequest> getModifiedPages();


  String getChangeComment();


  BookDesc getContaingBook();


  AccountDesc getContributor();
}