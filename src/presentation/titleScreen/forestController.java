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

public class forestController extends Main {
    //Hover-effect for buttons
    @FXML
    private StackPane roadButton;
    @FXML
    public void hover() {
        if (roadButton.getOpacity() == 1) {
            roadButton.setOpacity(0.75);
        } else {
            roadButton.setOpacity(1);
        }
    }
    //Changing rooms
    @FXML
    public void goRoad() throws IOException {
        changeRooms("road", "road.fxml");
    }
}
