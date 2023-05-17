package application;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button addButton;

    @FXML
    private Button addIfMinButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button removeGreaterButton;

    @FXML
    private Button removeLowerButton;

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
        mapButton.setOnAction(event -> {
            mapButton.getScene().getWindow().hide();
            try {
                Stage stage = new Stage();
                stage.setTitle("Dragons collection manager");
                stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/fxml/map.fxml")).load(), 1024, 720));
                stage.setResizable(false);
                stage.setMaximized(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        helpButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("""
                    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                    add {element} : добавить новый элемент в коллекцию
                    update id {element} : обновить значение элемента коллекции, id которого равен заданному
                    remove_by_id id : удалить элемент из коллекции по его id
                    clear : очистить коллекцию
                    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                    add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                    remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
                    remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
                    max_by_head : вывести любой объект из коллекции, значение поля head которого является максимальным
                    count_by_head head : вывести количество элементов, значение поля head которых равно заданному
                    """);
            alert.showAndWait();
        });
        infoButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText(DragonsCollection.getInfo());
            alert.showAndWait();
        });
    }
}
