package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Dragons collection manager");
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/fxml/authentication.fxml")).load(), 1024, 720));
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }
    public static void open() {
        launch();
    }

    public static Font appFont(double size) {
        return Font.loadFont(MyApplication.class.getResourceAsStream("/assets/game-of-thrones.ttf"), size);
    }

    public static void openTableWindow() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Dragons collection manager");
            Scene scene = new Scene(new FXMLLoader(MyApplication.class.getResource("/fxml/table.fxml")).load(), 1024, 720);
            scene.getStylesheets().add("/css/table.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}