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
public class Login extends Composite {
	private TextBox textBoxUsername;
	private PasswordTextBox textBoxPassword;

	public Login() {

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(30);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);

		Label lblLoginToYour = new Label("Sign in to your account");
		lblLoginToYour.setStyleName("gwt-Label-Login");
		verticalPanel.add(lblLoginToYour);

		final FlexTable flexTable = new FlexTable();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(flexTable);
		flexTable.setWidth("345px");

		Label lblUsername = new Label("Username:");
		lblUsername.setStyleName("gwt-Label-Login");
		flexTable.setWidget(0, 0, lblUsername);

		textBoxUsername = new TextBox();
		flexTable.setWidget(0, 1, textBoxUsername);

		Label lblPassword = new Label("Password:");
		lblPassword.setStyleName("gwt-Label-Login");
		flexTable.setWidget(1, 0, lblPassword);

		textBoxPassword = new PasswordTextBox();

		flexTable.setWidget(1, 1, textBoxPassword);

		CheckBox chckbxRememberMeOn = new CheckBox("Remember me on this computer");
		chckbxRememberMeOn.setStyleName("gwt-Login-CheckBox");
		flexTable.setWidget(2, 1, chckbxRememberMeOn);

		Button btnSignIn = new Button("Sign In");
		btnSignIn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0
						|| textBoxPassword.getText().length() == 0) {
						Window.alert("Username or password is empty.");
					}
			}
		});
		flexTable.setWidget(3, 0, btnSignIn);

		Button btnSignUp = new Button("Sign Up");
		btnSignUp.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0
						|| textBoxPassword.getText().length() == 0) {
						Window.alert("Username or password is empty.");
			}}
		});
		flexTable.setWidget(3, 1, btnSignUp);
	}

}