package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Home extends Application {
    private static final String UI_FILE = "home.fxml";
    @Override
    public void start(Stage stage_dummy) throws Exception{
        Stage stage = FXMLLoader.load(getClass().getResource(UI_FILE));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
