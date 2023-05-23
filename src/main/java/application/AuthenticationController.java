package application;

import allForDragons.DragonsCollection;
import database.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AuthenticationController {

    private boolean registration = false;

    @FXML
    private Text title;

    @FXML
    private Text label;

    @FXML
    private Button becomeAMemberButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        title.setFont(MyApplication.appFont(39));
        label.setFont(MyApplication.appFont(23));
        becomeAMemberButton.setOnAction(event -> {
            if (registration) {
                label.setText("authorisation");
                logInButton.setText("Log In");
                becomeAMemberButton.setText("Don't have an account? Sign Up");
                registration = false;
            } else {
                label.setText("   registration");
                label.setTextAlignment(TextAlignment.CENTER);
                logInButton.setText("Submit");
                logInButton.setTextAlignment(TextAlignment.CENTER);
                becomeAMemberButton.setText("       Already registered? Log In");
                registration = true;
            }
        });
        logInButton.setOnAction(event -> {
            if (registration) {
                if (UserAuthentication.userRegistration(loginField.getText(), passwordField.getText())) {
                    label.setText("authorisation");
                    logInButton.setText("Log In");
                    loginField.setText("");
                    passwordField.setText("");
                    registration = false;
                } else {
                    loginField.setText("");
                    passwordField.setText("");
                    loginField.setPromptText("Login already exists");
                }
            } else {
                if (UserAuthentication.userLoggingIn(loginField.getText(), passwordField.getText())) {
                    logInButton.getScene().getWindow().hide();
                    DragonsCollection.putDragonsFromDB();
                    MyApplication.openTableWindow();
                } else {
                    loginField.setText("");
                    passwordField.setText("");
                    loginField.setPromptText("Invalid login or password");
                }
            }
        });
    }
}
