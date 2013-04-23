package cyber.learning.project.shared.changerequests;

import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;



public interface ContributionChangeRequest
{
  int getContributionID();


  int getTargetComponentID();


  int getProposedComponentID();


  int getContributorID();


  String getChangeComment();


  int getVotes();


  int getAcceptanceStatus();


  ContributionDesc getContribution();


  ComponentDesc getTargetComponent();


  ComponentDesc getProposedComponent();


  AccountDesc getContributor();
}