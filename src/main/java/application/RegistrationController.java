package application;

import database.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button toLogInButton;

    @FXML
    void initialize(){
        registerButton.setOnAction(event -> {
            if (UserAuthentication.userRegistration(loginField.getText(), passwordField.getText())) {
                registerButton.getScene().getWindow().hide();
            } else {
                loginField.setText("");
                passwordField.setText("");
                loginField.setPromptText("Login already exists");
            }
        });
        toLogInButton.setOnAction(event -> toLogInButton.getScene().getWindow().hide());
    }
}
