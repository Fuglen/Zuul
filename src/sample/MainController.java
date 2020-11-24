package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain(ActionEvent event) throws IOException {
        /*Stage homeStage = FXMLLoader.load(getClass().getResource("home.fxml"));
        homeStage.show();*/
    }
}