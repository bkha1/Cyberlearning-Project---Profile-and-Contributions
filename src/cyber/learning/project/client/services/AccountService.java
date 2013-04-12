package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;


@RemoteServiceRelativePath("account")
public interface AccountService extends RemoteService
{
  CallbackPayload<AccountDesc> create(String username, String password);


  CallbackPayload<AccountDesc> login(String username, String password);
}