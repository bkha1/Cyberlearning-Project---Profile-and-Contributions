package cyber.learning.project.shared.descs;

import java.io.Serializable;
import java.sql.Date;

import cyber.learning.project.shared.Enumerations.AcceptanceStatus;


@SuppressWarnings("serial")
public final class ContributionDesc extends BaseDesc
                                    implements Serializable
{
  public ContributionDesc() {}


  public ContributionDesc(int id,
                          ComponentDesc target,
                          AccountDesc contributor,
                          String changeComment,
                          Date timestamp,
                          int votes,
                          int acceptanceStatus)
  {
    super(id);
    target_ = target;
    contributor_ = contributor;
    changeComment_ = changeComment;
    timestamp_ = timestamp;
    votes_ = votes;
    acceptanceStatus_ = acceptanceStatus;
  }


  public ComponentDesc getTargetedComponent()
  {
    return target_;
  }


  public AccountDesc getContributor()
  {
    return contributor_;
  }


  public String getChangeComment()
  {
    return changeComment_;
  }


  public Date getContributionTime()
  {
    return timestamp_;
  }


  public int getVotes()
  {
    return votes_;
  }


  public AcceptanceStatus getAcceptanceStatus()
  {
    return AcceptanceStatus.toEnum(acceptanceStatus_);
  }


  private ComponentDesc target_;
  private AccountDesc contributor_;
  private String changeComment_;
  private Date timestamp_;
  private int votes_;
  private int acceptanceStatus_;
}