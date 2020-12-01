package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.effect.ColorAdjust;

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