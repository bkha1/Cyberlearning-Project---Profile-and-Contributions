package cyber.learning.project.server.providers;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cyber.learning.project.client.services.BookService;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ProposedContributions;
import cyber.learning.project.shared.descs.RegionDesc;



@SuppressWarnings("serial")
public final class BookServiceProvider extends RemoteServiceServlet
                                       implements BookService
{
  @Override
  public CallbackPayload<BookDesc> create()
  {
    return null;
  }


  @Override
  public CallbackPayload<RegionDesc[]> getAllRegions()
  {
    return null;
  }


  @Override
  public CallbackPayload<Boolean> contribute(BookChangeRequest[] changeRequests)
  {
    return null;
  }


  @Override
  public
  CallbackPayload<ProposedContributions> getProposedContributions(BookDesc book)
  {
    return null;
  }
}