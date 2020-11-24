package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain() throws IOException {
        Stage homeStage = FXMLLoader.load(getClass().getResource("home.fxml"));
        homeStage.show();
    }
}