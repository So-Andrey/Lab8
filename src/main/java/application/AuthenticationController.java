package application;

import allForDragons.DragonsCollection;
import database.UserAuthentication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import l10n_i18n.Language;

import java.util.ResourceBundle;

public class AuthenticationController {

    @FXML
    public Button enLangButton;

    @FXML
    public Button ruLangButton;

    @FXML
    public Button frLangButton;

    @FXML
    public Button trLangButton;

    @FXML
    private MenuButton languageMenuButton;

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

    private boolean registration = false;

    @FXML
    void initialize() {
        title.setFont(MyApplication.appFont(39));
        languageMenuButton.getStyleClass().add("language-menu-button");
        setLanguage();
        enLangButton.setOnAction(event -> updateLanguage(Language.en));
        ruLangButton.setOnAction(event -> updateLanguage(Language.ru));
        frLangButton.setOnAction(event -> updateLanguage(Language.fr));
        trLangButton.setOnAction(event -> updateLanguage(Language.tr));
        becomeAMemberButton.setOnAction(event -> {
            if (registration) {
                label.setText(MyApplication.getAppLanguage().getString("auth"));
                logInButton.setText(MyApplication.getAppLanguage().getString("log_in"));
                becomeAMemberButton.setText(MyApplication.getAppLanguage().getString("become"));
                registration = false;
            } else {
                label.setText(MyApplication.getAppLanguage().getString("reg"));
                logInButton.setText(MyApplication.getAppLanguage().getString("submit"));
                becomeAMemberButton.setText(MyApplication.getAppLanguage().getString("already_reg"));
                registration = true;
            }
        });
        logInButton.setOnAction(event -> {
            if (registration) {
                if (UserAuthentication.userRegistration(loginField.getText().trim(), passwordField.getText())) {
                    loginField.setText("");
                    passwordField.setText("");
                    label.setText(MyApplication.getAppLanguage().getString("auth"));
                    logInButton.setText(MyApplication.getAppLanguage().getString("log_in"));
                    becomeAMemberButton.setText(MyApplication.getAppLanguage().getString("become"));
                    registration = false;
                } else {
                    loginField.setText("");
                    passwordField.setText("");
                    loginField.setPromptText(MyApplication.getAppLanguage().getString("login_exists"));
                }
            } else {
                if (UserAuthentication.userLoggingIn(loginField.getText().trim(), passwordField.getText())) {
                    logInButton.getScene().getWindow().hide();
                    DragonsCollection.putDragonsFromDB();
                    MyApplication.openTableWindow();
                } else {
                    loginField.setText("");
                    passwordField.setText("");
                    loginField.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
                }
            }
        });
    }

    private void setLanguage() {
        loginField.setPromptText(MyApplication.getAppLanguage().getString("login"));
        passwordField.setPromptText(MyApplication.getAppLanguage().getString("password"));
        if (registration) {
            label.setText(MyApplication.getAppLanguage().getString("reg"));
            logInButton.setText(MyApplication.getAppLanguage().getString("submit"));
            becomeAMemberButton.setText(MyApplication.getAppLanguage().getString("already_reg"));
        } else {
            label.setText(MyApplication.getAppLanguage().getString("auth"));
            logInButton.setText(MyApplication.getAppLanguage().getString("log_in"));
            becomeAMemberButton.setText(MyApplication.getAppLanguage().getString("become"));
        }
    }

    private void updateLanguage(ResourceBundle language) {
        if (!MyApplication.getAppLanguage().equals(language)) {
            MyApplication.setAppLanguage(language);
            setLanguage();
        }
    }
}
