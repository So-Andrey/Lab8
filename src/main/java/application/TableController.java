package application;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class TableController {

    @FXML
    private TableView<Dragon> table;

    @FXML
    private TableColumn<Dragon, Long> age;

    @FXML
    private TableColumn<Dragon, String> character;

    @FXML
    private TableColumn<Dragon, String> color;

    @FXML
    private TableColumn<Dragon, String> creationDate;

    @FXML
    private TableColumn<Dragon, String> creator;

    @FXML
    private TableColumn<Dragon, Double> eyesCount;

    @FXML
    private TableColumn<Dragon, Long> id;

    @FXML
    private TableColumn<Dragon, String> name;

    @FXML
    private TableColumn<Dragon, String> type;

    @FXML
    private TableColumn<Dragon, Long> x;

    @FXML
    private TableColumn<Dragon, Float> y;

    @FXML
    void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
        y.setCellValueFactory(new PropertyValueFactory<>("y"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        eyesCount.setCellValueFactory(new PropertyValueFactory<>("eyesCount"));
        table.setItems(FXCollections.observableList(new ArrayList<>(DragonsCollection.getDragons())));
    }
}
