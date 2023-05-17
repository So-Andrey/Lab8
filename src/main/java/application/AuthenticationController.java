package application;

import allForDragons.DragonsCollection;
import database.UserAuthentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class AuthenticationController {

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
        becomeAMemberButton.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                stage.setTitle("Dragons collection manager");
                stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/fxml/registration.fxml")).load(), 1024, 720));
                stage.setResizable(false);
                stage.setMaximized(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        logInButton.setOnAction(event -> {
            if (UserAuthentication.userLoggingIn(loginField.getText(), passwordField.getText())) {
                logInButton.getScene().getWindow().hide();
                DragonsCollection.putDragonsFromDB();
                try {
                    Stage stage = new Stage();
                    stage.setTitle("Dragons collection manager");
                    stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/fxml/table.fxml")).load(), 1024, 720));
                    stage.setResizable(false);
                    stage.setMaximized(false);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                loginField.setText("");
                passwordField.setText("");
                loginField.setPromptText("Invalid login or password");
            }
        });
    }
}
