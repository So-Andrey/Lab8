package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MapController {

    @FXML
    private Button tableButton;

    @FXML
    void initialize() {
        tableButton.setFont(MyApplication.appFont(14));
        tableButton.setOnAction(event -> {
            tableButton.getScene().getWindow().hide();
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
        });
    }
}
