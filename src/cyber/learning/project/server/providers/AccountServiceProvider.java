package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.insertTupleWithAutoIncrementingID;
import static cyber.learning.project.server.PersistenceManager.makeParameterizedCommandFor;
import static cyber.learning.project.server.PersistenceManager.searchForScalar;
import static cyber.learning.project.shared.CallbackPayload.make;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.AccountService;
import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;



@SuppressWarnings("serial")
public final class AccountServiceProvider extends RemoteServiceServlet
                                          implements AccountService
{
  @Override
  public CallbackPayload<AccountDesc> create(String username, String password)
  {
    try
    {
      final SQLCommand command = makeParameterizedCommandFor(CREATE);
      command.setString(1, username);
      command.setString(2, password);
      return make(new AccountDesc(insertTupleWithAutoIncrementingID(command),
                                  username));
    }
    catch (Throwable t)
    {
      return make(t, AccountDesc.class);
    }
  }


  @Override
  public CallbackPayload<AccountDesc> login(String username, String password)
  {
    try
    {
      final SQLCommand command = makeParameterizedCommandFor(FIND_ACCOUNT);
      command.setString(1, username);
      command.setString(2, password);

      final Integer accountID = searchForScalar(command, Integer.class);
      return make(accountID == null ?
                  new AccountDesc(-1, "badAccount") :
                  new AccountDesc(accountID, username));
    }
    catch (Throwable t)
    {
      return make(t, AccountDesc.class);
    }
  }


  private static final String CREATE =
    "INSERT INTO \"accounts\" (\"username\",\"password\") VALUES (?1,?2)";
  private static final String FIND_ACCOUNT =
    "SELECT account_ID " +
    "FROM \"accounts\" " +
    "WHERE \"username\"=?1 AND \"password\"=?2";
}