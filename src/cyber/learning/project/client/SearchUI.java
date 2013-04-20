package cyber.learning.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchUI {

	public static Widget createSearchUI()
	{
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		//rootPanel.add(horizontalPanel, 500, 100);
		horizontalPanel.setSize("470px", "212px");

		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);

		Label lblWelcomeToThe = new Label("Welcome to my login page");
		lblWelcomeToThe.setStyleName("gwt-Label-Login");

		verticalPanel.add(lblWelcomeToThe);

		lblWelcomeToThe.setSize("206px", "124px");

		Login login = new Login();
		horizontalPanel.add(login);

		Panel searchResults = new FlowPanel();
		TextBox srch= new TextBox();
		Widget searchBox = createSearchBox("Search:", "Go!", searchResults,srch);

		FlowPanel mainContainer = new FlowPanel();
//		mainContainer.add(new Label("Search:"));
		mainContainer.add(searchBox);
		mainContainer.add(new Label("Results:"));
		horizontalPanel.add(mainContainer);

		return horizontalPanel;
	}

	private static Widget createSearchBox(String inputLabel, String buttonLabel,
			Panel searchResults, TextBox srch) {
		HorizontalPanel searchBox = new HorizontalPanel();
		searchBox.add(new Label(inputLabel));
		searchBox.add(srch);
		searchBox.add(new Button(buttonLabel,
				createClickHandler(searchResults,srch)));
		return searchBox;
	}

	private static ClickHandler createClickHandler(final Panel searchResults,final TextBox srch) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				for (int i = 1; i <= 3; i++) {
					searchResults.add(new Label("Result " + srch.getText()));
				}
			}
		};
	}

	private Panel createSearchResults() {
		VerticalPanel searchResults = new VerticalPanel();
		for (int i = 1; i <= 3; i++) {
			searchResults.add(new Label("Result " + i));
		}
		return searchResults;
	}

}
