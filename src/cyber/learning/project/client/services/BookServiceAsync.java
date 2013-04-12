package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ProposedContributions;
import cyber.learning.project.shared.descs.RegionDesc;



public interface BookServiceAsync
{
  void create(AsyncCallback<CallbackPayload<BookDesc>> callback);


  void getAllRegions(AsyncCallback<CallbackPayload<RegionDesc[]>> callback);


  void contribute(BookChangeRequest[] changeRequests,
                  AsyncCallback<CallbackPayload<Boolean>> callback);


  void getProposedContributions(
    BookDesc book,
    AsyncCallback<CallbackPayload<ProposedContributions>> callback);
}