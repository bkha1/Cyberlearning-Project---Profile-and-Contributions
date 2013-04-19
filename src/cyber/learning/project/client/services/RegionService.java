package cyber.learning.project.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.changerequests.RegionChangeRequest;
import cyber.learning.project.shared.descs.BookDesc;
import cyber.learning.project.shared.descs.RegionDesc;


@RemoteServiceRelativePath("region")
public interface RegionService extends RemoteService
{
  CallbackPayload<RegionDesc> createCanonical(RegionChangeRequest regionCR);


  CallbackPayload<RegionDesc> createProposed(RegionChangeRequest regionCR);


  CallbackPayload<RegionDesc[]> updateRegions(RegionChangeRequest[] updates);


  CallbackPayload<RegionDesc[]> getCanonicalRegionsForBook(BookDesc container);


  CallbackPayload<RegionDesc[]> getProposedRegionsForBook(BookDesc container);
}