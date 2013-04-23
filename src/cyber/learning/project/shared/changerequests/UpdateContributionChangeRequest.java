package cyber.learning.project.shared.changerequests;

import java.io.Serializable;

import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


@SuppressWarnings("serial")
public class UpdateContributionChangeRequest
  implements ContributionChangeRequest,
             Serializable
{
  public UpdateContributionChangeRequest() {}


  public UpdateContributionChangeRequest(int newAcceptanceStatus,
                                         int newVoteCount,
                                         ContributionDesc source)
  {
    newAcceptanceStatus_ = newAcceptanceStatus;
    newVoteCount_ = newVoteCount;
    source_ = source;
  }


  @Override
  public int getContributionID()
  {
    return source_.getID();
  }


  @Override
  public int getTargetComponentID()
  {
    return source_.getTargetedComponent().getID();
  }


  @Override
  public int getProposedComponentID()
  {
    return source_.getProposedComponent().getID();
  }


  @Override
  public int getContributorID()
  {
    return source_.getContributor().getID();
  }


  @Override
  public String getChangeComment()
  {
    return source_.getChangeComment();
  }


  @Override
  public int getVotes()
  {
    return newVoteCount_;
  }


  @Override
  public int getAcceptanceStatus()
  {
    return newAcceptanceStatus_;
  }


  @Override
  public ContributionDesc getContribution()
  {
    return source_;
  }


  @Override
  public ComponentDesc getTargetComponent()
  {
    return source_.getTargetedComponent();
  }


  @Override
  public ComponentDesc getProposedComponent()
  {
    return source_.getProposedComponent();
  }


  @Override
  public AccountDesc getContributor()
  {
    return source_.getContributor();
  }


  private int newAcceptanceStatus_;
  private int newVoteCount_;
  private ContributionDesc source_;
}