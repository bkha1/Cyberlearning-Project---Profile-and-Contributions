package cyber.learning.project.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.google.gwt.core.shared.GWT;
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

import cyber.learning.project.client.services.ContributionService;
import cyber.learning.project.client.services.ContributionServiceAsync;
import cyber.learning.project.shared.CallbackPayload;
import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;
import cyber.learning.project.shared.descs.ContributionDesc;


final class ContributionsPanel extends TabPanel
{
  private final ComponentDesc component_;
  private final AccountDesc editor_;
  //private ContributionDesc selectedItem = new ContributionDesc();

  private ArrayList<ContributionDesc> testList = new ArrayList<ContributionDesc>();
  private ArrayList<ContributionDesc> acceptedList = new ArrayList<ContributionDesc>();
  private ArrayList<ContributionDesc> rejectedList = new ArrayList<ContributionDesc>();

  final ContributionServiceAsync svc = GWT.create(ContributionService.class);

  public ContributionsPanel(ComponentDesc component, AccountDesc editor)
  {

    setSize("1000px", "500px");

    final AsyncCallback<CallbackPayload<ContributionDesc[]>> callback =
      new AsyncCallback<CallbackPayload<ContributionDesc[]>>()
      {

        @Override
        public void onFailure(Throwable arg0) {
          // TODO Auto-generated method stub

        }

        @Override
        public void onSuccess(CallbackPayload<ContributionDesc[]> payload)
        {

          if(payload.hasResult())
          {
            //payload.getResult();
            //new ArrayList(Arrays.asList(myArray));
            testList = new ArrayList<ContributionDesc>(Arrays.asList(payload.getResult()));
            //acceptedList = new ArrayList<ContributionDesc>(Arrays.asList(payload.getResult()));
            //populatePendingPanelWith(pending);
          }
          else
          {
            //error processing goes here
          }

        }

      };

    //final ContributionServiceAsync svc = GWT.create(ContributionService.class);
    svc.getProposedContributionsFor(component, callback);
    svc.getHistoricalContributionsFor(component, true, callback);
    svc.getHistoricalContributionsFor(component, false, callback);

    /*
    //dummy accounts
    AccountDesc testEditor1 = new AccountDesc(1, "testUser1");
    AccountDesc testEditor2 = new AccountDesc(2, "testUser2");
    AccountDesc testEditor3 = new AccountDesc(3, "testUser3");
    AccountDesc testEditor4 = new AccountDesc(3, "testUser4");

    //dummy components
    ComponentDesc testComp1 = new ComponentDesc(1, 2, "Monkey Island 2: LeChuck's Revenge is an adventure game developed and published by LucasArts in 1991. It was the second game of the Monkey Island series, following The Secret of Monkey Island, and the sixth LucasArts game to use the SCUMM engine. It was the first game to use the iMUSE sound system.");//text type
    ComponentDesc testComp2 = new ComponentDesc(2, 2, "Psychonauts is a platform video game created by Tim Schafer, developed by Double Fine Productions and published by Majesco, starring the voice of Richard Horvitz as Raz. The game was released on April 19, 2005, for the Xbox, April 26 for Microsoft Windows and June 21 for PlayStation 2. It was released on Steam on Oct 11, 2006, as an Xbox Original through Xbox Live Marketplace, and on the GameTap subscription service.[1] On November 5, 2009, Psychonauts also became available through the online distribution service GOG.com through their partnership with Majesco. In September 2011, a new version with support for Steamworks features, including a Mac OS X port, was released. In May 2012, a Linux port was released through the Humble Indie Bundle V. The PS2 version was re-released on PlayStation Network on August 28, 2012.");
    ComponentDesc testComp3 = new ComponentDesc(3, 2, "Grim Fandango is a dark comedy neo-noir Windows adventure game released by LucasArts in 1998, primarily written by Tim Schafer. It is the first adventure game by LucasArts to use 3D computer graphics overlaid on pre-rendered, static backgrounds. As with other LucasArts adventure games, the player must converse with other characters and examine, collect, and use objects correctly to solve puzzles in order to progress.");
    ComponentDesc testComp4 = new ComponentDesc(4, 2, "Day of the Tentacle, also known as Maniac Mansion II: Day of the Tentacle,[1][2] is a 1993 graphic adventure game developed and published by LucasArts. It is the sequel to the 1987 game Maniac Mansion. The game's plot follows Bernard Bernoulli and his friends Hoagie and Laverne as they attempt to stop the evil Purple Tentacle, a sentient, disembodied tentacle, from taking over the world. The player takes control of the three and solves puzzles while using time travel to explore different periods of history.");

    //dummy contributionDescs for testing
    ContributionDesc testContribution1 = new ContributionDesc(101, testComp1, testComp1, testEditor1, "It's a rubber chicken with a pulley in the middle", new Date(System.currentTimeMillis()), 1, 0);
    ContributionDesc testContribution2 = new ContributionDesc(102, testComp2, testComp2, testEditor2, "How about some delicious turtle soup??", new Date(System.currentTimeMillis()),2,0);
    ContributionDesc testContribution3 = new ContributionDesc(103, testComp3, testComp3, testEditor3, "RUN! IT'S ROBERT FROST!", new Date(System.currentTimeMillis()),3,0);
    ContributionDesc testContribution4 = new ContributionDesc(104, testComp4, testComp4, testEditor4, "Well, what possible harm could one insane, mutant tentacle do?", new Date(System.currentTimeMillis()),4,0);
    */

    /*
    ArrayList<ContributionDesc> testList = new ArrayList<ContributionDesc>();
    testList.add(testContribution1);
    testList.add(testContribution2);
    testList.add(testContribution3);
    testList.add(testContribution4);
    */

    //ArrayList<ContributionDesc> testAcceptedList = new ArrayList<ContributionDesc>();
    //ArrayList<ContributionDesc> testRejectedList = new ArrayList<ContributionDesc>();



    //add(getLoadingPanel(), "Pending");
    add(getPendingPanel(testList), "Pending");

    //add(getLoadingPanel(), "Accepted");
    add(getHistoricalPanel(acceptedList), "Accepted");

    //add(getLoadingPanel(), "Rejected");
    add(getHistoricalPanel(rejectedList), "Rejected");



    selectTab(0);
    addSelectionHandler(new LoadPanel(this));

    /*
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

      };*/

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

  ContributionDesc item = new ContributionDesc();

  @SuppressWarnings("unused")
  private Panel getPendingPanel(final Iterable<ContributionDesc> contributions) //final Iterable<ContributionDesc> acceptedList, final Iterable<ContributionDesc> rejectedList)//TODO: add two more iterable arguments for accepted and rejected contributions
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

    final HorizontalPanel commentAndVotePanel = new HorizontalPanel();
    final TextBox changeLogTextBox = new TextBox(); //changeLogTextBox will hold the user comments
    changeLogTextBox.setSize("550px", "50px");
    commentAndVotePanel.add(changeLogTextBox);
    final TextBox nameTextBox = new TextBox(); //nameTextBox will hold the username
    nameTextBox.setSize("90px", "50px");
    commentAndVotePanel.add(nameTextBox);
    final TextBox voteTextBox = new TextBox(); //voteTextBox will hold the number of votes the contribution has
    voteTextBox.setSize("80px", "50px");
    commentAndVotePanel.add(voteTextBox);
    previewCommentAndControlPanel.add(commentAndVotePanel);//.add(changeLogTextBox);

    //ContributionDesc selectedItem;
    //ContributionDesc item = new ContributionDesc();

    //add contributions stuff to the pending list, assigns contribution's unique id to the value of the item in the list
    for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
    {
      //ContributionDesc item = i.next();
      item = i.next();
      pendingList.addItem(item.getContributionTime().toString() + " - " + item.getContributor().getUsername() + " - id: " + item.getID(), "" + item.getID());
    }

    //gets the selected item by comparing the ContributionDesc ID and the selected item in pending List's value and display the contents and comments on the side
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
            item = i.next();

            if(item.getID() == Integer.parseInt(pendingList.getValue(selectedIndex)))
            {
              //selectedItem = item;
              previewArea.setText(item.getTargetedComponent().getContentValue());//get component's value
              changeLogTextBox.setText(item.getChangeComment());//sets comment box
              nameTextBox.setText(item.getContributor().getUsername());
              voteTextBox.setText(item.getVotes() + " Votes");
              break;
            }
          }//end for loop
        }//end if statement
      }//end onChange
    }//end addChangeHandler
    );

    /*
    previewArea.setText(item.getTargetedComponent().getContentValue());//get component's value
    changeLogTextBox.setText(item.getChangeComment());//sets comment box
    voteTextBox.setText(item.getVotes() + " Votes");*/

    //previewArea.
    //changeLogTextBox.setReadOnly(true);
    //voteTextBox.setReadOnly(true);

    final HorizontalPanel controlPanel = new HorizontalPanel();
    previewCommentAndControlPanel.add(controlPanel);
    controlPanel.setSize("750px", "25px");

    final Button acceptButton = new Button("Accept");
    acceptButton.addClickHandler(new ClickHandler()//clickhandler for the accept button
    {
      @Override
      public void onClick(ClickEvent event)
      {
        changeLogTextBox.setText("I AM CLICKING ON THE ACCEPT BUTTON");

        int selectedIndex = pendingList.getSelectedIndex();

        //takes the selected item and moves it to the accepted tab
        if(selectedIndex > -1)
        {
          for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
          {
            item = i.next();

            if(item.getID() == Integer.parseInt(pendingList.getValue(selectedIndex)))
            {
              acceptedList.add(item);
              pendingList.removeItem(selectedIndex);
              i.remove();
              previewArea.setText("");
              changeLogTextBox.setText("");
              nameTextBox.setText("");
              voteTextBox.setText("");
              break;
            }
          }//end for loop
        }//end if statement

        //takes the rest of the items in pending and moves them to the rejected tab
        for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
        {
          item = i.next();

          rejectedList.add(item);
          pendingList.removeItem(0);
          i.remove();
        }//end for loop

        //refresh panels
        remove(2);
        remove(1);
        add(getHistoricalPanel(acceptedList), "Accepted");
        add(getHistoricalPanel(rejectedList), "Rejected");
        //svc.updateContributionsAcceptance(changeRequests, callback)
        //ContributionDesc[] bar = acceptedList.toArray(new ContributionDesc[0]);
        //bar[0] = new UpdateContributionChangeRequest(true, 1, bar[2]);

      }
    }
    );

    final Button rejectButton = new Button("Reject");
    rejectButton.addClickHandler(new ClickHandler()//clickhandler for the reject button
    {
      @Override
      public void onClick(ClickEvent event)
      {
        changeLogTextBox.setText("I AM CLICKING ON THE REJECT BUTTON");

        int selectedIndex = pendingList.getSelectedIndex();

        if(selectedIndex > -1)
        {
          for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
          {
            item = i.next();

            if(item.getID() == Integer.parseInt(pendingList.getValue(selectedIndex)))
            {
              rejectedList.add(item);
              pendingList.removeItem(selectedIndex);
              i.remove();
              previewArea.setText("");
              changeLogTextBox.setText("");
              nameTextBox.setText("");
              voteTextBox.setText("");
              break;
            }
          }//end for loop
        }//end if statement

      //refresh panels
        remove(2);
        remove(1);
        add(getHistoricalPanel(acceptedList), "Accepted");
        add(getHistoricalPanel(rejectedList), "Rejected");
      }
    }
    );

    final Button upVoteButton = new Button("UpVote");
    upVoteButton.addClickHandler(new ClickHandler()//clickhandler for the upvote button
    {
      @Override
      public void onClick(ClickEvent event)
      {
        changeLogTextBox.setText("I AM CLICKING ON THE UPVOTE BUTTON");
        voteTextBox.setText(item.getVotes() + 1 + " Votes");
        //TODO: make this permanent
      }
    }
    );

    final Button downVoteButton = new Button("DownVote");
    downVoteButton.addClickHandler(new ClickHandler()//clickhandler for the downvote button
    {
      @Override
      public void onClick(ClickEvent event)
      {
        changeLogTextBox.setText("I AM CLICKING ON THE DOWNVOTE BUTTON");
        voteTextBox.setText(item.getVotes() - 1 + " Votes");
        //TODO: make this permanent
      }
    }
    );

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


  private Panel getHistoricalPanel(final Iterable<ContributionDesc> contributions)
  {

    final HorizontalPanel historicalPanel = new HorizontalPanel();

    historicalPanel.setSize("1000px", "500px");
    final ListBox pendingList = new ListBox(true);



    historicalPanel.add(pendingList);
    pendingList.setSize("250px", "500px");

    final VerticalPanel previewCommentAndControlPanel = new VerticalPanel();
    historicalPanel.add(previewCommentAndControlPanel);
    final RichTextArea previewArea = new RichTextArea();
    previewCommentAndControlPanel.add(previewArea);
    previewArea.setSize("750px", "400px");

    final HorizontalPanel commentAndVotePanel = new HorizontalPanel();
    final TextBox changeLogTextBox = new TextBox(); //changeLogTextBox will hold the user comments
    changeLogTextBox.setSize("550px", "50px");
    commentAndVotePanel.add(changeLogTextBox);
    final TextBox nameTextBox = new TextBox(); //nameTextBox will hold the username
    nameTextBox.setSize("90px", "50px");
    commentAndVotePanel.add(nameTextBox);
    final TextBox voteTextBox = new TextBox(); //voteTextBox will hold the number of votes the contribution has
    voteTextBox.setSize("80px", "50px");
    commentAndVotePanel.add(voteTextBox);
    previewCommentAndControlPanel.add(commentAndVotePanel);

  //add contributions stuff to the pending list, assigns contribution's unique id to the value of the item in the list
    for(Iterator<ContributionDesc> i = contributions.iterator(); i.hasNext();)
    {
      //ContributionDesc item = i.next();
      item = i.next();
      pendingList.addItem(item.getContributionTime().toString() + " - " + item.getContributor().getUsername() + " - id: " + item.getID(), "" + item.getID());
    }

    //gets the selected item by comparing the ContributionDesc ID and the selected item in pending List's value and display the contents and comments on the side
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
            item = i.next();

            if(item.getID() == Integer.parseInt(pendingList.getValue(selectedIndex)))
            {
              //selectedItem = item;
              previewArea.setText(item.getTargetedComponent().getContentValue());//get component's value
              changeLogTextBox.setText(item.getChangeComment());//sets comment box
              nameTextBox.setText(item.getContributor().getUsername());
              voteTextBox.setText(item.getVotes() + " Votes");
              break;
            }
          }//end for loop
        }//end if statement
      }//end onChange
    }//end addChangeHandler
    );

    final HorizontalPanel controlPanel = new HorizontalPanel();
    previewCommentAndControlPanel.add(controlPanel);
    final TextBox extraTextBox = new TextBox();
    extraTextBox.setSize("740px", "20px");
    controlPanel.add(extraTextBox);

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