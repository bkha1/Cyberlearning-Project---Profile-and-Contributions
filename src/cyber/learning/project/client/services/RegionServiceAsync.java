package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
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
    BookDesc container,
    AsyncCallback<CallbackPayload<RegionDesc[]>> callback);


  void getProposedRegionsForBook(
    BookDesc container,
    AsyncCallback<CallbackPayload<RegionDesc[]>> callback);
}