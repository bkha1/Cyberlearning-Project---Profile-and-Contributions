package cyber.learning.project.shared;


public final class Enumerations
{
  public enum ComponentType
  {
    IMAGE, SOUND, TEXT, VIDEO;


    public static ComponentType get(int value)
    {
      switch (value)
      {
        case 0:
          return IMAGE;
        case 1:
          return SOUND;
        case 2:
          return TEXT;
        case 3:
          return VIDEO;
        default:
          throw new IllegalStateException(
            String.format("Unknown enum value: %s", value));
      }
    }
  }


  public enum ApprovalStatus
  {
    PENDING, APPROVED, REJECTED;


    public static ApprovalStatus get(int value)
    {
      switch (value)
      {
        case 0:
          return PENDING;
        case 1:
          return APPROVED;
        case 2:
          return REJECTED;
        default:
          throw new IllegalStateException(
            String.format("Unknown enum value: %s", value));
      }
    }
  }


  private Enumerations() {}
}