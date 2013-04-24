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
  public static final String CREATE_CONTRIBUTION =
    "INSERT INTO \"contributions\" " +
    "(" +
      "\"target_comp_id\"," +
      "\"proposed_comp_id\"," +
      "\"account_id\"," +
      "\"change_comment\"," +
      "\"timestamp\"," +
      "\"votes\"," +
      "\"acceptance_status" +
    ")" +
    "VALUES (?1,?2,?3,?4,?5,?6,?7)";
}