package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class HomeController {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain() {

    }
}
