package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


@RemoteServiceRelativePath("book")
public interface ContributionsService extends RemoteService
{
  CallbackPayload<ContributionDesc[]>
  getComponentContributions(ComponentDesc component);


  CallbackPayload<Boolean> acceptContribution(ContributionDesc accepted);


  CallbackPayload<Boolean> rejectContribution(ContributionDesc rejected);


  CallbackPayload<ContributionDesc[]>
  getAllAcceptedComponentContributions(ComponentDesc component);


  CallbackPayload<ContributionDesc[]>
  getAllRejectedComponentContributions(ComponentDesc component);
}