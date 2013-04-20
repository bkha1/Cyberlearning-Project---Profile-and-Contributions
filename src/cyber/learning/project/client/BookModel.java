package cyber.learning.project.client;

import java.util.UUID;

import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.PageDesc;
import cyber.learning.project.shared.descs.Tuple2;


final class BookModel
{
  public enum ContentType { IMAGE, SOUND, TEXT, VIDEO }
  public enum RegionType { ABSOLUTE }


  public BookModel(BookDesc container, AccountDesc user)
  {
    container_ = container;
    user_ = user;
  }


  public Iterable<BookDesc> getRegionsForPage(PageDesc page)
  {
    return null;
  }


  public int getProposalCountForRegion(UUID region)
  {
    return -1;
  }


  public UUID addPage()
  {
    return null;
  }


  public Tuple2<UUID, UUID> addRegion(PageDesc containingPage,
                                      String location,
                                      RegionType regionType,
                                      ContentType contentType,
                                      String value)
  {
    return null;
  }


  public void updateRegionLocation(UUID modifiedRegion,
                                   String newLocation)
  {

  }


  public Tuple2<UUID, UUID> updateRegionsContentType(UUID modifiedRegion,
                                                     ContentType newContentType,
                                                     String newContentValue)
  {
    return null;
  }


  public void updateRegionsContentValue(UUID modifiedRegion,
                                        UUID modifiedContent,
                                        String newContentValue)
  {

  }


  public void saveModifications()
  {

  }


  private final BookDesc container_;
  private final AccountDesc user_;
}