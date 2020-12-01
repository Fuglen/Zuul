package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain() throws IOException {
        Stage windowStage = FXMLLoader.load(getClass().getResource("window.fxml"));
        windowStage.show();
    }
}
