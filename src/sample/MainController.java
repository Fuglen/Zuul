package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
}