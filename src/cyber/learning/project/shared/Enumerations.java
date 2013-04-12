package cyber.learning.project.shared;


public final class Enumerations
{
  public enum RegionType
  {
    ABSOLUTE, GRID;

    public static RegionType toEnum(int type)
    {
      switch (type)
      {
        case 0:
          return ABSOLUTE;
        case 1:
        default:
          return GRID;
      }
    }


    public static int toInteger(RegionType type)
    {
      return type.ordinal();
    }
  }


  public enum ComponentType
  {
    IMAGE, SOUND, TEXT, VIDEO;


    public static ComponentType toEnum(int type)
    {
      switch (type)
      {
        case 0:
          return IMAGE;
        case 1:
          return SOUND;
        case 2:
          return TEXT;
        case 3:
        default:
          return VIDEO;
      }
    }


    public static int toInteger(ComponentType type)
    {
      return type.ordinal();
    }
  }


  public enum AcceptanceStatus
  {
    ACCEPTED, PENDING, REJECTED;


    public static AcceptanceStatus toEnum(int type)
    {
      switch (type)
      {
        case 0:
          return ACCEPTED;
        case 1:
          return PENDING;
        case 2:
        default:
          return REJECTED;
      }
    }


    public static int toInteger(AcceptanceStatus type)
    {
      return type.ordinal();
    }
  }


  private Enumerations() {}
}