package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.ContributionDesc;

@SuppressWarnings("serial")
public final class ContributionChangeRequest implements Serializable
{
  public ContributionChangeRequest() {}


  public ContributionChangeRequest(ContributionDesc source,
                                   int newAcceptanceStatus,
                                   int newVoteCount)
  {
    source_ = source;
    newAcceptanceStatus_ = newAcceptanceStatus;
    newVoteCount_ = newVoteCount;
  }


  public int getContributionID()
  {
    return source_.getID();
  }


  public ContributionDesc getContributionSource()
  {
    return source_;
  }

  public int getNewAcceptanceStatus()
  {
    return newAcceptanceStatus_;
  }


  public int getNewVoteCount()
  {
    return newVoteCount_;
  }


  private ContributionDesc source_;
  private int newAcceptanceStatus_;
  private int newVoteCount_;
}