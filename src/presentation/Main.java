package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Stage stage = FXMLLoader.load(getClass().getResource("window.fxml"));
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}