package application;

import allForDragons.*;
import commands.Invoker;
import commands.concreteCommand.ExecuteScriptCommand;
import commands.concreteCommand.RemoveByIdCommand;
import commands.concreteCommand.RemoveGreaterCommand;
import commands.concreteCommand.RemoveLowerCommand;
import database.DatabaseConnection;
import database.UserAuthentication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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
    private Label currentUser;

    @FXML
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button removeGreaterButton;

    @FXML
    private Button removeLowerButton;

    @FXML
    private Button executeScriptButton;

    @FXML
    void initialize() {
        currentUser.setText(UserAuthentication.getCurrentUser());
        updateTable();
        mapButton.setOnAction(event -> setMapButton());
        helpButton.setOnAction(event -> setHelpButton());
        infoButton.setOnAction(event -> setInfoButton());
        addButton.setOnAction(event -> setAddButton());
        clearButton.setOnAction(event -> setClearButton());
        executeScriptButton.setOnAction(event -> setExecuteScriptButton());
        removeByIdButton.setOnAction(event -> setRemoveButton("removeById"));
        removeGreaterButton.setOnAction(event -> setRemoveButton("removeGreater"));
        removeLowerButton.setOnAction(event -> setRemoveButton("removeLower"));
    }

    private void updateTable() {
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

    private void setAddButton() {
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);

        Label label1 = new Label("Name:");
        TextField textField1 = new TextField();
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        Label label2 = new Label("Age:");
        TextField textField2 = new TextField();
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        Label label3 = new Label("X coordinate:");
        TextField textField3 = new TextField();
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        Label label4 = new Label("Y coordinate:");
        TextField textField4 = new TextField();
        gridPane.add(label4, 0, 3);
        gridPane.add(textField4, 1, 3);

        Label label5 = new Label("Color:");
        TextField textField5 = new TextField();
        gridPane.add(label5, 0, 4);
        gridPane.add(textField5, 1, 4);

        Label label6 = new Label("Type:");
        TextField textField6 = new TextField();
        gridPane.add(label6, 0, 5);
        gridPane.add(textField6, 1, 5);

        Label label7 = new Label("Character:");
        TextField textField7 = new TextField();
        gridPane.add(label7, 0, 6);
        gridPane.add(textField7, 1, 6);

        Label label8 = new Label("Eyes count:");
        TextField textField8 = new TextField();
        gridPane.add(label8, 0, 7);
        gridPane.add(textField8, 1, 7);

        Button add = new Button("SUBMIT");
        gridPane.add(add, 0, 8);

        CheckBox ifMin = new CheckBox("IF MIN");
        gridPane.add(ifMin, 1, 8);

        Scene scene = new Scene(gridPane, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        add.setOnAction(event1 -> {
            try {
                Dragon dragon = new Dragon(
                        textField1.getText(),
                        new Coordinates(Long.parseLong(textField3.getText()), Float.parseFloat(textField4.getText())),
                        Long.parseLong(textField2.getText()),
                        Color.getColor(textField5.getText()),
                        DragonType.getType(textField6.getText()),
                        DragonCharacter.getCharacter(textField7.getText()),
                        new DragonHead(Double.parseDouble(textField8.getText())));
                if (!ifMin.isSelected()) {
                    DragonAdder.dragonToAdderToDB(dragon);
                    add.getScene().getWindow().hide();
                    updateTable();
                } else {
                    if (DragonsCollection.getDragons().isEmpty()) {
                        DragonAdder.dragonToAdderToDB(dragon);
                        add.getScene().getWindow().hide();
                        updateTable();
                    } else {
                        if (DragonsCollection.getDragons().stream().noneMatch((dragon1 -> dragon.getAge() >= dragon1.getAge()))) {
                            DragonAdder.dragonToAdderToDB(dragon);
                            add.getScene().getWindow().hide();
                            updateTable();
                        } else {
                            System.out.println("Новый элемент не добавлен, так как возраст заданного дракона слишком большой");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void setInfoButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(DragonsCollection.getInfo());
        alert.showAndWait();
    }

    private void setHelpButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("""
                    map : открыть карту с объектами
                    info : вывести информацию о коллекции (тип, дата инициализации, количество элементов)
                    add (if min) : добавить новый элемент в коллекцию (если он наименьший)
                    remove : удалить элемент из коллекции по его id
                    remove_greater : удалить из коллекции все элементы, превышающие заданный по id
                    remove_lower : удалить из коллекции все элементы, меньшие, чем заданный по id
                    clear : очистить созданную Вами часть коллекции
                    execute_script : считать и исполнить скрипт из указанного файла
                    update : обновить значения объекта (осуществляется двойным кликом по ячейке)
                    """);
        alert.showAndWait();
    }

    private void setMapButton() {
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
    }

    private void setClearButton() {
        DatabaseConnection.executeStatement("delete from dragons where creator = '" + UserAuthentication.getCurrentUser() + "'");
        DragonsCollection.updateFromDB();
        updateTable();
        System.out.println("Созданная Вами часть коллекции очищена");
    }

    private void setExecuteScriptButton() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a script file");
            String filePath = fileChooser.showOpenDialog(null).getAbsolutePath();
            Invoker.setSplit(new String[]{"", filePath});
            new ExecuteScriptCommand().execute();
            updateTable();
        } catch (Exception ignored) {}
    }

    private void setRemoveButton(String type) {
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField textField1 = new TextField();
        textField1.setPromptText("ID");
        gridPane.add(textField1, 1, 0);
        Button submit = new Button("SUBMIT");
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

        submit.setOnAction(event -> {
            try {
                Invoker.setSplit(new String[]{"", textField1.getText()});
                switch (type) {
                    case "removeById" -> new RemoveByIdCommand().execute();
                    case "removeLower" -> new RemoveLowerCommand().execute();
                    case "removeGreater" -> new RemoveGreaterCommand().execute();
                }
                submit.getScene().getWindow().hide();
                Thread.sleep(10);
                updateTable();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
