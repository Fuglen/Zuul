package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mcdonaldsController {
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
    public void goCity(MouseEvent event) throws IOException {
        Parent startGameParent = FXMLLoader.load(getClass().getResource("city.fxml"));
        Scene homeViewScene = new Scene(startGameParent);

        //This line gets the stage information
        Stage start = (Stage)((Node)event.getSource()).getScene().getWindow();
        start.setScene(homeViewScene);
        start.show();
    }
}
