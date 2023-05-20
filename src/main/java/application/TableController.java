package application;

import allForDragons.*;
import commands.Invoker;
import commands.concreteCommand.*;
import database.DatabaseConnection;
import database.UserAuthentication;
import exceptions.IllegalValueOfXException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private Text label;

    @FXML
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button addButton;

    @FXML
    private MenuButton removeMenuButton;

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
    private AnchorPane scrollSquare;

    @FXML
    void initialize() {

        table.getStyleClass().add("table-view");
        setFont();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
        y.setCellValueFactory(new PropertyValueFactory<>("y"));
        color.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getColor() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getColor().toString()));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        character.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getCharacter() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getCharacter().toString()));
        eyesCount.setCellValueFactory(new PropertyValueFactory<>("eyesCount"));

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

//        здесь все должно работать с бд
//        name.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue()));
//        age.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setAge(event.getNewValue()));
//        x.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).getCoordinates().setX(event.getNewValue()));
//        y.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).getCoordinates().setY(event.getNewValue()));
//        color.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setColor(Color.getColor(event.getNewValue())));
//        type.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setType(DragonType.getType(event.getNewValue())));
//        character.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setCharacter(DragonCharacter.getCharacter(event.getNewValue())));
//        eyesCount.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).getHead().setEyesCount(event.getNewValue()));

    }

    private void updateTable() {
        scrollSquare.setVisible(DragonsCollection.getDragons().size() >= 21);
        table.setItems(FXCollections.observableList(new ArrayList<>(DragonsCollection.getDragons())));
    }

    private void setFont() {
        removeMenuButton.setFont(MyApplication.appFont(12));
        label.setFont(MyApplication.appFont(13));
        currentUser.setFont(MyApplication.appFont(13));
        helpButton.setFont(MyApplication.appFont(14));
        infoButton.setFont(MyApplication.appFont(14));
        mapButton.setFont(MyApplication.appFont(14));
        addButton.setFont(MyApplication.appFont(12));
        clearButton.setFont(MyApplication.appFont(12));
        removeByIdButton.setFont(MyApplication.appFont(12));
        removeGreaterButton.setFont(MyApplication.appFont(12));
        removeLowerButton.setFont(MyApplication.appFont(12));
        executeScriptButton.setFont(MyApplication.appFont(12));
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
                    Вы можете удалять или изменять дракона только, если являетесь его создателем!!!
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText(null);
        alert.setContentText("Your part of collection has been cleaned");
        alert.showAndWait();
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
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #F9F5D2");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label("ID:");
        gridPane.add(label, 0, 0);
        TextField textField = new TextField();
        gridPane.add(textField, 1, 0);
        Button submit = new Button("SUBMIT");
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 210, 80);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();

        submit.setOnAction(event -> {
            switch (type) {
                case "removeById" -> {
                    String result = RemoveByIdCommand.executeFromGUI(textField.getText());
                    if (result == null) {
                        submit.getScene().getWindow().hide();
                        updateTable();
                    } else {
                        textField.setText("");
                        textField.setPromptText(result);
                    }
                }
                case "removeLower" -> getResultOfDeleting(RemoveLowerCommand.executeFromGUI(textField.getText()), submit.getScene().getWindow(), textField);
                case "removeGreater" -> getResultOfDeleting(RemoveGreaterCommand.executeFromGUI(textField.getText()), submit.getScene().getWindow(), textField);
            }
        });
    }

    private void getResultOfDeleting(String result, Window window, TextField textField) {
        if (result.contains(":")) {
            window.hide();
            updateTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.showAndWait();
        } else {
            textField.setText("");
            textField.setPromptText(result);
        }
    }

    private void setAddButton() {

        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #F9F5D2");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(16.6);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(16.6);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(16.6);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(16.6);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(16.6);
        ColumnConstraints column6 = new ColumnConstraints();
        column6.setPercentWidth(16.6);
        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);

        Label label1 = new Label("Name:");
        TextField textField1 = new TextField();
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        Label label2 = new Label("Age:");
        TextField textField2 = new TextField();
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        Label label3 = new Label("X Coordinate:");
        TextField textField3 = new TextField();
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        Label label4 = new Label("Y Coordinate:");
        TextField textField4 = new TextField();
        gridPane.add(label4, 2, 2);
        gridPane.add(textField4, 3, 2);

        Label label5 = new Label("Eyes count:");
        TextField textField5 = new TextField();
        gridPane.add(label5, 0, 3);
        gridPane.add(textField5, 1, 3);

        Label label6 = new Label("Color:");
        RadioButton radioButton1 = new RadioButton("Green");
        radioButton1.setUserData("green");
        RadioButton radioButton2 = new RadioButton("Brown");
        radioButton2.setUserData("brown");
        RadioButton radioButton3 = new RadioButton("Orange");
        radioButton3.setUserData("orange");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setUserData("null");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup1);
        radioButton2.setToggleGroup(toggleGroup1);
        radioButton3.setToggleGroup(toggleGroup1);
        radioButton4.setToggleGroup(toggleGroup1);
        gridPane.add(label6, 0, 4);
        gridPane.add(radioButton1, 1, 4);
        gridPane.add(radioButton2, 2, 4);
        gridPane.add(radioButton3, 3, 4);
        gridPane.add(radioButton4, 4, 4);

        Label label7 = new Label("Type:");
        RadioButton radioButton5 = new RadioButton("Underground");
        radioButton5.setUserData("underground");
        RadioButton radioButton6 = new RadioButton("Fire");
        radioButton6.setUserData("fire");
        RadioButton radioButton7 = new RadioButton("Water");
        radioButton7.setUserData("water");
        ToggleGroup toggleGroup2 = new ToggleGroup();
        radioButton5.setToggleGroup(toggleGroup2);
        radioButton6.setToggleGroup(toggleGroup2);
        radioButton7.setToggleGroup(toggleGroup2);
        gridPane.add(label7, 0, 5);
        gridPane.add(radioButton7, 1, 5);
        gridPane.add(radioButton5, 2, 5);
        gridPane.add(radioButton6, 3, 5);

        Label label8 = new Label("Character:");
        RadioButton radioButton10 = new RadioButton("Cunning");
        radioButton10.setUserData("cunning");
        RadioButton radioButton8 = new RadioButton("Wise");
        radioButton8.setUserData("wise");
        RadioButton radioButton11 = new RadioButton("Chaotic evil");
        radioButton11.setUserData("chaotic_evil");
        RadioButton radioButton9 = new RadioButton("Fickle");
        radioButton9.setUserData("fickle");
        RadioButton radioButton12 = new RadioButton("null");
        radioButton12.setUserData("null");
        ToggleGroup toggleGroup3 = new ToggleGroup();
        radioButton10.setToggleGroup(toggleGroup3);
        radioButton11.setToggleGroup(toggleGroup3);
        radioButton12.setToggleGroup(toggleGroup3);
        radioButton8.setToggleGroup(toggleGroup3);
        radioButton9.setToggleGroup(toggleGroup3);
        gridPane.add(label8, 0, 6);
        gridPane.add(radioButton8, 1, 6);
        gridPane.add(radioButton9, 2, 6);
        gridPane.add(radioButton10, 3, 6);
        gridPane.add(radioButton11, 4, 6);
        gridPane.add(radioButton12, 5, 6);

        Label label9 = new Label("If min:");
        CheckBox checkBox = new CheckBox();
        gridPane.add(label9, 3, 7);
        gridPane.add(checkBox, 4, 7);
        Button button = new Button("Submit");
        button.setOnAction(event -> {
            label6.setTextFill(javafx.scene.paint.Color.BLACK);
            label7.setTextFill(javafx.scene.paint.Color.BLACK);
            label8.setTextFill(javafx.scene.paint.Color.BLACK);
            boolean error = false;
            if (textField1.getText().isEmpty() | textField1.getText().contains("'")) {
                textField1.setText("");
                textField1.setPromptText("Invalid input");
                error = true;
            }
            try {
                if (Long.parseLong(textField2.getText()) <= 0) throw new InputMismatchException();
            } catch (NumberFormatException numberFormatException) {
                textField2.setText("");
                textField2.setPromptText("Invalid input");
                error = true;
            } catch (InputMismatchException inputMismatchException) {
                textField2.setText("");
                textField2.setPromptText("age > 0");
                error = true;
            }
            try {
                if (Long.parseLong(textField3.getText()) > 610) throw new IllegalValueOfXException();
            } catch (NumberFormatException numberFormatException) {
                textField3.setText("");
                textField3.setPromptText("Invalid input");
                error = true;
            } catch (IllegalValueOfXException illegalValueOfXException) {
                textField3.setText("");
                textField3.setPromptText("x <= 610");
                error = true;
            }
            try {
                Float.parseFloat(textField4.getText());
            } catch (NumberFormatException numberFormatException) {
                textField4.setText("");
                textField4.setPromptText("Invalid input");
                error = true;
            }
            try {
                Double.parseDouble(textField5.getText());
            } catch (NumberFormatException numberFormatException) {
                textField5.setText("");
                textField5.setPromptText("Invalid input");
                error = true;
            }
            if (toggleGroup1.getSelectedToggle() == null) {
                label6.setTextFill(javafx.scene.paint.Color.RED);
                error = true;
            }
            if (toggleGroup2.getSelectedToggle() == null) {
                label7.setTextFill(javafx.scene.paint.Color.RED);
                error = true;
            }
            if (toggleGroup3.getSelectedToggle() == null) {
                label8.setTextFill(javafx.scene.paint.Color.RED);
                error = true;
            }
            if (error) return;
            try {
                Dragon dragon = new Dragon(
                        textField1.getText(),
                        new Coordinates(Long.parseLong(textField3.getText()), Float.parseFloat(textField4.getText())),
                        Long.parseLong(textField2.getText()),
                        Color.getColor(toggleGroup1.getSelectedToggle().getUserData().toString()),
                        DragonType.getType(toggleGroup2.getSelectedToggle().getUserData().toString()),
                        DragonCharacter.getCharacter(toggleGroup3.getSelectedToggle().getUserData().toString()),
                        new DragonHead(Double.parseDouble(textField5.getText())));
                if (!checkBox.isSelected()) {
                    DragonAdder.dragonToAdderToDB(dragon);
                    button.getScene().getWindow().hide();
                    updateTable();
                } else {
                    if (DragonsCollection.getDragons().isEmpty()) {
                        DragonAdder.dragonToAdderToDB(dragon);
                        button.getScene().getWindow().hide();
                        updateTable();
                    } else {
                        if (DragonsCollection.getDragons().stream().noneMatch((dragon1 -> dragon.getAge() >= dragon1.getAge()))) {
                            DragonAdder.dragonToAdderToDB(dragon);
                            button.getScene().getWindow().hide();
                            updateTable();
                        } else {
                            System.out.println("Dragon is too old"); // to GUI
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        gridPane.add(button, 2, 7);

        Scene scene = new Scene(gridPane, 700, 270);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
