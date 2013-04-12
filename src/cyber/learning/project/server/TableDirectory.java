package cyber.learning.project.server;


final class TableDirectory
{
  static String[] CREATION_COMMANDS =
    new String[]
    {
      // Accounts table
      "CREATE TABLE IF NOT EXISTS \"accounts\" " +
      "(" +
        "\"account_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
        "\"username\" TEXT NOT NULL UNIQUE, " +
        "\"password\" TEXT NOT NULL" +
      ")",

      // Books table
      "CREATE TABLE IF NOT EXISTS \"books\" " +
      "(" +
        "\"book_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
        "\"account_id\" INTEGER NOT NULL, " +
        "FOREIGN KEY(\"account_id\") REFERENCES \"accounts\"(\"account_id\")" +
      ")",

      // Regions table
      "CREATE TABLE IF NOT EXISTS \"regions\" " +
      "(" +
        "\"region_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
        "\"book_id\" INTEGER NOT NULL, "+
        "\"location\" TEXT NOT NULL, " +
        "\"type\" INTEGER NOT NULL, " +
        "\"isCanonical\" BOOL NO NULL, " +
        "FOREIGN KEY(\"book_id\") REFERENCES \"books\"(\"book_id\")" +
      ")",

      // Components table
      "CREATE TABLE IF NOT EXISTS \"components\" " +
      "(" +
        "\"comp_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
        "\"region_id\" INTEGER NOT NULL, " +
        "\"type\" INTEGER NOT NULL, " +
        "\"value\" TEXT NOT NULL, " +
        "FOREIGN KEY(\"region_id\") REFERENCES \"regions\"(\"region_id\")" +
      ")",

      // Contributions table
      "CREATE TABLE IF NOT EXISTS \"contributions\" " +
      "(" +
        "\"contrib_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
        "\"comp_id\" INTEGER NOT NULL, " +
        "\"account_id\" INTEGER NOT NULL, " +
        "\"change_comment\" TEXT NOT NULL, " +
        "\"timestamp\" DATETIME NOT NULL, " +
        "\"acceptance_status\" INTEGER NOT NULL," +
        "FOREIGN KEY(\"comp_id\") REFERENCES \"components\"(\"comp_id\"), " +
        "FOREIGN KEY(\"account_id\") REFERENCES \"accounts\"(\"account_id\") " +
      ")"
    };
}