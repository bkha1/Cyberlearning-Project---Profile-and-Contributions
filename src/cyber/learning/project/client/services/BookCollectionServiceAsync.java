package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;



public interface BookCollectionServiceAsync
{
  void getMyBooks(AccountDesc owner,
                  AsyncCallback<CallbackPayload<BookDesc[]>> callback);


  void getAllBooks(AsyncCallback<CallbackPayload<BookDesc[]>> callback);
}