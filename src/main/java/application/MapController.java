package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MapController {

    @FXML
    private Button tableButton;

    @FXML
    private Pane mapPane;

    @FXML
    void initialize() {
        tableButton.setFont(MyApplication.appFont(14));
        tableButton.setOnAction(event -> {
            tableButton.getScene().getWindow().hide();
            MyApplication.openTableWindow();
        });
    }
}
