package cyber.learning.project.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Image;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CyberLearning implements EntryPoint {
	
	/**
	 * Declare variables
	 */
	boolean toolbarVisible = false;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("bookContainer");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		VerticalPanel flowPanel = new VerticalPanel();
		rootPanel.add(flowPanel, 100, 100);
		flowPanel.setSize("1000px", "600px");
		
		MenuBar menuBar = new MenuBar(false);
		flowPanel.add(menuBar);
		
		MenuItem mntmSearchtext = new MenuItem("Table of Contents", false, (Command) null);
		menuBar.addItem(mntmSearchtext);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		flowPanel.add(horizontalPanel);
		horizontalPanel.setSize("100%", "500px");
		
		final VerticalPanel toolbarPanel = new VerticalPanel();
		horizontalPanel.add(toolbarPanel);
		toolbarPanel.setSize("80px", "144px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("Button");
		toolbarPanel.add(btnNewButton);
		btnNewButton.setWidth("101px");
		
		TextArea txtText = new TextArea();
		txtText.setText("Text");		
		toolbarPanel.add(txtText);
		txtText.setWidth("100%");
		
		Image image = new Image("cyberlearning/gwt/clean/images/image_icon.PNG");
		toolbarPanel.add(image);
		image.setSize("92px", "80px");
		
		final FlexTable contentPanel = new FlexTable();
		horizontalPanel.add(contentPanel);
		contentPanel.setSize("885px", "100%");
		
		TextArea someText = new TextArea();
		someText.setText("     Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut viverra pulvinar tellus et cursus. Nam viverra dapibus libero et consequat. Aenean imperdiet erat nec risus cursus ac pretium mauris ultrices. Aliquam mollis tellus at nibh bibendum fringilla. Quisque ac purus quis lectus auctor placerat. Maecenas eu lorem sed ligula consequat sagittis. Mauris pulvinar nulla a dolor lobortis vehicula. Quisque in elit vel felis aliquam pulvinar eu et nulla. Phasellus eu nibh velit. Pellentesque porttitor eros id arcu placerat aliquam. Duis eget arcu non magna pretium cursus in et nisl. Praesent sollicitudin pharetra risus hendrerit convallis. Vestibulum bibendum lobortis quam in faucibus. Morbi quis leo nibh. Quisque cursus euismod augue, eu malesuada libero interdum in. Quisque volutpat gravida ligula eget blandit.\r\n\r\n     Morbi sed fringilla erat. Suspendisse ut risus felis. Sed dolor nisi, feugiat non auctor vel, dictum vitae arcu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean vel facilisis orci. Pellentesque in massa enim. Nunc facilisis rutrum laoreet.\r\n\r\n     Curabitur sollicitudin, ligula et porta tincidunt, nisl erat tempor nulla, et lobortis justo ligula in tellus. Donec nisi lectus, rutrum vel feugiat eu, aliquet eget lorem. Aenean eu ligula a justo varius sagittis ornare mattis felis. Curabitur id sapien massa. Vivamus facilisis viverra quam, sed blandit justo suscipit vel. Integer varius justo vel mauris dignissim imperdiet. Donec laoreet luctus adipiscing. Nam sit amet odio mauris. Ut sit amet mauris sit amet velit adipiscing elementum. Phasellus sed felis ac lacus aliquet interdum. Proin nulla turpis, imperdiet quis ultricies id, vehicula et ante. Phasellus tempor dolor eu ipsum laoreet feugiat. Vestibulum et mi eu neque volutpat placerat non quis lectus. Cras fringilla tempor sapien, sed pretium lorem condimentum nec. Nunc diam nisi, scelerisque a mollis ut, posuere sit amet mi. Sed aliquet tincidunt pharetra.\r\n\r\n     Vivamus varius arcu nulla. Pellentesque ut turpis non ante porttitor aliquet tempor vel libero. Vivamus massa ipsum, condimentum non suscipit sed, rutrum sed lorem. Praesent sit amet enim quam, vulputate auctor augue. Nunc lacinia consectetur odio et suscipit. Sed consequat feugiat diam, vel pharetra lectus pellentesque eget. Nam blandit, velit a varius eleifend, ipsum nibh commodo risus, eu ultricies turpis eros vel leo. In mattis ornare dolor, at mattis lacus feugiat a.\r\n\r\n     Curabitur fermentum pharetra purus, a tristique massa semper quis. Duis ut orci elit. Praesent posuere, sem sed mattis venenatis, turpis odio ullamcorper quam, vel hendrerit felis erat vulputate diam. Quisque sed justo eu mauris bibendum vehicula ac ac felis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Praesent lectus tellus, rutrum eu vehicula sit amet, egestas eu mi. Proin eget nisl sed nulla tempor accumsan. Nam adipiscing fermentum felis, quis porta arcu lacinia at. Fusce semper vehicula ligula, sed fermentum est accumsan ac. Etiam hendrerit luctus lorem, quis facilisis quam venenatis eget. Nulla facilisi. Vestibulum elementum, enim non scelerisque pellentesque, sapien sem viverra metus, nec egestas neque purus nec lorem. Sed risus sapien, ullamcorper ac sagittis non, ultricies et tortor. Curabitur pharetra accumsan augue, ut pellentesque lectus mattis et. Aliquam libero turpis, commodo volutpat convallis vitae, convallis non massa. Aliquam erat volutpat. ");
		contentPanel.setWidget(0, 0, someText);
		someText.setSize("100%", "500px");
		
		Button viewToolbarBtn = new Button("New button");
		flowPanel.add(viewToolbarBtn);
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
}
