package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;

public class workController extends Main {
    //Hover-effect for buttons
    @FXML
    private StackPane cityButton;
    @FXML
    public void hover() {
        if (cityButton.getOpacity() == 1) {
            cityButton.setOpacity(0.75);
        } else {
            cityButton.setOpacity(1);
        }
    }
    //Changing rooms
    @FXML
    public void goCity() throws IOException {
        changeRooms("city", "city.fxml");
    }
}
