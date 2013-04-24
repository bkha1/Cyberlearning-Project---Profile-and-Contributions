package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.RegionDesc;


public interface RegionServiceAsync
{
  void createCanonical(RegionChangeRequest regionCR,
                       AsyncCallback<CallbackPayload<RegionDesc>> callback);


  void createProposed(RegionChangeRequest regionCR,
                      AsyncCallback<CallbackPayload<RegionDesc>> callback);


  void updateRegions(RegionChangeRequest[] updates,
                     AsyncCallback<CallbackPayload<RegionDesc[]>> callback);


  void getCanonicalRegionsForBook(
    PageDesc container,
    AsyncCallback<CallbackPayload<RegionDesc[]>> callback);


  void getProposedRegionsForBook(
    PageDesc container,
    AsyncCallback<CallbackPayload<RegionDesc[]>> callback);
}