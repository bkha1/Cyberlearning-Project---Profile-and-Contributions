package cyber.learning.project.server;

import static cyber.learning.project.server.TableDirectory.CREATION_COMMANDS;
import static java.sql.DriverManager.getConnection;
import static java.util.logging.Level.SEVERE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


/**
 * The single access point to the SQLite implementation of a relational
 * database named 'persistence.sql'. The use of the supplied execution methods
 * ensures single-threaded access to the database.
 */
final class PersistenceManager
{
  /**
   * A mechanism that handles a tuple that satisfies a given search query.
   */
  public interface QueryTupleResultSink
  {
    /**
     * Puts a result tuple that satisfied a given search query.
     * <p/>
     * Note: The {@link ResultSet} reference should only be used to access the
     *       current tuple's cell. Although it is possible to drain the result
     *       set of all tuple results, it is not recommended.
     * <p/>
     * <b>
     *   Note: The {@link ResultSet} reference should never be closed. The
     *         invoker of the method will handle the disposal of the result set.
     * </b>
     *
     * @param tuple the result query
     */
    void putTo(ResultSet tuple);
  }


  /**
   * Retrieves the connection to the relational database.
   * </p>
   * The retrieved connection must be used with extreme care as this connection
   * is used by other consumers. Early termination of the connection or other
   * adjustments may result in catastrophic failure of the application and/or
   * lead to an inconsistent state of the database.
   * </p>
   * <b>
   *   Note: All access to the database must be synchronized against the static
   *         instance of the {@link PersistenceManager}. Failure to enforce this
   *         concurrency invariant may result in an inconsistent state of the
   *         database.
   * </b>
   *
   * @return the connection to the database
   */
  public static
  Connection getDatabaseConnection()
  {
    return CONN;
  }


  /**
   * Inserts a tuple.
   *
   * @param command the SQL command to update a specified tuple
   * @throws SQLException if the underlying database connection is closed or the
   *                      update command is corrupt and malformed
   */
  public static
  void insertTuple(SQLCommand command) throws SQLException
  {
    executeModification(command.getStatment(), false);
  }


  /**
   * Inserts a tuple whose primary key is a single numeric column that
   * auto-increments.
   *
   * @param command the SQL command to update a specified tuple
   * @return the auto-incremented ID
   * @throws SQLException if the underlying database connection is closed or the
   *                      update command is corrupt and malformed
   */
  public static
  int insertTupleWithAutoIncrementingID(SQLCommand command)
    throws SQLException
  {
    return executeModification(command.getStatment(), true);
  }


  /**
   * Updates a tuple.
   *
   * @param command the SQL command to update a specified tuple
   * @throws SQLException if the underlying database connection is closed or the
   *                      update command is corrupt and malformed
   */
  public static
  void updateTuple(SQLCommand command) throws SQLException
  {
    executeModification(command.getStatment(), false);
  }


  /**
   * Creates a SQL command whose parameters can be established via setters.
   *
   * @param command the parameterized SQL command
   * @return a SQL command whose parameters can be set via setter methods
   * @throws SQLException if the underlying database connection is closed
   * @see {@link SQLCommand}
   */
  public static
  SQLCommand makeParameterizedCommandFor(String command) throws SQLException
  {
    return new SQLCommand(CONN, command);
  }


  /**
   * Searches for multiple tuples that satisfies the given prepared query.
   *
   * @param searchQuery the prepared query that may return multiple tuples
   * @param sink a store which accepts a result tuple
   * @throws SQLException if the underlying database connection was closed or
   *                      the supplied query was corrupt and malformed
   */
  public static
  void searchFor(SQLCommand searchQuery, QueryTupleResultSink sink)
    throws SQLException
  {
    final PreparedStatement ps = searchQuery.getStatment();
    try
    {
      final ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        sink.putTo(rs);
      }
    }
    finally
    {
      ps.close();
    }
  }


  /**
   * Searches for a scalar result that satisfies the given prepared query.
   *
   * @param searchQuery the prepared query that may return a scalar value
   * @param expectedType the type of the scalar value
   * @return the typed value or null if no value was returned
   * @throws SQLException if the underlying database connection was closed or
   *                      the supplied query was corrupt and malformed
   */
  public static <T>
  T searchForScalar(SQLCommand searchQuery, Class<T> expectedType)
    throws SQLException
  {
    final PreparedStatement ps = searchQuery.getStatment();
    try
    {
      final ResultSet rs = ps.executeQuery();

      Object result = null;
      while (rs.next())
      {
        result = rs.getObject(1);
      }

      return result != null ? expectedType.cast(result) : null;
    }
    finally
    {
      ps.close();
    }
  }


  private static void ensureDatabaseExistence() throws SQLException
  {
    final Statement queryForUserTableCount = CONN.createStatement();
    try
    {
      final ResultSet result =
        queryForUserTableCount.executeQuery("SELECT COUNT(*) " +
      	  	                                "FROM sqlite_master " +
                                            "WHERE type='table'");
      if (result.next() && result.getInt(1) != TABLE_COUNT)
      {
        for (String tableCreationCommand : CREATION_COMMANDS)
        {
          final Statement createTable = CONN.createStatement();
          try
          {
            createTable.executeUpdate(tableCreationCommand);
          }
          finally
          {
            createTable.close();
          }
        }
      }
    }
    finally
    {
      queryForUserTableCount.close();
    }
  }


  private static Integer executeModification(PreparedStatement ps,
                                             boolean hasGeneratedKey)
    throws SQLException
  {
    try
    {
      ps.executeUpdate();
      if (!hasGeneratedKey)
      {
        return null;
      }
      else
      {
        final ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
      }
    }
    finally
    {
      ps.close();
    }
  }

  private static final Connection CONN;
  private static final Logger LOG;
  private static final Integer TABLE_COUNT;

  static
  {
    /* This value will need to be updated when a new table is added to the
     * database.
     */
    TABLE_COUNT = 1;

    LOG = Logger.getLogger(PersistenceManager.class.getName());
    try
    {
      Class.forName("org.sqlite.JDBC");
      CONN = getConnection("jdbc:sqlite:persistence.sql");
      ensureDatabaseExistence();
    }
    catch (ClassNotFoundException ex)
    {
      LOG.log(SEVERE, "Error loading SQLite connector driver.", ex);
      throw new ExceptionInInitializerError(ex);
    }
    catch (SQLException ex)
    {
      LOG.log(SEVERE, "Error with connection to SQLite database.", ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
}