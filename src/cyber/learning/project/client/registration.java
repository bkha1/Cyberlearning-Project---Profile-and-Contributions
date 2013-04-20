package cyber.learning.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
public class registration extends Composite {
	private TextBox textBoxUsername;
	private PasswordTextBox textBoxPassword;

	public registration() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(30);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		
		Label lblLoginToYour = new Label("Register here");
		lblLoginToYour.setStyleName("gwt-Label-register");
		verticalPanel.add(lblLoginToYour);
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(flexTable);
		flexTable.setWidth("345px");
		
		Label lblUsername = new Label("Username:");
		lblUsername.setStyleName("gwt-Label-register");
		flexTable.setWidget(5, 0, lblUsername);
		
		textBoxUsername = new TextBox();
		flexTable.setWidget(5, 1, textBoxUsername);
		
		Label lblPassword = new Label("Password:");
		lblPassword.setStyleName("gwt-Label-register");
		flexTable.setWidget(6, 0, lblPassword);
		
		textBoxPassword = new PasswordTextBox();
		
		flexTable.setWidget(6, 1, textBoxPassword);
		
		
		Button btnSignIn = new Button("Create account");
		btnSignIn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0
						|| textBoxPassword.getText().length() == 0) {
						Window.alert("Username or password is empty."); 
					}
			}
		});
		flexTable.setWidget(8, 1, btnSignIn);
	}

}