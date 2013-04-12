package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;


public interface AccountServiceAsync
{
  void create(String username, String password,
              AsyncCallback<CallbackPayload<AccountDesc>> callback);


  void login(String username, String password,
             AsyncCallback<CallbackPayload<AccountDesc>> callback);
}
