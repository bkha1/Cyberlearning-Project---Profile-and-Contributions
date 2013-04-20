package cyber.learning.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cyber.learning.project.shared.descs.ContributionDesc;


final class ContributionsPanel extends HorizontalPanel
{
  public ContributionsPanel()
  {
    final TabPanel proposalPanel = new TabPanel();
    proposalPanel.setSize("1000px", "500px");

    proposalPanel.add(getLoadingPanel(), "Pending");
    proposalPanel.add(getLoadingPanel(), "Accepted");
    proposalPanel.add(getLoadingPanel(), "Rejected");

    proposalPanel.selectTab(0);
    proposalPanel.addSelectionHandler(new LoadPanel(proposalPanel));
  }


  private static Panel getLoadingPanel()
  {
    /* TODO: Make the label center to the tabbed panel. */
    final HorizontalPanel panel = new HorizontalPanel();
    panel.add(new Label("Loading..."));
    return panel;
  }


  @SuppressWarnings("unused")
  private static Panel getPendingPanel(Iterable<ContributionDesc> contributions)
  {
    final HorizontalPanel pendingPanel = new HorizontalPanel();

    pendingPanel.setSize("1000px", "500px");
    final ListBox pendingList = new ListBox(true);
    pendingPanel.add(pendingList);
    pendingList.setSize("250px", "500px");

    final VerticalPanel previewCommentAndControlPanel = new VerticalPanel();
    pendingPanel.add(previewCommentAndControlPanel);
    final RichTextArea previewArea = new RichTextArea();
    previewCommentAndControlPanel.add(previewArea);
    previewArea.setSize("750px", "400px");
    final TextBox changeLogTextBox = new TextBox();
    changeLogTextBox.setSize("750px", "50px");
    previewCommentAndControlPanel.add(changeLogTextBox);
    final HorizontalPanel controlPanel = new HorizontalPanel();
    previewCommentAndControlPanel.add(controlPanel);
    controlPanel.setSize("750px", "25px");
    final Button acceptButton = new Button("Accept");
    final Button rejectButton = new Button("Reject");
    final Button upVoteButton = new Button("UpVote");
    final Button downVoteButton = new Button("DownVote");
    controlPanel.add(acceptButton);
    controlPanel.add(rejectButton);
    controlPanel.add(upVoteButton);
    controlPanel.add(downVoteButton);

    return pendingPanel;
  }


  private static final class LoadPanel implements SelectionHandler<Integer>
  {
    public LoadPanel(TabPanel tabPanel)
    {
      tabPanel_ = tabPanel;
    }


    @Override
    public void onSelection(SelectionEvent<Integer> arg0)
    {

    }


    private final TabPanel tabPanel_;
  }


  private static Panel getHistoricalPanel()
  {
    final HorizontalPanel historicalPanel = new HorizontalPanel();

    historicalPanel.setSize("1000px", "500px");
    final ListBox pendingList = new ListBox(true);
    historicalPanel.add(pendingList);
    pendingList.setSize("250px", "500px");
    final VerticalPanel previewAndCommentPanel = new VerticalPanel();
    historicalPanel.add(previewAndCommentPanel);
    final RichTextArea previewArea = new RichTextArea();
    previewAndCommentPanel.add(previewArea);
    previewArea.setSize("750px", "450px");
    final TextBox changeLogTextBox = new TextBox();
    changeLogTextBox.setSize("750px", "50px");
    previewAndCommentPanel.add(changeLogTextBox);

    return historicalPanel;
  }


  private static class ContributionSelection implements ClickHandler
  {
    public ContributionSelection(Button acceptButton,
                                 Button rejectButton,
                                 RichTextArea previewArea,
                                 TextBox changeComment)
    {
      acceptButton_ = acceptButton;
      rejectButton_ = rejectButton;
      previewArea_ = previewArea;
      changeComment_ = changeComment;
    }


    @Override
    public void onClick(ClickEvent selectedListItemEvent)
    {

    }


    private final Button acceptButton_;
    private final Button rejectButton_;
    private final RichTextArea previewArea_;
    private final TextBox changeComment_;
  }


  private static final class AcceptContribution implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent acceptedClicked)
    {

    }
  }


  private static final class RejectContributions implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent rejectedClicked)
    {

    }
  }


  private static final class LikeContribution implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent likedClicked)
    {

    }
  }


  private static final class DislikeContribution implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent dislikeClicked)
    {

    }
  }


  private static class ViewHistoricalContributions implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent dislikeClicked)
    {

    }
  }


  private static class ViewAcceptedContributions
    extends ViewHistoricalContributions
  {

  }


  private static class ViewRejectedContributions
    extends ViewHistoricalContributions
  {

  }
}