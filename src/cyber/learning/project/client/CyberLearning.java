package cyber.learning.project.client;

import gwtquery.plugins.draggable.client.gwt.DraggableWidget;

import com.google.gwt.core.client.EntryPoint; 
import com.google.gwt.dom.client.Style.Cursor;
import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.youtube.client.YouTubeEmbeddedPlayer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CyberLearning implements EntryPoint {//test comment for test commit
	
	/**
	 * Declare variables
	 */
	String selectedText; //Brian added
	boolean sndOn = false;
	SoundController sController = new SoundController(); //sound stuff - brian
	Sound sound = sController.createSound(Sound.MIME_TYPE_AUDIO_OGG,"https://dl.dropbox.com/u/22130680/testfolder/test.ogg");
	TextArea someText = new TextArea();//moved this from inside onModuleLoad - Brian
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private boolean toolbarVisible = true;
	private boolean resizeEnabled = false;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
	
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("bookContainer");
		RootPanel.get("errorLabelContainer").add(errorLabel);
	
		/* Example code for embedding a YouTube clip.
		 
		   // String corresponds to the alphanumeric ID at the end of YouTube URL. //
		   YouTubeEmbeddedPlayer ytPlayer = new YouTubeEmbeddedPlayer("zn7-fVtT16k");
		   ytPlayer.setWidth("300px");
		   ytPlayer.setHeight("300px");
		   RootPanel.get().add(ytPlayer);
		 */
				
		VerticalPanel flowPanel = new VerticalPanel();
		rootPanel.add(flowPanel, 100, 100);
		flowPanel.setSize("1000px", "600px");
		
		MenuBar menuBar = new MenuBar(false);
		flowPanel.add(menuBar);
		
		MenuItem mntmSearchtext = new MenuItem("Table of Contents", false, (Command) null);
		menuBar.addItem(mntmSearchtext);
		
		HorizontalPanel contentHorizontalPanel = new HorizontalPanel();
		flowPanel.add(contentHorizontalPanel);
		contentHorizontalPanel.setSize("100%", "500px");
		
		final VerticalPanel toolbarPanel = new VerticalPanel();
		contentHorizontalPanel.add(toolbarPanel);
		toolbarPanel.setSize("80px", "144px");
		
		final AbsolutePanel contentPanel = new AbsolutePanel();
		contentHorizontalPanel.add(contentPanel);
		contentPanel.setSize("885px", "600px");
		
		/*
		 * Toolbar items for editing
		 */
		
		//create "Text" item
		final Button newTextArea = new Button("Text Area");
		newTextArea.setSize("90px", "30px");
		toolbarPanel.add(newTextArea);
		//configure as draggable and add to content area
		newTextArea.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event) {
				//create the new draggable object
				final Widget draggableText = createDraggableText();
				RichTextToolbar toolBar = new RichTextToolbar(((DraggableWidget<RichTextArea>) draggableText).getOriginalWidget());
				contentPanel.add(toolBar, 0, 0);
				contentPanel.add(draggableText, 0, 50);
				draggableText.setSize("90%", "75px");
				
				//use "CTRL" key to alternate between drag and resize mode
				newTextArea.addMouseDownHandler(new MouseDownHandler()
				{

					@Override
					public void onMouseDown(MouseDownEvent event) {
						//if "CTRL" key pressed, user resizing widget
						if(event.isControlKeyDown())
						{
							((DraggableWidget<TextArea>) draggableText).setDisabledDrag(true);
						}
					}
				});
				newTextArea.addMouseUpHandler(new MouseUpHandler()
				{
					@Override
					public void onMouseUp(MouseUpEvent event) {
						((DraggableWidget<TextArea>) draggableText).setDisabledDrag(false);
					}
					
				});				
			}	
		});
		
		//create "Image" item
		Button newImage = new Button("Image");
		newImage.setSize("90px", "30px");
		toolbarPanel.add(newImage);
		newImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//configure as draggable and add to toolbar
				Widget draggableImage = createDraggableImage();
				contentPanel.add(draggableImage);
			}		
		});
				
		//create "Video" item
		Button newVideo = new Button("Video");
		newVideo.setSize("90px", "30px");
		toolbarPanel.add(newVideo);
		newVideo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//configure as draggable and add to toolbar
				Widget draggableVideo = createDraggableVideo();
				contentPanel.add(draggableVideo);				
			}	
		});
		
		//Sound Wrapper Stuff - Brian
		Button sndButton = new Button("Test Sound");
		sndButton.setSize("90px", "30px");
		toolbarPanel.add(sndButton);
		sndButton.addClickHandler(new ClickHandler() 
		{
			@Override
			public void onClick(ClickEvent event) {
				Widget draggableSound = createDraggableSound();
				contentPanel.add(draggableSound);
			}			
		});//end sound test stuff
        
		
		//create "Table" item
		//CellTable<Object> cellTable = new CellTable<Object>();
		//Column<Object, ?> col = null;
		//cellTable.addColumn(col);
		//configure as draggable and add to toolbar		
		//Widget draggableTable = createDraggableTable(cellTable);
		//toolbarPanel.add(draggableTable);
		//draggableTable.setSize("90%", "141px");
		
		/*
		 * Content area for reading
		 */
		
		//Highlighting Stuff - Brian
		//Button highlightBtn = new Button ("Highlight Selection");//, new ClickListener()
		/*{
			public void onClick(Widget sender)
			{
				Window.alert("HOW HIGH?");
			}
		});*/
		//toolbarPanel.add(highlightBtn);
		//highlightBtn.setWidth("101px");
		//String testText = someText.getSelectedText();
		//highlightBtn.addClickHandler(new ClickHandler()
		//{
		//public void onClick(ClickEvent event)
		//{
			//someText.getSelectedText().toUpperCase();
			//someText.
			//someText.setReadOnly(true);
			//Window.alert(selectedText);
		//}
		//});
		
		HorizontalPanel editHorizontalPanel_1 = new HorizontalPanel();
		flowPanel.add(editHorizontalPanel_1);
		editHorizontalPanel_1.setWidth("100%");
		
		Button viewToolbarBtn = new Button("New button");
		editHorizontalPanel_1.add(viewToolbarBtn);
		viewToolbarBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				toolbarVisible = !toolbarVisible;
				if(toolbarVisible)
				{
					DOM.setStyleAttribute(toolbarPanel.getElement(), "visibility", "hidden");
					toolbarPanel.setSize("0px", "100%");
					contentPanel.setSize("965px", "100%");
				}
				else
				{
					DOM.setStyleAttribute(toolbarPanel.getElement(), "visibility", "");
					toolbarPanel.setSize("80px", "100%");
					contentPanel.setSize("885px", "100%");					
				}
			}
		});
		viewToolbarBtn.setText("View Toolbar");
	}
	
	Widget createDraggableText()
	{
		//Rich Text Editor - Brian
		RichTextArea textArea = new RichTextArea();
		textArea.setText("\tLorem ipsum dolor sit amet, consectetur adipiscing elit. Ut viverra pulvinar tellus et cursus. Nam viverra dapibus libero et consequat. Aenean imperdiet erat nec risus cursus ac pretium mauris ultrices. Aliquam mollis tellus at nibh bibendum fringilla. Quisque ac purus quis lectus auctor placerat. Maecenas eu lorem sed ligula consequat sagittis. Mauris pulvinar nulla a dolor lobortis vehicula. Quisque in elit vel felis aliquam pulvinar eu et nulla. Phasellus eu nibh velit. Pellentesque porttitor eros id arcu placerat aliquam. Duis eget arcu non magna pretium cursus in et nisl. Praesent sollicitudin pharetra risus hendrerit convallis. Vestibulum bibendum lobortis quam in faucibus. Morbi quis leo nibh. Quisque cursus euismod augue, eu malesuada libero interdum in. Quisque volutpat gravida ligula eget blandit.");		//contentPanel.setWidget(25,0,textArea);
		textArea.setSize("200px","150px");
		//end rich text editor stuff
		
		//configure as draggable
		DraggableWidget<RichTextArea> draggableText = new DraggableWidget<RichTextArea>(textArea);
		draggableText.setDraggingCursor(Cursor.MOVE);
		
		return draggableText;
	}
	
	Widget createDraggableImage()
	{		
		//configure as draggable
		Image img = new Image("cyberlearning/gwt/clean/images/image_icon.PNG");
		DraggableWidget<Image> draggableImage = new DraggableWidget<Image>(img);
		draggableImage.setDraggingCursor(Cursor.MOVE);
		
		return draggableImage;
	}
	
	Widget createDraggableVideo()
	{	 
		// String corresponds to the alphanumeric ID at the end of YouTube URL. //
		AbsolutePanel videoContainer = new AbsolutePanel();
		videoContainer.setSize("300px", "300px");
		DOM.setStyleAttribute(videoContainer.getElement(), "border", "2px solid blue");
		YouTubeEmbeddedPlayer ytPlayer = new YouTubeEmbeddedPlayer("zn7-fVtT16k");
		ytPlayer.setWidth("300px");
		ytPlayer.setHeight("300px");
		videoContainer.add(ytPlayer);
		DraggableWidget<AbsolutePanel> draggableVideoContainer = new DraggableWidget<AbsolutePanel>(videoContainer);
		draggableVideoContainer.setDraggingCursor(Cursor.MOVE);
		
		return draggableVideoContainer;
	}
	
	Widget createDraggableSound()
	{
		Button newSound = new Button("Click to play");
		newSound.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event)
			{
				if(!sndOn)
				{
					sound.play();
				}
				else
				{
					sound.stop();
				}
				sndOn=!sndOn;
			}
		});
		
		DraggableWidget<Button> draggableButton = new DraggableWidget<Button>(newSound);
		draggableButton.setDraggingCursor(Cursor.MOVE);
		
		return draggableButton;
	}
	Widget createDraggableTable(CellTable<Object> table)
	{
		//configure as draggable
		DraggableWidget<CellTable<Object>> draggableTable = new DraggableWidget<CellTable<Object>>(table);
		draggableTable.setDraggingCursor(Cursor.MOVE);
		draggableTable.setDisabledDrag(true);
		
		return draggableTable;		
	}
}
