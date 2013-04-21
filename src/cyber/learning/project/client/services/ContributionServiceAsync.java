package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.ContributionChangeRequest;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;

public interface ContributionServiceAsync
{
  void getProposedContributionsFor(
    ComponentDesc target,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);


  void getHistoricalContributionsFor(
    ComponentDesc target,
    boolean getAccepted,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);


  void updateContributionsAcceptance(
    ContributionChangeRequest[] changeRequests,
    AsyncCallback<CallbackPayload<ContributionDesc[]>> callback);
}