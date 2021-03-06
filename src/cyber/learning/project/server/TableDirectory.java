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
        "\"password\" TEXT NOT NULL, " +
        "\"learning_style\" TEXT, " +
        "\"education_level\" TEXT, " +
        "\"age\" INTEGER " +
      ")",

      // Profile table
      "CREATE TABLE IF NOT EXISTS \"profile\" " +
      "( " +
        "\"profile_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
        "\"learning_ability\" INTEGER NOT NULL, " +
        "\"learning_interest\" INTEGER NOT NULL, " +
        "\"prior_knowledge\" INTEGER NOT NULL " +
      ")",

      // Account-Subject_Profile-xref table
      "CREATE TABLE IF NOT EXISTS \"account-profile-xref\" " +
      "(" +
        "\"account_id\" INTEGER NOT NULL, " +
        "\"subject_profile_id\" INTEGER NOT NULL, " +
        "FOREIGN KEY(\"account_id\") " +
          "REFERENCES \"accounts\"(\"account_id\"), " +
        "FOREIGN KEY(\"subject_profile_id\") " +
          "REFERENCES \"subject_profile\"(\"subject_profile_id\") " +
      ")",

      // Books table
      "CREATE TABLE IF NOT EXISTS \"books\" " +
      "(" +
        "\"book_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
        "\"account_id\" INTEGER NOT NULL, " +
        "\"subject\" TEXT NOT NULL, " +
        "FOREIGN KEY(\"account_id\") REFERENCES \"accounts\"(\"account_id\")" +
      ")",

      // Page table
      "CREATE TABLE IF NOT EXISTS \"pages\" " +
      "(" +
        "\"page_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
        "\"book_id\" INTEGER NOT NULL, " +
        "\"isCanonical\" BOOL NOT NULL, " +
        "FOREIGN KEY(\"book_id\") REFERENCES \"books\"(\"book_id\")" +
      ")",

      // Regions table
      "CREATE TABLE IF NOT EXISTS \"regions\" " +
      "(" +
        "\"region_id\" INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
        "\"page_id\" INTEGER NOT NULL, "+
        "\"location\" TEXT NOT NULL, " +
        "\"type\" INTEGER NOT NULL, " +
        "\"isCanonical\" BOOL NOT NULL, " +
        "FOREIGN KEY(\"page_id\") REFERENCES \"pages\"(\"page_id\")" +
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
        "\"target_comp_id\" INTEGER NOT NULL, " +
        "\"proposed_comp_id\" INTEGER NOT NULL, " +
        "\"account_id\" INTEGER NOT NULL, " +
        "\"change_comment\" TEXT NOT NULL, " +
        "\"timestamp\" DATETIME NOT NULL, " +
        "\"votes\" INTEGER NOT NULL, " +
        "\"acceptance_status\" INTEGER NOT NULL," +
        "FOREIGN KEY(\"target_comp_id\") " +
          "REFERENCES \"components\"(\"comp_id\"), " +
        "FOREIGN KEY(\"proposed_comp_id\") " +
          "REFERENCES \"components\"(\"comp_id\"), " +
        "FOREIGN KEY(\"account_id\") REFERENCES \"accounts\"(\"account_id\") " +
      ")"
    };
}