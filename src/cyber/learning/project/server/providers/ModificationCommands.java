package cyber.learning.project.server.providers;

final class ModificationCommands
{
  private ModificationCommands() {}


  public static final String CREATE_PAGE =
    "INSERT INTO \"pages\" (\"book_id\",\"isCanonical\") VALUES (?1,?2)";
  public static final String CREATE_REGION =
    "INSERT INTO \"regions\" " +
    "(" +
      "\"page_id\"," +
      "\"location\"," +
      "\"type\"," +
      "\"isCanonical\"" +
    ") " +
    "VALUES (?1,?2,?3,?4)";
  public static final String CREATE_COMPONENT =
    "INSERT INTO \"components\" " +
    "(" +
      "\"region_id\"," +
      "\"type\"," +
      "\"value\"" +
    ") " +
    "VALUES (?1,?2,?3)";
}