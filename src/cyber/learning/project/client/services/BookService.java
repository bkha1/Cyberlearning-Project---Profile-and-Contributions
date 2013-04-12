package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.BookChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.ProposedContributions;
import cyber.learning.project.shared.descs.RegionDesc;


@RemoteServiceRelativePath("book")
public interface BookService extends RemoteService
{
  CallbackPayload<BookDesc> create();


  CallbackPayload<RegionDesc[]> getAllRegions();


  CallbackPayload<Boolean> contribute(BookChangeRequest[] changeRequests);


  CallbackPayload<ProposedContributions>
  getProposedContributions(BookDesc book);
}