package cyber.learning.project.client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


final class ContributionsPanel extends TabPanel
{
	private final ComponentDesc component_;
	private final AccountDesc editor_;
  public ContributionsPanel(ComponentDesc component, AccountDesc editor)
  {

    setSize("1000px", "500px");

    //dummy accounts
    AccountDesc testEditor1 = new AccountDesc(1, "test1");
    AccountDesc testEditor2 = new AccountDesc(1, "test2");

    //dummy components
    ComponentDesc testComp1 = new ComponentDesc(1, 2, "content1");//text type
    ComponentDesc testComp2 = new ComponentDesc(2, 2, "content2");

    //dummy contributionDescs for testing
    ContributionDesc testContribution1 = new ContributionDesc(101, testComp1, testEditor1, "the change comment", new Date(System.currentTimeMillis()), 1, 0);
    ContributionDesc testContribution2 = new ContributionDesc(102, testComp2, testEditor2, "another change comment", new Date(System.currentTimeMillis()),2,0);

    /*
    ContributionDesc[] testContributions = new ContributionDesc[2];
    testContributions[0] = testContribution1;
    testContributions[1] = testContribution2;
    */

    /*ArrayList<ContributionDesc> testcontr;
    testcontr = new ArrayList<ContributionDesc>;*/

    ArrayList<ContributionDesc> testList = new ArrayList<ContributionDesc>();
    testList.add(testContribution1);
    testList.add(testContribution2);



    //add(getLoadingPanel(), "Pending");
    add(getPendingPanel(testList), "Pending");

    add(getLoadingPanel(), "Accepted");
    //add(getHistoricalPanel(), "Accepted");

    add(getLoadingPanel(), "Rejected");



    selectTab(0);
    addSelectionHandler(new LoadPanel(this));

    final AsyncCallback<CallbackPayload<ContributionDesc[]>> callback =
      new AsyncCallback<CallbackPayload<ContributionDesc[]>>()
      {

        @Override
        public void onFailure(Throwable arg0) {
          // TODO Auto-generated method stub

        }

        @Override
        public void onSuccess(CallbackPayload<ContributionDesc[]> arg0)
        {

          if(arg0.hasResult())
          {
            arg0.getResult();
          }

        }

      };

    component_ = component;
    editor_ = editor;
  }



  private static Panel getLoadingPanel()
  {
    /* TODO: Make the label center to the tabbed panel. */
    final HorizontalPanel panel = new HorizontalPanel();
    panel.add(new Label("Loading..."));
    return panel;
  }


  @SuppressWarnings("unused")
  private static Panel getPendingPanel(final Iterable<ContributionDesc> contributions)
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

    //add contributions stuff to the pending list, assigns contribution's unique id to the value of the item in the list
    for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
    {
      ContributionDesc item = i.next();
      pendingList.addItem(item.getContributionTime().toString() + " - " + item.getContributor().getUsername() + " - " + item.getID(), "" + item.getID());
    }

    //gets the selected item and display the contents and comments on the side
    //pendingList.setSelectedIndex(0);//default selection
    pendingList.addChangeHandler(new ChangeHandler()
    {
      @Override
      public void onChange(ChangeEvent event)
      {
        int selectedIndex = pendingList.getSelectedIndex();

        if(selectedIndex > -1)
        {
          for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
          {
            ContributionDesc item = i.next();

            if(item.getID() == Integer.parseInt(pendingList.getValue(selectedIndex)))
            {
              previewArea.setText(item.getTargetedComponent().getContentValue());//get component's value
              changeLogTextBox.setText(item.getChangeComment());//sets comment box
            }
          }//end for loop
        }//end if statement
      }//end onChange
    }//end addChangeHandler
    );

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

      //should change the acceptance status of a ContributionDesc to 0 (accepted)

    }
  }


  private static final class RejectContributions implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent rejectedClicked)
    {
      //should change the acceptance status of a ContributionDesc to 1(rejected)

    }
  }


  private static final class LikeContribution implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent likedClicked)
    {
      //should increment votes of ContributionDesc

    }
  }


  private static final class DislikeContribution implements ClickHandler
  {
    @Override
    public void onClick(ClickEvent dislikeClicked)
    {
      //should increment votes of ContributionDesc
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