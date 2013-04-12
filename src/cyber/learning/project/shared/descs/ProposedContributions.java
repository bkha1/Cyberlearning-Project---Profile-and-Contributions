package cyber.learning.project.shared.descs;

import java.io.Serializable;



@SuppressWarnings("serial")
public final class ProposedContributions implements Serializable
{
  public ProposedContributions() {}


  public ProposedContributions(int[] proposalCount, RegionDesc[] regions)
  {
    proposalCount_ = proposalCount;
    regions_ = regions;
  }


  /** @return -1 if region is not known, otherwise proposal count */
  public int getProposalCount(RegionDesc region)
  {
    for (int i = 0; i != regions_.length; ++i)
    {
      final RegionDesc inspectee = regions_[i];
      if (inspectee.isEqualTo(region))
      {
        return proposalCount_[i];
      }
    }

    return -1;
  }


  private int[] proposalCount_;
  private RegionDesc[] regions_;
}