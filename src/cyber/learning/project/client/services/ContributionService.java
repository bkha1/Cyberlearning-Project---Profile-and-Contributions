package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.ContributionChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


@RemoteServiceRelativePath("contribution")
public interface ContributionService extends RemoteService
{
  CallbackPayload<ContributionDesc[]>
  getProposedContributionsFor(ComponentDesc target);


  CallbackPayload<ContributionDesc[]>
  getHistoricalContributionsFor(ComponentDesc target, boolean getAccepted);


  CallbackPayload<ContributionDesc[]>
  updateContributionsAcceptance(ContributionChangeRequest[] changeRequests);
}