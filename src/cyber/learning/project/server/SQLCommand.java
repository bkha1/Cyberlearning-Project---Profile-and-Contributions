package cyber.learning.project.server;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;


/**
 * A decorator that restricts access to the underlying database connection
 * of a {@link PreparedStatement}.
 * <p/>
 * Note: The column index is one-based rather than zero-based, thus the first
 * column is '1' rather than '0'.
 */
public final class SQLCommand
{
  /**
   * @param columnIndex the one-based index of the boolean column
   * @param value the boolean value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setBinary(int columnIndex, Boolean value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.BOOLEAN);
    }
    else
    {
      parameterizedCommand_.setBoolean(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the byte column
   * @param value the byte value which <b>may not be null</b>
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setByte(int columnIndex, byte value)
    throws SQLException
  {
    parameterizedCommand_.setByte(columnIndex, value);
  }


  /**
   * @param columnIndex the one-based index of the byte array column
   * @param value the byte array value which <b>may not be null</b>
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   * @throws IllegalArgumentException if <code>value</code> is null or is of an
   *                                  empty length
   */
  public void setBytes(int columnIndex, byte[] value)
    throws SQLException
  {
    if (value == null || value.length == 0)
    {
      throw new IllegalArgumentException(
        "Can't store null array or zero-length array");
    }
    else
    {
      parameterizedCommand_.setBytes(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the SQL-based date column
   * @param value the SQL-based date value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setDate(int columnIndex, Date value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.DATE);
    }
    else
    {
      parameterizedCommand_.setDate(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the double column
   * @param value the double value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setDouble(int columnIndex, Double value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.DOUBLE);
    }
    else
    {
      parameterizedCommand_.setDouble(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the float column
   * @param value the float value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setFloat(int columnIndex, Float value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.FLOAT);
    }
    else
    {
      parameterizedCommand_.setFloat(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the integer column
   * @param value the integer value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setInt(int columnIndex, Integer value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.INTEGER);
    }
    else
    {
      parameterizedCommand_.setInt(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the long column
   * @param value the long value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setLong(int columnIndex, Long value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.BIGINT);
    }
    else
    {
      parameterizedCommand_.setLong(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the short column
   * @param value the short value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setShort(int columnIndex, Short value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.TINYINT);
    }
    else
    {
      parameterizedCommand_.setShort(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the string column
   * @param value the string value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setString(int columnIndex, String value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.NVARCHAR);
    }
    else
    {
      parameterizedCommand_.setString(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the SQL-based time column
   * @param value the SQL-based time value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setTime(int columnIndex, Time value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.TIME);
    }
    else
    {
      parameterizedCommand_.setTime(columnIndex, value);
    }
  }


  /**
   * @param columnIndex the one-based index of the SQL-based time stamp column
   * @param value the SQL-based time stamp value which may be null
   * @throws SQLException if the specified <code>columnIndex</code> is not
   *                      within range
   */
  public void setTimestamp(int columnIndex, Timestamp value)
    throws SQLException
  {
    if (value == null)
    {
      parameterizedCommand_.setNull(columnIndex, Types.TIMESTAMP);
    }
    else
    {
      parameterizedCommand_.setTimestamp(columnIndex, value);
    }
  }


  /**
   * Notify that the current tuple has been completed and can be added to the
   * pending batch transaction.
   *
   * @throws IllegalStateException if the bound database connection is not a
   *                               multi-staged connection
   * @throws SQLException if the completed tuple cannot be added to the batch
   */
  public void tupleComplete() throws IllegalStateException, SQLException
  {
    if (!allowBatching_)
    {
      throw new IllegalStateException("Bound connection isn't multi-staged");
    }
    else
    {
      parameterizedCommand_.addBatch();
    }
  }


  SQLCommand(Connection conn, String command, boolean allowBatching)
    throws SQLException
  {
    allowBatching_ = allowBatching;
    parameterizedCommand_ = conn.prepareStatement(command,
                                                  RETURN_GENERATED_KEYS);
  }


  PreparedStatement getStatment()
  {
    return parameterizedCommand_;
  }


  private final boolean allowBatching_;
  private final PreparedStatement parameterizedCommand_;
}