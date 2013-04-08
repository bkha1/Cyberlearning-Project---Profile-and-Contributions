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
        "\"book_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
      ")",

      // Contributions table
      "CREATE TABLE IF NOT EXISTS \"contributions\" " +
      "(" +
        "\"contribution_id\" INTEGER PRIMARY KEY " +
                            "AUTOINCREMENT NOT NULL UNIQUE, " +
        "\"book_id\" INTEGER NOT NULL, " +
        "\"component_id\" INTEGER, " +
        "\"component_type\" INTEGER NOT NULL, " +
        "\"position\" TEXT NOT NULL, " +
        "\"contributor_id\" INTEGER, " +
        "\"change_log\" TEXT, " +
        "\"timestamp\" DATETIME NOT NULL, " +
        "\"status\" INTEGER NOT NULL " +
        // Foreign keys must be declared after all columns.
        "FOREIGN KEY(\"book_ref_id\") REFERENCES \"books\"(\"book_id\"), " +
        "FOREIGN KEY(\"contributor_id\") " +
          "REFERENCES \"accounts\"(\"account_id\") " +
       ")"
    };
}