package presentation.titleScreen;

import domain.Command;
import domain.CommandWord;
import domain.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class beachController extends Main implements Initializable {

    @FXML
    private ImageView oldShoes;

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
    public void goRoad(MouseEvent event) throws IOException {
        changeRooms("road", "road.fxml");
    }

    @FXML
    public void collectoldShoes() {
        getGame().collectItem(new Command(CommandWord.COLLECT, "oldShoes"));
        oldShoes.setVisible(false);
    }

    public void showOldShoes() {
        itemShow(oldShoes,"oldShoes");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOldShoes();
    }
}
