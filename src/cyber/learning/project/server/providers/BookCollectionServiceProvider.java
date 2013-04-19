package cyber.learning.project.server.providers;

import static cyber.learning.project.server.PersistenceManager.makeParameterizedCommandFor;
import static cyber.learning.project.server.PersistenceManager.searchFor;
import static cyber.learning.project.shared.CallbackPayload.make;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.BookCollectionService;
import cyber.learning.project.server.PersistenceManager.QueryTupleResultSink;
import cyber.learning.project.server.SQLCommand;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;


@SuppressWarnings("serial")
public final class BookCollectionServiceProvider
  extends RemoteServiceServlet
  implements BookCollectionService
{
  @Override
  public CallbackPayload<BookDesc[]> getMyBooks(final AccountDesc owner)
  {
    try
    {
      final SQLCommand command = makeParameterizedCommandFor(OWNED_BOOKS);
      command.setInt(1, owner.getID());

      final Collection<BookDesc> owned = new LinkedList<BookDesc>();
      searchFor(
        command, true,
        new QueryTupleResultSink()
        {
          @Override
          public void putTo(ResultSet tuple) throws SQLException
          {
            owned.add(new BookDesc(tuple.getInt(1),
                                   owner,
                                   tuple.getString(2)));
          }
        });
      return make(owned.toArray(new BookDesc[owned.size()]));
    }
    catch (Throwable error)
    {
      return make(error, BookDesc[].class);
    }
  }


  @Override
  public CallbackPayload<BookDesc[]> getAllBooks()
  {
    try
    {
      final Collection<BookDesc> books = new LinkedList<BookDesc>();
      searchFor(
        makeParameterizedCommandFor(ALL_BOOKS),
        true,
        new QueryTupleResultSink()
        {
          @Override
          public void putTo(ResultSet tuple) throws SQLException
          {
            books.add(new BookDesc(tuple.getInt(1),
                                   new AccountDesc(tuple.getInt(1),
                                                   tuple.getString(2)),
                                   tuple.getString(2)));
          }
        });
      return make(books.toArray(new BookDesc[books.size()]));
    }
    catch (Throwable error)
    {
      return make(error, BookDesc[].class);
    }
  }


  private static final String OWNED_BOOKS =
    "SELECT \"book_ID\", \"subject\" " +
    "FROM \"books\" " +
    "WHERE \"account_id\"=?1";
  private static final String ALL_BOOKS =
    "SELECT \"tmp\".\"book_id\", " +
           "\"account_id\", " +
           "\"username\", " +
           "\"tmp\".\"subject\" " +
    "FROM \"accounts\" " +
      "INNER JOIN " +
      "(" +
        "SELECT \"book_id\", " +
               "\"account_id\" as \"aid\", " +
               "\"subject\" " +
        "FROM \"books\" " +
      ") \"tmp\" on \"tmp\".\"aid\"=\"accounts\".\"account_id\"";
}