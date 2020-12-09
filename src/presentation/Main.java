package presentation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("titleScreen/titleScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void EndGame () {
        Platform.exit();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
