package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import presentation.Main;

import java.awt.*;
import java.io.IOException;

public class homeScreenController extends Main {
    @FXML
    private StackPane roadButton;
    @FXML
    private Label roadLabel;
    @FXML
    public void hover() {
        if (roadButton.getOpacity() == 1) {
            roadButton.setOpacity(0.75);
        } else {
            roadButton.setOpacity(1);
        }
    }

    @FXML
    public void goRoad() throws IOException {
        if (!shoes.isVisible() && !shoesInv.isVisible()) {
            changeRooms("road", "road.fxml");
        } else {
            roadLabel.setText("Put on your shoes");
        }
    }
}