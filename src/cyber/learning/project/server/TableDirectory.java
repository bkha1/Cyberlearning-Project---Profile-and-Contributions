package cyber.learning.project.server;


final class TableDirectory
{
  static String[] CREATION_COMMANDS =
    new String[]
    {
      // Account table
      "CREATE TABLE IF NOT EXISTS \"account\" " +
      "(" +
        "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
        "\"username\" TEXT NOT NULL UNIQUE, " +
        "\"password\" TEXT NOT NULL" +
      ")"
    };
}