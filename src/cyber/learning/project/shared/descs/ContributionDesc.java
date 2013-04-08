package cyber.learning.project.shared.descs;

import static cyber.learning.project.shared.Enumerations.ApprovalStatus.get;

import java.io.Serializable;
import java.sql.Date;

import cyber.learning.project.shared.Enumerations.ApprovalStatus;


@SuppressWarnings("serial")
public final class ContributionDesc implements Serializable
{
  public ContributionDesc()
  {
    // Appease the GWT compiler with a nullary constructor.
  }


  public ContributionDesc(int id,
                          BookDesc target,
                          ComponentDesc proposed,
                          AccountDesc contributor,
                          String changeLog,
                          Date proposalTime,
                          int status)
  {
    contributionID_ = id;
    targetedBook_ = target;
    proposedModification_ = proposed;
    contributor_ = contributor;
    changeLog_ = changeLog;
    timestamp_ = proposalTime;
    status_ = status;
  }


  public ContributionDesc(BookDesc target,
                          ComponentDesc proposed,
                          AccountDesc contributor,
                          String changeLog,
                          ApprovalStatus status)
  {
    targetedBook_ = target;
    proposedModification_ = proposed;
    contributor_ = contributor;
    changeLog_ = changeLog;
    timestamp_ = new Date(System.currentTimeMillis());
    status_ = status.ordinal();
  }


  int getContributionID()
  {
    /* Not used thus package-private scoped. */
    return contributionID_;
  }


  public BookDesc getTargetedBook()
  {
    return targetedBook_;
  }


  public ComponentDesc getProposed()
  {
    return proposedModification_;
  }


  public AccountDesc getContributor()
  {
    return contributor_;
  }


  public String getChangeLog()
  {
    return changeLog_;
  }


  public Date getTimestamp()
  {
    return timestamp_;
  }


  public ApprovalStatus getStatus()
  {
    return get(status_);
  }


  private int contributionID_;
  private BookDesc targetedBook_;
  private ComponentDesc proposedModification_;
  private AccountDesc contributor_;
  private String changeLog_;
  private Date timestamp_;
  private int status_;
}