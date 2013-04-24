package cyber.learning.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

import cyber.learning.project.shared.descs.AccountDesc;
import cyber.learning.project.shared.descs.ComponentDesc;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("deprecation")
public class CyberLearning implements EntryPoint {//test comment

	/**
	 * Declare variables
	 */
	SplitLayoutPanel contentPanel = new SplitLayoutPanel();
//	String selectedText; //Brian added -bkha1
//	boolean sndOn = false;
//	boolean uploadVisible = false;
//	boolean selectTemplateVisible = false;
//	SoundController sController = new SoundController(); //sound stuff - brian - bkha1
//	Sound sound; //= sController.createSound(Sound.MIME_TYPE_AUDIO_OGG,"https://dl.dropbox.com/u/22130680/testfolder/air.ogg");
//	String sndLink;//will contain the url to the sound
//	TextArea sndArea = new TextArea();
//	TextArea someText = new TextArea();//moved this from inside onModuleLoad - Brian - bkha1
//	Image img;
//
//
//	 PopupPanel templateSelectionPopup, positionSelectionPopup;
//	 ArrayList<MySplitPanel> pages = new ArrayList<MySplitPanel>();
//	 MySplitPanel curMySplitPanel = new MySplitPanel();
//	 int currentPage = 0;
//	 Label pageNumLabel;
//
//
//	/**
//	 * The message displayed to the user when the server cannot be reached or
//	 * returns an error.
//	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";
//
//	private boolean toolbarVisible = false;
//	//private boolean resizeEnabled = false;
//	private String uploadFileName = "";
//
//	public enum fileType {
//	  TEXT,
//	  VIDEO,
//		IMAGE,
//		SOUND
//	};
//
//	private enum templateType {
//		TITLE_PAGE,
//		TXT_IMG,
//		TXT_VIDEO,
//		BLANK
//	}

//	String selectedText; // Brian added -bkha1
//	boolean sndOn = false;
//	boolean uploadVisible = false;
//	boolean selectTemplateVisible = false;
//	SoundController sController = new SoundController(); //sound stuff - brian - bkha1
//	Sound sound; //= sController.createSound(Sound.MIME_TYPE_AUDIO_OGG,"https://dl.dropbox.com/u/22130680/testfolder/air.ogg");
//	String sndLink;//will contain the url to the sound
//	TextArea sndArea = new TextArea();
//	TextArea someText = new TextArea();//moved this from inside onModuleLoad - Brian - bkha1
//	Image img;
//
//
//	 PopupPanel templateSelectionPopup, positionSelectionPopup;
//	 ArrayList<MySplitPanel> pages = new ArrayList<MySplitPanel>();
//	 MySplitPanel curMySplitPanel = new MySplitPanel();
//	 int currentPage = 0;
//	 Label pageNumLabel;


	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private boolean toolbarVisible = false;
	//private boolean resizeEnabled = false;
	private String uploadFileName = "";

	public enum fileType {
	  TEXT,
	  VIDEO,
		IMAGE,
		SOUND
	};

	private enum templateType {
		TITLE_PAGE,
		TXT_IMG,
		TXT_VIDEO,
		BLANK
	}


	/**
	 * This is the entry point method.
	 */
	@Override
  public void onModuleLoad() {
	  RootPanel rootPanel = RootPanel.get("bookContainer");
	  rootPanel.setSize("500px", "500px");

	  final HorizontalPanel outer = new HorizontalPanel();
    rootPanel.setSize("500px", "500px");
	  rootPanel.add(outer);
	  //outer.add(loadProposalPane());
	  //outer.add(SearchUI.createSearchUI());
	  System.out.println("Initializing Contributions Panel");
	  outer.add(new ContributionsPanel(new ComponentDesc(1,1,"meh"), new AccountDesc(1,"meh"))); //this is used to test ContributionsPanel.java, comment this out and load another pane if you wish - bkha1
	  //outer.add(new EditablePanel());

	  /*
	  //code to test GWT-RPC
	  final ContributionServiceAsync csa = GWT.create(ContributionService.class);

	  csa.getProposedContributionsFor(new ComponentDesc(1,1,"blah"), new AsyncCallback<CallbackPayload<ContributionDesc[]>>()
	    {

	    @Override
	    public void onSuccess(CallbackPayload<ContributionDesc[]> arg0)
	    {
	      System.out.println("END TEST");//arg0.getResult()[0].getTargetedComponent().getContentValue());
	    }

	    @Override
	    public void onFailure(Throwable arg0)
	    {
	      //TODO Auto generated method stub
	    }

	    });
	    */

	  System.out.println("END PROGRAM");
	}

//	final class EditablePanel extends Composite
//	{
//
//	  public EditablePanel()
//	  {
//	    loadEditablePane();
//	  }
//	  private void loadEditablePane()
//	  {
//
//	    final Label errorLabel = new Label();
//	    final HorizontalPanel editHorizontalPanel_1 = new HorizontalPanel();
//
//	    // Use RootPanel.get() to get the entire body element
//	    RootPanel rootPanel = RootPanel.get("bookContainer");
//	    rootPanel.setSize("1200", "800");
//	    RootPanel.get("errorLabelContainer").add(errorLabel);
//
//	    VerticalPanel flowPanel = new VerticalPanel();
//	    rootPanel.add(flowPanel, 100, 100);
//	    flowPanel.setSize("1000px", "600px");
//
//	    final MenuBar menuBar = new MenuBar(false);
//	    flowPanel.add(menuBar);
//
//	    MenuItem mntmSearchtext = new MenuItem("A Sample freBook", false, (Command) null);
//	    menuBar.addItem(mntmSearchtext);
//	    menuBar.setSize("965px", "100%");
//
//	    HorizontalPanel contentHorizontalPanel = new HorizontalPanel();
//	    flowPanel.add(contentHorizontalPanel);
//	    contentHorizontalPanel.setSize("100%", "500px");
//
//	    final VerticalPanel toolbarPanel = new VerticalPanel();
//	    contentHorizontalPanel.add(toolbarPanel);
//	    toolbarPanel.setSize("80px", "144px");
//
//	    contentHorizontalPanel.add(contentPanel);
//	    contentPanel.setSize("885px", "650px");
//
//	  /********************************************************************************
//	  * Toolbar items for editing
//	  ********************************************************************************/
//
//	    // Popup to allow users to select area for Widget placement
//	    positionSelectionPopup = new PopupPanel();
//	    positionSelectionPopup.setPopupPosition(450, 150);
//
//	    //create "Text" item
//	    final PushButton newTextArea = new PushButton(new Image("cyberlearning/gwt/clean/images/text_icon.png"));
//	    newTextArea.setSize("90px", "80px");
//	    toolbarPanel.add(newTextArea);
//	    newTextArea.addClickHandler(new ClickHandler()
//	    {
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        positionSelectionPopup.clear();
//	        positionSelectionPopup.add(selectWidgetPostion(fileType.TEXT));
//	        positionSelectionPopup.show();
//	      }
//	    });
//
//	    //create "Image" item
//	    PushButton newImage = new PushButton(new Image("cyberlearning/gwt/clean/images/image_icon.PNG"));
//	    newImage.setSize("90px", "90px");
//	    toolbarPanel.add(newImage);
//	    newImage.addClickHandler(new ClickHandler() {
//
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        //allow user to upload image file
//	        if(!uploadVisible)
//	        {
//	          editHorizontalPanel_1.add(uploadNewFile(fileType.IMAGE));
//	          uploadVisible = true;
//	        }
//	      }
//	    });
//
//
//	    //create "Video" item
//	    PushButton newVideo = new PushButton(new Image("cyberlearning/gwt/clean/images/video_icon.png"));
//	    newVideo.setSize("90px", "70px");
//	    toolbarPanel.add(newVideo);
//	    newVideo.addClickHandler(new ClickHandler() {
//
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        positionSelectionPopup.clear();
//	        positionSelectionPopup.add(selectWidgetPostion(fileType.VIDEO));
//	        positionSelectionPopup.show();
//	      }
//	    });
//
//	    //Sound Wrapper Stuff - Brian - bkha1
//	    PushButton sndButton = new PushButton(new Image("cyberlearning/gwt/clean/images/sound_icon.png"));
//	    sndButton.setSize("90px", "90px");
//	    toolbarPanel.add(sndButton);
//
//	    sndButton.addClickHandler(new ClickHandler()
//	    {
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        //allow user to upload sound file
//	        if(!uploadVisible)
//	        {
//	          positionSelectionPopup.clear();
//	          positionSelectionPopup.add(selectWidgetPostion(fileType.SOUND));
//	          positionSelectionPopup.show();
//	        }
//	      }
//	    });
//
//	    // create new "Page"
//	    PushButton pageButton = new PushButton(new Image("cyberlearning/gwt/clean/images/page_icon.png"));
//	    toolbarPanel.add(pageButton);
//	    pageButton.setSize("90px", "90px");
//	    templateSelectionPopup = new PopupPanel();
//	    templateSelectionPopup.add(selectPageTemplate());
//	    templateSelectionPopup.setPopupPosition(450,  150);
//	    pageButton.addClickHandler(new ClickHandler()
//	    {
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        if(!selectTemplateVisible)
//	        {
//	          templateSelectionPopup.show();
//	          selectTemplateVisible = true;
//	        }
//	      }
//	    });
//
//	    Button sndOffButton = new Button("Sound Off");//button for sound off
//	    //toolbarPanel.add(sndOffButton);
//	    sndOffButton.addClickHandler(new ClickHandler(){
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        if(sndOn == true)
//	        {
//	        sound.stop();//turns off song if its playing
//	        sndOn = false;
//	        }
//	      }
//	    });
//
//	    sndArea.setText("http://www.public.asu.edu/~bkha1/air.ogg");//default link
//
//	    //end sound test stuff
//
//	    // Hide toolbar initially
//	    DOM.setStyleAttribute(toolbarPanel.getElement(), "visibility", "hidden");
//
//	  /********************************************************************************
//	   * Content area for reading
//	   ********************************************************************************/
//
//	    //Highlighting Stuff - Brian
//	    //Button highlightBtn = new Button ("Highlight Selection");//, new ClickListener()
//	    /*{
//	      public void onClick(Widget sender)
//	      {
//	        Window.alert("HOW HIGH?");
//	      }
//	    });*/
//	    //toolbarPanel.add(highlightBtn);
//	    //highlightBtn.setWidth("101px");
//	    //String testText = someText.getSelectedText();
//	    //highlightBtn.addClickHandler(new ClickHandler()
//	    //{
//	    //public void onClick(ClickEvent event)
//	    //{
//	      //someText.getSelectedText().toUpperCase();
//	      //someText.
//	      //someText.setReadOnly(true);
//	      //Window.alert(selectedText);
//	    //}
//	    //});
//
//	    flowPanel.add(editHorizontalPanel_1);
//	    editHorizontalPanel_1.setWidth("100%");
//
//	    // Button to show/hide toolbar
//	    Button viewToolbarBtn = new Button("New button");
//	    editHorizontalPanel_1.add(viewToolbarBtn);
//	    viewToolbarBtn.setSize("100px", "40px");
//
//	    // Buttons to enable switching between pages
//	    PushButton scrollLeftButton = new PushButton(new Image("cyberlearning/gwt/clean/images/left_arrow_icon.PNG"));
//	    scrollLeftButton.setSize("75px", "75px");
//	    editHorizontalPanel_1.add(scrollLeftButton);
//	    scrollLeftButton.addClickHandler(new ClickHandler(){
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        if(currentPage > 0)
//	        {
//	          saveExistingPage();
//	            loadExistingPage(currentPage-1);
//	          contentPanel.clear();
//	          loadExistingPage(currentPage-1);
//	        }
//
//	      }
//
//	    });
//
//	    PushButton scrollRightButton = new PushButton(new Image("cyberlearning/gwt/clean/images/right_arrow_icon.PNG"));
//	    scrollRightButton.setSize("75px", "75px");
//	    editHorizontalPanel_1.add(scrollRightButton);
//	    scrollRightButton.addClickHandler(new ClickHandler(){
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        if(currentPage != pages.size()-1)
//	        {
//	          saveExistingPage();
//	            loadExistingPage(currentPage-1);
//	          contentPanel.clear();
//	          loadExistingPage(currentPage+1);
//	        }
//	      }
//
//	    });
//
//	    //editHorizontalPanel_1.add(sndArea);//added these for convenience and sound link testing -bkha
//	    //editHorizontalPanel_1.add(sndOffButton);//bkha1
//
//	    viewToolbarBtn.addClickHandler(new ClickHandler() {
//	      @Override
//	      public void onClick(ClickEvent event) {
//	        if(toolbarVisible)
//	        {
//	          DOM.setStyleAttribute(toolbarPanel.getElement(), "visibility", "hidden");
//	          //toolbarPanel.setSize("0px", "100%");
//	          contentPanel.setSize("885px", "100%");
//	        }
//	        else
//	        {
//	          DOM.setStyleAttribute(toolbarPanel.getElement(), "visibility", "");
//	          //toolbarPanel.setSize("80px", "100%");
//	          //contentPanel.setSize("965px", "100%");
//	        }
//	        toolbarVisible = !toolbarVisible;
//	      }
//	    });
//	    viewToolbarBtn.setText("View Toolbar");
//
//	    // Label to display current page number
//	    pageNumLabel = new Label(" 1");
//	    editHorizontalPanel_1.add(pageNumLabel);
//	  }
//
//	  Widget createDraggableText()
//	  {
//	    //Rich Text Editor - Brian - bkha1
//	    RichTextArea textArea = new RichTextArea();
//	    textArea.setText("\tLorem ipsum dolor sit amet, consectetur adipiscing elit. Ut viverra pulvinar tellus et cursus. Nam viverra dapibus libero et consequat. Aenean imperdiet erat nec risus cursus ac pretium mauris ultrices. Aliquam mollis tellus at nibh bibendum fringilla. Quisque ac purus quis lectus auctor placerat. Maecenas eu lorem sed ligula consequat sagittis. Mauris pulvinar nulla a dolor lobortis vehicula. Quisque in elit vel felis aliquam pulvinar eu et nulla. Phasellus eu nibh velit. Pellentesque porttitor eros id arcu placerat aliquam. Duis eget arcu non magna pretium cursus in et nisl. Praesent sollicitudin pharetra risus hendrerit convallis. Vestibulum bibendum lobortis quam in faucibus. Morbi quis leo nibh. Quisque cursus euismod augue, eu malesuada libero interdum in. Quisque volutpat gravida ligula eget blandit.");   //contentPanel.setWidget(25,0,textArea);
//	    textArea.setSize("200px","150px");
//	    //end rich text editor stuff
//
//	    //configure as draggable
//	    DraggableWidget<RichTextArea> draggableText = new DraggableWidget<RichTextArea>(textArea);
//	    draggableText.setDraggingCursor(Cursor.MOVE);
//
//	    return draggableText;
//	  }
//
//	  Widget createDraggableImage(String iconLocation)
//	  {
//	  //configure as draggable
//	    Image newImg = new Image(iconLocation);
//	    DOM.setStyleAttribute(newImg.getElement(),  "border",  "2px solid blue");
//	    DraggableWidget<Image> draggableImage = new DraggableWidget<Image>(newImg);
//	    draggableImage.setDraggingCursor(Cursor.MOVE);
//	    img = null;
//
//	    return draggableImage;
//	  }
//
//	  Widget createDraggableVideo()
//	  {
//	    AbsolutePanel videoContainer = new AbsolutePanel();
//	    videoContainer.setSize("300px", "300px");
//	    DOM.setStyleAttribute(videoContainer.getElement(), "border", "2px solid blue");
//
//	    //Prompt the user for a specific YouTube video
//	    String alphaId = Window.prompt("Enter alphanumeric ID at the end of YouTube URL:", "");
//
//	    // String corresponds to the alphanumeric ID at the end of YouTube URL. //
//	    //YouTubeEmbeddedPlayer ytPlayer = new YouTubeEmbeddedPlayer("zn7-fVtT16k");
//	    YouTubeEmbeddedPlayer ytPlayer = new YouTubeEmbeddedPlayer(alphaId);
//	    ytPlayer.setWidth("300px");
//	    ytPlayer.setHeight("300px");
//	    videoContainer.add(ytPlayer);
//	    DraggableWidget<AbsolutePanel> draggableVideoContainer = new DraggableWidget<AbsolutePanel>(videoContainer);
//	    draggableVideoContainer.setDraggingCursor(Cursor.MOVE);
//
//	    return draggableVideoContainer;
//	  }
//
//	  //NOTE: dragging sound button seems to be buggy, cant get it to let go once i've clicked on the widget, always follows the cursor - bkha1
//	  Widget createDraggableSound()
//	  {
//
//	    final Image playImg = new Image("cyberlearning/gwt/clean/images/play_icon.png");
//	    final Image stopImg = new Image("cyberlearning/gwt/clean/images/stop_icon.png");
//	    AbsolutePanel soundContainer = new AbsolutePanel();
//	    soundContainer.setSize("52px", "52px");
//	    DOM.setStyleAttribute(soundContainer.getElement(), "border", "2px solid blue");
//	    final PushButton newSound = new PushButton(playImg);
//	    newSound.setSize("50px", "50px");
//	    soundContainer.add(newSound);
//	    final DraggableWidget<AbsolutePanel> draggableButton = new DraggableWidget<AbsolutePanel>(soundContainer);
//	    draggableButton.setDraggingCursor(Cursor.MOVE);
//	    newSound.addDoubleClickHandler(new DoubleClickHandler(){
//	      @Override
//	      public void onDoubleClick(DoubleClickEvent event)
//	      {
//	        draggableButton.setDisabledDrag(true);
//
//	        //changed sound on logic cus toggling doesnt seem to work well enough - bkha1
//	        if(!sndOn)
//	        {
//	          sndLink = sndArea.getText();//grabs link from textbox
//	          sound = sController.createSound(Sound.MIME_TYPE_AUDIO_OGG,sndLink);//sets up the sound
//	          sound.setLooping(true);
//	          //sound.play();
//	          if(sound.play() == true)//plays sound
//	          {
//	          sndOn = true;
//	          }
//	        }
//	        /*
//	        if(!sndOn)
//	        {
//	          sound.play();
//	          newSound.getUpFace().setImage(stopImg);
//	        }
//	        else
//	        {
//	          sound.stop();
//	          newSound.getUpFace().setImage(playImg);
//	        }
//	        sndOn=!sndOn;
//	        */
//
//	        draggableButton.setDisabledDrag(false);
//	      }
//
//	    });
//
//	    return draggableButton;
//	  }
//
//	  /********************************************************************************
//	   * Helper functions for saving, loading, and creating new pages
//	   ********************************************************************************/
//
//	  Widget addToEditPane(fileType type)
//	  {
//	    AbsolutePanel widgetPanel = new AbsolutePanel();
//	      switch(type)
//	      {
//	          case TEXT:
//	              //configure as draggable and add to Edit Pane
//	          final Widget draggableText = createDraggableText();
//	          @SuppressWarnings("unchecked")
//	          RichTextToolbar toolBar = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText).getOriginalWidget());
//	          draggableText.setSize("95%", "95%");
//	          widgetPanel.add(toolBar);
//	          widgetPanel.add(draggableText);
//	          //use "CTRL" key to alternate between drag and resize mode
//	          /*newTextArea.addMouseDownHandler(new MouseDownHandler()
//	          {
//
//	            @Override
//	            public void onMouseDown(MouseDownEvent event) {
//	              //if "CTRL" key pressed, user resizing widget
//	              if(event.isControlKeyDown())
//	              {
//	                ((DraggableWidget<TextArea>) draggableText).setDisabledDrag(true);
//	              }
//	            }
//	          });
//	          newTextArea.addMouseUpHandler(new MouseUpHandler()
//	          {
//	            @Override
//	            public void onMouseUp(MouseUpEvent event) {
//	              ((DraggableWidget<TextArea>) draggableText).setDisabledDrag(false);
//	            }
//
//	          }); */
//	          break;
//
//	          case IMAGE:
//	          //configure as draggable and add to Edit Pane
//	              Widget draggableImage = createDraggableImage(img.getUrl());
//	              draggableImage.setSize("95%", "95%");
//	              widgetPanel.add(draggableImage);
//	          break;
//
//	          case VIDEO:
//	              //configure as draggable and add to Edit Pane
//	          Widget draggableVideo = createDraggableVideo();
//	          draggableVideo.setSize("95%", "95%");
//	          widgetPanel.add(draggableVideo);
//	          break;
//
//	          case SOUND:
//	              //configure as draggable and add to Edit Pane
//	              Widget draggableSound = createDraggableSound();
//	              draggableSound.setSize("30px", "30px");
//	              widgetPanel.add(draggableSound);
//	          break;
//
//	        default:
//	          break;
//	      }
//	      return widgetPanel;
//	  }
//
//	  Widget createDraggableTable(CellTable<Object> table)
//	  {
//	  //configure as draggable
//	    DraggableWidget<CellTable<Object>> draggableTable = new DraggableWidget<CellTable<Object>>(table);
//	    draggableTable.setDraggingCursor(Cursor.MOVE);
//	    draggableTable.setDisabledDrag(true);
//
//	    return draggableTable;
//	  }
//
//	  @SuppressWarnings("deprecation")
//	  public FormPanel uploadNewFile(final fileType f)
//	  {
//	  //Prompt the user for a specific sound
//	    final FormPanel uploadForm = new FormPanel();
//	    uploadForm.setAction(GWT.getModuleBaseURL() + "upload");
//	    // Because we're going to add a FileUpload widget, we'll need to set the
//	      // form to use the POST method, and multipart MIME encoding.
//	    uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
//	    uploadForm.setMethod(FormPanel.METHOD_POST);
//
//	      // Create a panel to hold all of the form widgets.
//	      VerticalPanel panel = new VerticalPanel();
//	      uploadForm.setWidget(panel);
//
//	      // Create a FileUpload widget.
//	      final FileUpload upload = new FileUpload();
//	      upload.setName("uploadFormElement");
//	      panel.add(upload);
//
//	      // Add a 'submit' button.
//	      panel.add(new Button("Submit", new ClickListener() {
//	        @Override
//	        public void onClick(Widget sender) {
//	          uploadForm.submit();
//	        }
//	      }));
//
//	      // Add an event handler to the form.
//	      uploadForm.addFormHandler(new FormHandler() {
//	        @Override
//	        public void onSubmit(FormSubmitEvent event) {
//	            System.out.println(upload.getFilename());
//	            uploadFileName = "cyberlearning/gwt/clean/uploads/" + upload.getFilename();
//	            if(f == fileType.IMAGE)
//	            {
//	              img = new Image(uploadFileName);
//	          positionSelectionPopup.clear();
//	          positionSelectionPopup.add(selectWidgetPostion(fileType.IMAGE));
//	          positionSelectionPopup.show();
//	            }
//	            else
//	            {
//	              //insert button to play user's sound
//	              sound = sController.createSound(Sound.MIME_TYPE_AUDIO_OGG,"https://dl.dropbox.com/u/22130680/testfolder/air.ogg");
//	          positionSelectionPopup.clear();
//	          positionSelectionPopup.add(selectWidgetPostion(fileType.SOUND));
//	          positionSelectionPopup.show();
//
//	              //sound = sController.createSound(Sound.MIME_TYPE_AUDIO_MP4, uploadFileName);
//	            }
//	        uploadFileName.equals("");
//	        }
//
//	        @Override
//	        public void onSubmitComplete(FormSubmitCompleteEvent event) {
//	          // When the form submission is successfully completed, this event is
//	          // fired. Assuming the service returned a response of type text/html,
//	          // we can get the result text here (see the FormPanel documentation for
//	          // further explanation).
//	          Window.alert(event.getResults());
//	        }
//	      });
//	      return uploadForm;
//	  }
//
//	  @SuppressWarnings("deprecation")
//	  public FormPanel selectPageTemplate()
//	  {
//	  // Create our main form
//	    final FormPanel templateSelectionForm = new FormPanel();
//
//	      // Create a panel to hold all of the form widgets.
//	      VerticalPanel panel = new VerticalPanel();
//	      HorizontalPanel topRowPanel = new HorizontalPanel();
//	      HorizontalPanel bottomRowPanel = new HorizontalPanel();
//	      panel.add(topRowPanel);
//	      panel.add(bottomRowPanel);
//	      templateSelectionForm.setWidget(panel);
//
//	      // Create a PushButton for each predefined template.
//	      topRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/title_template.PNG"), new ClickListener() {
//	        @Override
//	        public void onClick(Widget sender) {
//	         addNewPage(templateType.TITLE_PAGE);
//	        }
//	      }));
//
//	      topRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/template_1.PNG"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            addNewPage(templateType.TXT_IMG);
//	          }
//	        }));
//
//	      bottomRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/template_2.PNG"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            addNewPage(templateType.TXT_VIDEO);
//	          }
//	        }));
//
//	      bottomRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/blank_page.PNG"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            addNewPage(templateType.BLANK);
//	          }
//	        }));
//
//	      return templateSelectionForm;
//	  }
//
//	  public FormPanel selectWidgetPostion(final fileType type)
//	  {
//	    // Create our main form
//	    final FormPanel widgetPositionForm = new FormPanel();
//
//	      // Create a panel to hold all of the form widgets.
//	      VerticalPanel panel = new VerticalPanel();
//	      HorizontalPanel topRowPanel = new HorizontalPanel();
//	      HorizontalPanel middleRowPanel = new HorizontalPanel();
//	      HorizontalPanel bottomRowPanel = new HorizontalPanel();
//	      panel.add(topRowPanel);
//	      panel.add(middleRowPanel);
//	      panel.add(bottomRowPanel);
//	      widgetPositionForm.setWidget(panel);
//
//	      // Create a PushButton for each predefined template.
//	      topRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/north.png"), new ClickListener() {
//	        @Override
//	        public void onClick(Widget sender) {
//	          AbsolutePanel widgetPanel = (AbsolutePanel) addToEditPane(type);
//
//	          curMySplitPanel.north.add(widgetPanel);
//	          widgetPanel.setSize("95%", "95%");
//	        positionSelectionPopup.hide();
//	          selectTemplateVisible = false;
//	          saveExistingPage();
//	          loadExistingPage(currentPage-1);
//	        }
//	      }));
//
//	      middleRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/west.png"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            AbsolutePanel widgetPanel = (AbsolutePanel) addToEditPane(type);
//
//	            curMySplitPanel.west.add(widgetPanel);
//	            widgetPanel.setSize("95%", "95%");
//	          positionSelectionPopup.hide();
//	            selectTemplateVisible = false;
//	            saveExistingPage();
//	            loadExistingPage(currentPage-1);
//	          }
//	        }));
//
//	      middleRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/east.png"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            AbsolutePanel widgetPanel = (AbsolutePanel) addToEditPane(type);
//
//	            curMySplitPanel.east.add(widgetPanel);
//	            widgetPanel.setSize("95%", "95%");
//	          positionSelectionPopup.hide();
//	            selectTemplateVisible = false;
//	            saveExistingPage();
//	            loadExistingPage(currentPage-1);
//	          }
//	        }));
//
//	      bottomRowPanel.add(new PushButton(new Image("cyberlearning/gwt/clean/images/south.png"), new ClickListener() {
//	          @Override
//	          public void onClick(Widget sender) {
//	            AbsolutePanel widgetPanel = (AbsolutePanel) addToEditPane(type);
//
//	            curMySplitPanel.south.add(widgetPanel);
//	            widgetPanel.setSize("95%", "95%");
//	          positionSelectionPopup.hide();
//	            selectTemplateVisible = false;
//	            saveExistingPage();
//	            loadExistingPage(currentPage-1);
//	          }
//	        }));
//
//	      return widgetPositionForm;
//	  }
//
//
//	  @SuppressWarnings("unchecked")
//	  public void addNewPage(templateType template)
//	  {
//	    contentPanel.clear();
//	    saveExistingPage();
//	    switch(template) {
//	      case TITLE_PAGE:
//	        templateSelectionPopup.hide();
//
//	        // title text
//	        Widget draggableText1 = createDraggableText();
//	        RichTextToolbar toolBar1 = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText1).getOriginalWidget());
//	        draggableText1.setSize("95%", "95%");
//	        // title image
//	        Widget draggableImage1 = createDraggableImage("cyberlearning/gwt/clean/images/bookworm.jpg");
//
//	        curMySplitPanel.north.add(toolBar1);
//	        curMySplitPanel.north.add(draggableText1);
//	        curMySplitPanel.south.add(draggableImage1);
//	        break;
//
//	      case TXT_IMG:
//	        templateSelectionPopup.hide();
//
//	        VerticalPanel westPanel = new VerticalPanel();
//	        westPanel.setSize("100%", "100%");
//	        // text
//	        Widget draggableText2 = createDraggableText();
//	        RichTextToolbar toolBar2 = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText2).getOriginalWidget());
//	        westPanel.add(toolBar2);
//	        westPanel.add(draggableText2);
//	        draggableText2.setSize("95%", "50%");
//	        // image
//	        Widget draggableImage2 = createDraggableImage("cyberlearning/gwt/clean/images/bookworm.jpg");
//	        draggableImage2.setSize("95%", "50%");
//	        westPanel.add(draggableImage2);
//	        westPanel.setSize("95%", "95%");
//
//	        VerticalPanel eastPanel = new VerticalPanel();
//	        eastPanel.setSize("100%", "100%");
//	        // text
//	        Widget draggableText3 = createDraggableText();
//	        RichTextToolbar toolBar3 = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText3).getOriginalWidget());
//	        eastPanel.add(toolBar3);
//	        eastPanel.add(draggableText3);
//	        draggableText3.setSize("95%", "95%");
//	        draggableText3.setSize("95%", "95%");
//	        eastPanel.setSize("95%", "95%");
//
//	        curMySplitPanel.west.add(draggableText2);
//	        curMySplitPanel.west.add(draggableImage2);
//	        curMySplitPanel.east.add(draggableText3);
//	        break;
//
//	      case TXT_VIDEO:
//	        templateSelectionPopup.hide();
//
//	        VerticalPanel westPanel2 = new VerticalPanel();
//	        westPanel2.setSize("100%", "100%");
//	        // text
//	        Widget draggableText4 = createDraggableText();
//	        RichTextToolbar toolBar4 = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText4).getOriginalWidget());
//	        westPanel2.add(toolBar4);
//	        westPanel2.add(draggableText4);
//	        draggableText4.setSize("95%", "50%");
//	        // video
//	        Widget draggableVideo1 = createDraggableVideo();
//	        draggableVideo1.setSize("95%", "50%");
//	        westPanel2.add(draggableVideo1);
//	        westPanel2.setSize("95%", "95%");
//
//	        VerticalPanel eastPanel2 = new VerticalPanel();
//	        eastPanel2.setSize("100%", "100%");
//	        // text
//	        Widget draggableText5 = createDraggableText();
//	        RichTextToolbar toolBar5 = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText5).getOriginalWidget());
//	        eastPanel2.add(toolBar5);
//	        eastPanel2.add(draggableText5);
//	        draggableText5.setSize("95%", "95%");
//	        draggableText5.setSize("95%", "95%");
//	        eastPanel2.setSize("95%", "95%");
//
//	        curMySplitPanel.west.add(draggableText4);
//	        curMySplitPanel.west.add(draggableVideo1);
//	        curMySplitPanel.east.add(draggableText5);
//	        break;
//
//	      case BLANK:
//	        templateSelectionPopup.hide();
//	        break;
//	    }
//	    saveExistingPage();
//	    loadExistingPage(currentPage-1);
//	    selectTemplateVisible = false;
//	  }
//
//	  public void loadExistingPage(int pageNum)
//	  {
//	    contentPanel.clear();
//	    curMySplitPanel = pages.get(pageNum);
//	    if(curMySplitPanel.north.getWidgetCount() != 0)
//	    {
//	      contentPanel.addNorth(curMySplitPanel.north, 200);
//	      curMySplitPanel.north.setSize("95%", "95%");
//	    }
//	    if(curMySplitPanel.south.getWidgetCount() != 0)
//	    {
//	      contentPanel.addSouth(curMySplitPanel.south, 200);
//	      curMySplitPanel.south.setSize("95%", "95%");
//	    }
//	    if(curMySplitPanel.east.getWidgetCount() != 0)
//	    {
//	      contentPanel.addEast(curMySplitPanel.east, 200);
//	      curMySplitPanel.east.setSize("95%", "95%");
//	    }
//	    if(curMySplitPanel.west.getWidgetCount() != 0)
//	    {
//	      contentPanel.addWest(curMySplitPanel.west, 200);
//	      curMySplitPanel.west.setSize("95%", "95%");
//	    }
//	    currentPage = pageNum;
//	    pageNumLabel.setText("" + (pageNum+1));
//	  }
//
//	  public void saveExistingPage()
//	  {
//	    if(curMySplitPanel != null)
//	    {
//	      try
//	      {
//	        pages.set(currentPage, curMySplitPanel);
//	      }
//	      catch (Exception e)
//	      {
//	        pages.add(currentPage, curMySplitPanel);
//	      }
//	    }
//	    currentPage++;
//	    pageNumLabel.setText("" + (currentPage+1));
//	    curMySplitPanel = new MySplitPanel();
//	    // TODO Create persistent data - save to database
//	  }
//	}




}
