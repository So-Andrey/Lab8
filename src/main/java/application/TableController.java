package application;

import allForDragons.*;
import commands.concreteCommand.*;
import database.DatabaseConnection;
import database.UserAuthentication;
import exceptions.IllegalValueOfXException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.LongStringConverter;
import l10n_i18n.Language;

public class TableController {

    @FXML
    public TextField xTextField;

    @FXML
    public TextField yTextField;

    @FXML
    public Button setCenterButton;
    @FXML
    private Pane mapPane;

    @FXML
    public Button enLangButton;

    @FXML
    public Button ruLangButton;

    @FXML
    public Button frLangButton;

    @FXML
    public Button trLangButton;

    @FXML
    public RadioButton idRadioButton;

    @FXML
    public RadioButton creatorRadioButton;

    @FXML
    public RadioButton nameRadioButton;

    @FXML
    public RadioButton creationDateRadioButton;

    @FXML
    public RadioButton ageRadioButton;

    @FXML
    public RadioButton xRadioButton;

    @FXML
    public RadioButton colorRadioButton;

    @FXML
    public RadioButton yRadioButton;

    @FXML
    public RadioButton typeRadioButton;

    @FXML
    public RadioButton characterRadioButton;

    @FXML
    public RadioButton eyesCountRadioButton;

    @FXML
    public Button countByHeadButton;

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
    private TableColumn<Dragon, String> eyesCount;

    @FXML
    private TableColumn<Dragon, Long> id;

    @FXML
    private TableColumn<Dragon, String> name;

    @FXML
    private TableColumn<Dragon, String> type;

    @FXML
    private TableColumn<Dragon, Coordinates> coordinates;

    @FXML
    private TableColumn<Dragon, Long> x;

    @FXML
    private TableColumn<Dragon, String> y;

    @FXML
    private Label currentUser;

    @FXML
    private Label label;

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
    private MenuButton languageMenuButton;

    @FXML
    private Button logOutButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MenuButton searchMenuButton;

    private final ToggleGroup searchToggleGroup = new ToggleGroup();

    private boolean map = false;

    @FXML
    void initialize() {

        idRadioButton.setToggleGroup(searchToggleGroup);
        creatorRadioButton.setToggleGroup(searchToggleGroup);
        nameRadioButton.setToggleGroup(searchToggleGroup);
        creationDateRadioButton.setToggleGroup(searchToggleGroup);
        ageRadioButton.setToggleGroup(searchToggleGroup);
        xRadioButton.setToggleGroup(searchToggleGroup);
        yRadioButton.setToggleGroup(searchToggleGroup);
        colorRadioButton.setToggleGroup(searchToggleGroup);
        typeRadioButton.setToggleGroup(searchToggleGroup);
        characterRadioButton.setToggleGroup(searchToggleGroup);
        eyesCountRadioButton.setToggleGroup(searchToggleGroup);
        searchToggleGroup.selectToggle(nameRadioButton);
        idRadioButton.setUserData("ID");
        creatorRadioButton.setUserData("Creator");
        nameRadioButton.setUserData("Name");
        creationDateRadioButton.setUserData("Creation Date");
        ageRadioButton.setUserData("Age");
        xRadioButton.setUserData("X Coordinate");
        yRadioButton.setUserData("Y Coordinate");
        colorRadioButton.setUserData("Color");
        typeRadioButton.setUserData("Type");
        characterRadioButton.setUserData("Character");
        eyesCountRadioButton.setUserData("Eyes Count");
        idRadioButton.setText("ID");

        searchMenuButton.getStyleClass().add("menu-button");
        removeMenuButton.getStyleClass().add("menu-button");
        languageMenuButton.getStyleClass().add("language-menu-button");
        table.getStyleClass().add("table-view");
        setLanguage();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateNameFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        age.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        age.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateAgeFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp") + " (" + MyApplication.getAppLanguage().getString("age") + " > 0)");
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
        x.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        x.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateXFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp") + " (X <= 610)");
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        y.setCellValueFactory(dragonStringCellDataFeatures -> new SimpleStringProperty((dragonStringCellDataFeatures.getValue().getY() + "").replace(".", MyApplication.getAppLanguage().getString("separator"))));
        y.setCellFactory(TextFieldTableCell.forTableColumn());
        y.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateYFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        color.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getColor() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getColor().toString()));
        color.setCellFactory(TextFieldTableCell.forTableColumn());
        color.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateColorFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        type.setCellValueFactory(dragonStringCellDataFeatures -> new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getType().toString()));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateTypeFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        character.setCellValueFactory(dragonStringCellDataFeatures ->
                dragonStringCellDataFeatures.getValue().getCharacter() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(dragonStringCellDataFeatures.getValue().getCharacter().toString()));
        character.setCellFactory(TextFieldTableCell.forTableColumn());
        character.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateCharacterFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        eyesCount.setCellValueFactory(dragonStringCellDataFeatures -> new SimpleStringProperty((dragonStringCellDataFeatures.getValue().getHead().getEyesCount() + "").replace(".", MyApplication.getAppLanguage().getString("separator"))));
        eyesCount.setCellFactory(TextFieldTableCell.forTableColumn());
        eyesCount.setOnEditCommit(event -> {
            try {
                DragonUpdater.updateHeadFromGUI(event.getRowValue(), event.getNewValue());
                updateTable();
            } catch (InputMismatchException inputMismatchException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
                table.refresh();
            } catch (NullPointerException nullPointerException) {
                showAlert("Error", MyApplication.getAppLanguage().getString("edit"));
                table.refresh();
            }
        });
        eyesCount.setComparator((o1, o2) -> Double.compare(Double.parseDouble(o1.replace(',', '.')), Double.parseDouble(o2.replaceAll(",", "."))));
        y.setComparator((o1, o2) -> Float.compare(Float.parseFloat(o1.replace(',', '.')), Float.parseFloat(o2.replaceAll(",", "."))));
        creationDate.setComparator((o1, o2) -> {
            try {
                return Long.compare(new SimpleDateFormat(MyApplication.getAppLanguage().getString("date_format")).parse(o1).getTime(), new SimpleDateFormat(MyApplication.getAppLanguage().getString("date_format")).parse(o2).getTime());
            } catch (ParseException ignored) {}
            return 0;
        });

        currentUser.setText(UserAuthentication.getCurrentUser());
        updateTable();

        mapButton.setOnAction(event -> setMapButton());
        helpButton.setOnAction(event -> setHelpButton());
        infoButton.setOnAction(event -> setInfoButton());
        addButton.setOnAction(event -> setAddButton());
        clearButton.setOnAction(event -> setClearButton());
        executeScriptButton.setOnAction(event -> setExecuteScriptButton());
        countByHeadButton.setOnAction(event -> setCountByHeadButton());
        removeByIdButton.setOnAction(event -> setRemoveButton("removeById"));
        removeGreaterButton.setOnAction(event -> setRemoveButton("removeGreater"));
        removeLowerButton.setOnAction(event -> setRemoveButton("removeLower"));
        logOutButton.setOnAction(event -> setLogOutButton());
        enLangButton.setOnAction(event -> updateLanguage(Language.en));
        ruLangButton.setOnAction(event -> updateLanguage(Language.ru));
        frLangButton.setOnAction(event -> updateLanguage(Language.fr));
        trLangButton.setOnAction(event -> updateLanguage(Language.tr));
        setCenterButton.setOnAction(event -> setSetCenterButton());

    }

    private void updateLanguage(ResourceBundle language) {
        if (!MyApplication.getAppLanguage().equals(language)) {
            MyApplication.setAppLanguage(language);
            setLanguage();
        }
    }

    private void setLanguage() {
        name.setText(MyApplication.getAppLanguage().getString("name"));
        creator.setText(MyApplication.getAppLanguage().getString("creator"));
        creationDate.setText(MyApplication.getAppLanguage().getString("creation_date"));
        age.setText(MyApplication.getAppLanguage().getString("age"));
        color.setText(MyApplication.getAppLanguage().getString("color"));
        type.setText(MyApplication.getAppLanguage().getString("type"));
        coordinates.setText(MyApplication.getAppLanguage().getString("coordinates"));
        character.setText(MyApplication.getAppLanguage().getString("character"));
        eyesCount.setText(MyApplication.getAppLanguage().getString("eyes_count"));
        nameRadioButton.setText(MyApplication.getAppLanguage().getString("name"));
        creatorRadioButton.setText(MyApplication.getAppLanguage().getString("creator"));
        xRadioButton.setText(MyApplication.getAppLanguage().getString("x"));
        yRadioButton.setText(MyApplication.getAppLanguage().getString("y"));
        creationDateRadioButton.setText(MyApplication.getAppLanguage().getString("creation_date"));
        ageRadioButton.setText(MyApplication.getAppLanguage().getString("age"));
        colorRadioButton.setText(MyApplication.getAppLanguage().getString("color"));
        typeRadioButton.setText(MyApplication.getAppLanguage().getString("type"));
        characterRadioButton.setText(MyApplication.getAppLanguage().getString("character"));
        eyesCountRadioButton.setText(MyApplication.getAppLanguage().getString("eyes_count"));
        searchTextField.setPromptText(MyApplication.getAppLanguage().getString("search"));
        addButton.setText(MyApplication.getAppLanguage().getString("add"));
        removeMenuButton.setText(MyApplication.getAppLanguage().getString("remove"));
        removeByIdButton.setText(MyApplication.getAppLanguage().getString("remove_by_id"));
        removeLowerButton.setText(MyApplication.getAppLanguage().getString("remove_lower"));
        removeGreaterButton.setText(MyApplication.getAppLanguage().getString("remove_greater"));
        clearButton.setText(MyApplication.getAppLanguage().getString("clear"));
        executeScriptButton.setText(MyApplication.getAppLanguage().getString("execute_script"));
        countByHeadButton.setText(MyApplication.getAppLanguage().getString("count_by_head"));
        infoButton.setText(MyApplication.getAppLanguage().getString("info"));
        helpButton.setText(MyApplication.getAppLanguage().getString("help_button"));
        logOutButton.setText(MyApplication.getAppLanguage().getString("log_out"));
        label.setText(MyApplication.getAppLanguage().getString("user"));
        if (map) {
            mapButton.setText(MyApplication.getAppLanguage().getString("table"));
        } else {
            mapButton.setText(MyApplication.getAppLanguage().getString("map"));
        }
        if (MyApplication.getAppLanguage().equals(Language.en)) {
            addButton.setFont(MyApplication.appFont(16));
            removeMenuButton.setFont(MyApplication.appFont(16));
            removeByIdButton.setFont(MyApplication.appFont(16));
            removeLowerButton.setFont(MyApplication.appFont(16));
            removeGreaterButton.setFont(MyApplication.appFont(16));
            clearButton.setFont(MyApplication.appFont(16));
            executeScriptButton.setFont(MyApplication.appFont(16));
            countByHeadButton.setFont(MyApplication.appFont(16));
            infoButton.setFont(MyApplication.appFont(16));
            helpButton.setFont(MyApplication.appFont(16));
            label.setFont(MyApplication.appFont(16));
            mapButton.setFont(MyApplication.appFont(16));
        } else {
            addButton.setFont(new Font("System", 17));
            removeMenuButton.setFont(new Font("System", 17));
            removeByIdButton.setFont(new Font("System", 17));
            removeLowerButton.setFont(new Font("System", 17));
            removeGreaterButton.setFont(new Font("System", 17));
            clearButton.setFont(new Font("System", 17));
            executeScriptButton.setFont(new Font("System", 17));
            countByHeadButton.setFont(new Font("System", 17));
            infoButton.setFont(new Font("System", 17));
            helpButton.setFont(new Font("System", 17));
            label.setFont(new Font("System", 17));
            mapButton.setFont(new Font("System", 17));
        }
        table.refresh();
    }

    private void updateTable() {
        FilteredList<Dragon> filteredData = new FilteredList<>(FXCollections.observableList(new ArrayList<>(DragonsCollection.getDragons())), b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(dragon -> {
            if (newValue.trim().isEmpty()) return true;
            return switch (searchToggleGroup.getSelectedToggle().getUserData().toString()) {
                case "ID" -> String.valueOf(dragon.getId()).toLowerCase().contains(newValue.toLowerCase());
                case "Creator" -> dragon.getCreator().toLowerCase().contains(newValue.toLowerCase());
                case "Creation Date" -> dragon.getCreationDate().toLowerCase().contains(newValue.toLowerCase());
                case "Age" -> String.valueOf(dragon.getAge()).toLowerCase().contains(newValue.toLowerCase());
                case "X Coordinate" -> String.valueOf(dragon.getCoordinates().getX()).toLowerCase().contains(newValue.toLowerCase());
                case "Y Coordinate" -> String.valueOf(dragon.getCoordinates().getY()).toLowerCase().contains(newValue.toLowerCase());
                case "Color" -> String.valueOf(dragon.getColor()).toLowerCase().contains(newValue.toLowerCase());
                case "Type" -> String.valueOf(dragon.getType()).toLowerCase().contains(newValue.toLowerCase());
                case "Character" -> String.valueOf(dragon.getCharacter()).toLowerCase().contains(newValue.toLowerCase());
                case "Eyes Count" -> String.valueOf(dragon.getHead().getEyesCount()).toLowerCase().contains(newValue.toLowerCase());
                default -> dragon.getName().toLowerCase().contains(newValue.toLowerCase());
            };}));
        SortedList<Dragon> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    private void setInfoButton() {
        showAlert("Info", DragonsCollection.getInfo());
    }

    private void setHelpButton() {
        showAlert("Help", MyApplication.getAppLanguage().getString("help"));
    }

    private void setMapButton() {
        if (map) {
            mapButton.setText(MyApplication.getAppLanguage().getString("map"));
            mapPane.setVisible(false);
            table.setVisible(true);
            searchTextField.setVisible(true);
            searchMenuButton.setVisible(true);
            xTextField.setVisible(false);
            yTextField.setVisible(false);
            setCenterButton.setVisible(false);
            map = false;
        } else {
            clearAnimation();
            updateMap();
            mapButton.setText(MyApplication.getAppLanguage().getString("table"));
            table.setVisible(false);
            searchTextField.setVisible(false);
            searchMenuButton.setVisible(false);
            xTextField.setVisible(true);
            yTextField.setVisible(true);
            setCenterButton.setVisible(true);
            mapPane.setVisible(true);
            map = true;
        }
    }

    private void setClearButton() {
        new ClearCommand().executeFromGUI();
        updateTable();
        if (map) updateMap();
        showAlert("Result", MyApplication.getAppLanguage().getString("cleaned") + "\n" + MyApplication.getAppLanguage().getString("delete_ps"));
    }

    private void setExecuteScriptButton() {
        try {
            showAlert("README", MyApplication.getAppLanguage().getString("script_help"));
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a script file");
            String result = new ExecuteScriptCommand().executeFromGUI(fileChooser.showOpenDialog(table.getScene().getWindow()).getAbsolutePath());
            if (result != null) {
                showAlert("Result", result);
            } else {
                updateTable();
                if (map) updateMap();
                if (!CountByHeadCommand.isResultEmpty()) {
                    final String[] countByHeadResultString = {""};
                    CountByHeadCommand.getResult().forEach(result1 -> countByHeadResultString[0] += result1 + "\n");
                    CountByHeadCommand.resetResult();
                    showAlert("Count by head result", countByHeadResultString[0]);
                }
            }
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
        textField.setStyle("-fx-focus-color: #F3C173");
        gridPane.add(textField, 1, 0);
        Button submit = new Button(MyApplication.getAppLanguage().getString("submit").toUpperCase());
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 210, 80);
        scene.getStylesheets().add("/css/style.css");
        submit.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();

        submit.setOnAction(event -> {
            switch (type) {
                case "removeById" -> {
                    String result = new RemoveByIdCommand().executeFromGUI(textField.getText());
                    if (result == null) {
                        submit.getScene().getWindow().hide();
                        updateTable();
                        if (map) updateMap();
                    } else {
                        textField.setText("");
                        textField.setPromptText(result);
                    }
                }
                case "removeLower" -> getResultOfDeleting(new RemoveLowerCommand().executeFromGUI(textField.getText()), submit.getScene().getWindow(), textField);
                case "removeGreater" -> getResultOfDeleting(new RemoveGreaterCommand().executeFromGUI(textField.getText()), submit.getScene().getWindow(), textField);
            }
        });
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

        Label label1 = new Label(MyApplication.getAppLanguage().getString("name") + ":");
        TextField textField1 = new TextField();
        textField1.setStyle("-fx-focus-color: #F3C173;");
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        Label label2 = new Label(MyApplication.getAppLanguage().getString("age") + ":");
        TextField textField2 = new TextField();
        textField2.setStyle("-fx-focus-color: #F3C173;");
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        Label label3 = new Label(MyApplication.getAppLanguage().getString("x") + ":");
        TextField textField3 = new TextField();
        textField3.setStyle("-fx-focus-color: #F3C173;");
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        Label label4 = new Label(MyApplication.getAppLanguage().getString("y") + ":");
        TextField textField4 = new TextField();
        textField4.setStyle("-fx-focus-color: #F3C173;");
        gridPane.add(label4, 2, 2);
        gridPane.add(textField4, 3, 2);

        Label label5 = new Label(MyApplication.getAppLanguage().getString("eyes_count") + ":");
        TextField textField5 = new TextField();
        textField5.setStyle("-fx-focus-color: #F3C173;");
        gridPane.add(label5, 0, 3);
        gridPane.add(textField5, 1, 3);

        Label label6 = new Label(MyApplication.getAppLanguage().getString("color") + ":");
        RadioButton radioButton1 = new RadioButton("Green");
        radioButton1.setStyle("-fx-focus-color: #F3C173");
        radioButton1.setUserData("green");
        RadioButton radioButton2 = new RadioButton("Brown");
        radioButton2.setStyle("-fx-focus-color: #F3C173");
        radioButton2.setUserData("brown");
        RadioButton radioButton3 = new RadioButton("Orange");
        radioButton3.setStyle("-fx-focus-color: #F3C173");
        radioButton3.setUserData("orange");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setStyle("-fx-focus-color: #F3C173");
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

        Label label7 = new Label(MyApplication.getAppLanguage().getString("type") + ":");
        RadioButton radioButton5 = new RadioButton("Underground");
        radioButton5.setStyle("-fx-focus-color: #F3C173");
        radioButton5.setUserData("underground");
        RadioButton radioButton6 = new RadioButton("Fire");
        radioButton6.setStyle("-fx-focus-color: #F3C173");
        radioButton6.setUserData("fire");
        RadioButton radioButton7 = new RadioButton("Water");
        radioButton7.setStyle("-fx-focus-color: #F3C173");
        radioButton7.setUserData("water");
        ToggleGroup toggleGroup2 = new ToggleGroup();
        radioButton5.setToggleGroup(toggleGroup2);
        radioButton6.setToggleGroup(toggleGroup2);
        radioButton7.setToggleGroup(toggleGroup2);
        gridPane.add(label7, 0, 5);
        gridPane.add(radioButton7, 1, 5);
        gridPane.add(radioButton5, 2, 5);
        gridPane.add(radioButton6, 3, 5);

        Label label8 = new Label(MyApplication.getAppLanguage().getString("character") + ":");
        RadioButton radioButton10 = new RadioButton("Cunning");
        radioButton10.setStyle("-fx-focus-color: #F3C173");
        radioButton10.setUserData("cunning");
        RadioButton radioButton8 = new RadioButton("Wise");
        radioButton8.setStyle("-fx-focus-color: #F3C173");
        radioButton8.setUserData("wise");
        RadioButton radioButton11 = new RadioButton("Chaotic evil");
        radioButton11.setStyle("-fx-focus-color: #F3C173");
        radioButton11.setUserData("chaotic_evil");
        RadioButton radioButton9 = new RadioButton("Fickle");
        radioButton9.setStyle("-fx-focus-color: #F3C173");
        radioButton9.setUserData("fickle");
        RadioButton radioButton12 = new RadioButton("null");
        radioButton12.setStyle("-fx-focus-color: #F3C173");
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

        Label label9 = new Label(MyApplication.getAppLanguage().getString("if_min") + ":");
        CheckBox checkBox = new CheckBox();
        gridPane.add(label9, 3, 7);
        gridPane.add(checkBox, 4, 7);
        Button button = new Button(MyApplication.getAppLanguage().getString("submit").toUpperCase());
        button.setCursor(Cursor.HAND);
        button.setOnAction(event -> {
            textField1.setPromptText("");
            textField2.setPromptText("");
            textField3.setPromptText("");
            textField4.setPromptText("");
            textField5.setPromptText("");
            label6.setTextFill(javafx.scene.paint.Color.BLACK);
            label7.setTextFill(javafx.scene.paint.Color.BLACK);
            label8.setTextFill(javafx.scene.paint.Color.BLACK);
            boolean error = false;
            if (textField1.getText().trim().isEmpty() | textField1.getText().contains("'")) {
                textField1.setText("");
                textField1.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
                error = true;
            }
            try {
                if (Long.parseLong(textField2.getText()) <= 0) throw new InputMismatchException();
            } catch (NumberFormatException numberFormatException) {
                textField2.setText("");
                textField2.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
                error = true;
            } catch (InputMismatchException inputMismatchException) {
                textField2.setText("");
                textField2.setPromptText(MyApplication.getAppLanguage().getString("age") + " > 0");
                error = true;
            }
            try {
                if (Long.parseLong(textField3.getText()) > 610) throw new IllegalValueOfXException();
            } catch (NumberFormatException numberFormatException) {
                textField3.setText("");
                textField3.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
                error = true;
            } catch (IllegalValueOfXException illegalValueOfXException) {
                textField3.setText("");
                textField3.setPromptText("X <= 610");
                error = true;
            }
            try {
                Float.parseFloat(textField4.getText());
            } catch (NumberFormatException numberFormatException) {
                textField4.setText("");
                textField4.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
                error = true;
            }
            try {
                Double.parseDouble(textField5.getText());
            } catch (NumberFormatException numberFormatException) {
                textField5.setText("");
                textField5.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
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
                    toAppearDragons.add(dragon);
                    if (map) updateMap();
                    updateTable();
                } else {
                    if (DragonsCollection.getDragons().isEmpty()) {
                        DragonAdder.dragonToAdderToDB(dragon);
                        button.getScene().getWindow().hide();
                        toAppearDragons.add(dragon);
                        if (map) updateMap();
                        updateTable();
                    } else {
                        if (DragonsCollection.getDragons().stream().noneMatch((dragon1 -> dragon.getAge() >= dragon1.getAge()))) {
                            DragonAdder.dragonToAdderToDB(dragon);
                            button.getScene().getWindow().hide();
                            toAppearDragons.add(dragon);
                            if (map) updateMap();
                            updateTable();
                        } else {
                            textField2.setText("");
                            textField2.setPromptText(MyApplication.getAppLanguage().getString("too_old"));
                        }
                    }
                }
            } catch (Exception ignored) {}
        });
        gridPane.add(button, 2, 7);

        Scene scene = new Scene(gridPane, 700, 270);
        scene.getStylesheets().add("/css/style.css");
        button.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void setCountByHeadButton() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #F9F5D2");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label(MyApplication.getAppLanguage().getString("eyes_count") + ":");
        gridPane.add(label, 0, 0);
        TextField textField = new TextField();
        textField.setStyle("-fx-focus-color: #F3C173");
        gridPane.add(textField, 1, 0);
        Button submit = new Button(MyApplication.getAppLanguage().getString("submit").toUpperCase());
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 300, 80);
        scene.getStylesheets().add("/css/style.css");
        submit.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();

        submit.setOnAction(event -> {
            try {
                double eyesCount = Double.parseDouble(textField.getText());
                submit.getScene().getWindow().hide();
                showAlert("Result", MyApplication.getAppLanguage().getString("count_by_head") + ": " + DragonsCollection.getDragons().stream().filter(dragon -> dragon.getHead().getEyesCount() == eyesCount).count());
            } catch (NumberFormatException numberFormatException) {
                textField.setText("");
                textField.setPromptText(MyApplication.getAppLanguage().getString("invalid_inp"));
            }
        });
    }

    private void setLogOutButton() {
        logOutButton.getScene().getWindow().hide();
        DragonsCollection.getDragons().clear();
        UserAuthentication.logOut();
        users.clear();
        Stage stage = new Stage();
        stage.setTitle("Dragons collection manager");
        try {
            Scene scene = new Scene(new FXMLLoader(getClass().getResource("/fxml/authentication.fxml")).load(), 1024, 720);
            scene.getStylesheets().add("/css/style.css");
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }

    private void getResultOfDeleting(String result, Window window, TextField textField) {
        if (result.contains(":")) {
            window.hide();
            updateTable();
            if (map) updateMap();
            showAlert("Result", result);
        } else {
            textField.setText("");
            textField.setPromptText(result);
        }
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #F9F5D2");
        Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        ok.setStyle("-fx-background-color: #F3CBC5; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #FF8C4C; -fx-cursor: HAND;");
        alert.showAndWait();
    }

    private static final HashMap<String, javafx.scene.paint.Color> users = new HashMap<>();

    protected static void getUsersAndColors() {
        try {
            ResultSet resultSet = DatabaseConnection.executePreparedStatement("select * from users");
            while (resultSet.next()) {
                users.put(resultSet.getString(1), javafx.scene.paint.Color.color(1 - Math.random() * 0.8, 1 - Math.random() * 0.8, 1 - Math.random() * 0.8));
            }
        } catch (SQLException ignored) {}
    }

    private double X_CENTER = 0.0;
    private double Y_CENTER = 0.0;

    private void setMapPane() {
        DragonsCollection.getDragons().forEach(dragon -> {
            double x = dragon.getX() + 464.0 - X_CENTER;
            double y = dragon.getY() + 285.5 - Y_CENTER;
            if (dragon.getX() <= 450.0 + X_CENTER && dragon.getX() >= -450.0 + X_CENTER && dragon.getY() <= 270.0 + Y_CENTER && dragon.getY() >= -270.0 + Y_CENTER) {
                ImageView image = new ImageView("/assets/dragon.png");
                image.setRotate(180);
                image.setFitHeight(20);
                image.setFitWidth(20);
                image.setX(x - 10.0);
                image.setY(y - 10.0);
                image.setOpacity(1);
                image.setMouseTransparent(true);
                Circle circle = new Circle(x, y, 12, users.get(dragon.getCreator()));
                circle.setOpacity(0.5);
                mapPane.getChildren().addAll(circle, image);
                Label label1 = new Label("Dragon " + dragon.getId() + " by " + dragon.getCreator());
                label1.setRotationAxis(new Point3D(1,0,0));
                label1.setRotate(180);
                label1.setTranslateX(x - 50.0);
                label1.setTranslateY(y + 15.0);
                circle.setOnMouseEntered(event -> mapPane.getChildren().add(label1));
                circle.setOnMouseExited(event -> mapPane.getChildren().remove(label1));
                circle.setOnMouseClicked(event -> showAlert("Dragon " + dragon.getId() + " by " + dragon.getCreator(), dragon.toString()));
            }
        });
    }

    private void updateMap() {
        mapPane.getChildren().clear();
        setMapPane();
        playAnimation(toAppearDragons, true);
        playAnimation(toDisappearDragons, false);
    }

    private static final Collection<Dragon> toAppearDragons = new HashSet<>();
    private static final Collection<Dragon> toDisappearDragons = new HashSet<>();
    public static void addToAppear(Collection<Dragon> dragons) {
        toAppearDragons.addAll(dragons);
    }
    public static void addToDisappear(Collection<Dragon> dragons) {
        toDisappearDragons.addAll(dragons);
    }

    private void clearAnimation() {
        toAppearDragons.clear();
        toDisappearDragons.clear();
    }

    private void playAnimation(Collection<Dragon> dragons, boolean appear) {
        dragons.forEach(dragon -> {
            double x = dragon.getX() + 464.0 - X_CENTER;
            double y = dragon.getY() + 285.5 - Y_CENTER;
            if (dragon.getX() <= 450.0 + X_CENTER && dragon.getX() >= -450.0 + X_CENTER && dragon.getY() <= 270.0 + Y_CENTER && dragon.getY() >= -270.0 + Y_CENTER) {
                ImageView imageView;
                if (appear) {
                    imageView = new ImageView(new Image(getClass().getResource("/assets/appear.gif").toString()));
                    imageView.setFitWidth(64);
                    imageView.setFitHeight(36);
                    imageView.setX(x - 30.0);
                    imageView.setY(y - 20.0);
                } else {
                    imageView = new ImageView(new Image(getClass().getResource("/assets/disappear.gif").toString()));
                    imageView.setFitWidth(76.8);
                    imageView.setFitHeight(43.2);
                    imageView.setX(x - 40.0);
                    imageView.setY(y - 20.0);
                }
                imageView.setRotationAxis(new Point3D(1,0,0));
                imageView.setRotate(180);
                mapPane.getChildren().add(imageView);
            }
        });
        dragons.clear();
    }

    private void setSetCenterButton() {
        try {
            double x = Double.parseDouble(xTextField.getText());
            double y = Double.parseDouble(yTextField.getText());
            if (x > 160.0 || x < Long.MIN_VALUE + 450.0 || y > 3.4028235e+38f - 270.0 || y < - 3.4028235e+38f + 270.0) {
                xTextField.setText("");
                yTextField.setText("");
                showAlert("Error", """
                                            -9223372036854775358 <= X <= 160
                                            -3.40282346638528860e+38 + 270 <= Y <= 3.40282346638528860e+38 - 270
                                            """);
            } else {
                X_CENTER = x;
                Y_CENTER = y;
                updateMap();
            }
        } catch (NumberFormatException numberFormatException) {
            xTextField.setText("");
            yTextField.setText("");
            showAlert("Error", MyApplication.getAppLanguage().getString("invalid_inp"));
        }
    }

}
