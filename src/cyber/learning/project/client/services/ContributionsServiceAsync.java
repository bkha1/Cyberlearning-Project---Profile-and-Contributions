package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


public interface ContributionsServiceAsync
{
  void getComponentContributions(
    ComponentDesc component,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);


  void acceptContribution(
    ContributionDesc accepted,
    AsyncCallback<CallbackPayload<Boolean>> callback);


  void rejectContribution(
    ContributionDesc rejected,
    AsyncCallback<CallbackPayload<Boolean>> callback);


  void getAllAcceptedComponentContributions(
    ComponentDesc component,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);


  void getAllRejectedComponentContributions(
    ComponentDesc component,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);
}