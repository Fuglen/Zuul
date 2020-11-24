package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
