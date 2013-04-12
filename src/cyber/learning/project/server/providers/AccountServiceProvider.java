package cyber.learning.project.server.providers;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.AccountService;
import cyber.learning.project.server.PersistenceManager;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;



@SuppressWarnings("serial")
public final class AccountServiceProvider extends RemoteServiceServlet
                                          implements AccountService
{
  @Override
  public CallbackPayload<AccountDesc> create(String username, String password)
  {
    PersistenceManager.getDatabaseConnection();
    return null;
  }


  @Override
  public CallbackPayload<AccountDesc> login(String username, String password)
  {
    return null;
  }
}