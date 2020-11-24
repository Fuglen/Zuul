package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage_dummy) throws Exception{
        Stage stage = FXMLLoader.load(getClass().getResource("window.fxml"));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}