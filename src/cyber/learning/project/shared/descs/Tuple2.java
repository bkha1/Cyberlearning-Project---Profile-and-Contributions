package cyber.learning.project.shared.descs;

public final class Tuple2 <A, B>
{
  public static <A, B> Tuple2<A, B> create(A item1, B item2)
  {
    return new Tuple2<A, B>(item1, item2);
  }


  public A get1()
  {
    return item1_;
  }


  public B get2()
  {
    return item2_;
  }


  private Tuple2(A item1, B item2)
  {
    item1_ = item1;
    item2_ = item2;
  }


  private final A item1_;
  private final B item2_;
}