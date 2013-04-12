package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.BookDesc;


@RemoteServiceRelativePath("bookCollection")
public interface BookCollectionService extends RemoteService
{
  CallbackPayload<BookDesc[]> getMyBooks(AccountDesc owner);


  CallbackPayload<BookDesc[]> getAllBooks();
}