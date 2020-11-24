package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private void EndGame () {
        Platform.exit();
    }
    @FXML
    private void goMain(ActionEvent event) throws IOException {
        Stage homeStage = FXMLLoader.load(getClass().getResource("home.fxml"));
        homeStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}