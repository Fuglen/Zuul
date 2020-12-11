package presentation.titleScreen;

import domain.Command;
import domain.CommandWord;
import domain.Item;
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

public class homeScreenController extends Main {
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
    public void collectShoes(){
        getGame().collectItem(new Command(CommandWord.COLLECT, "shoes"));
        shoes.setVisible(false);
        Item item = new Item("shoes");

    }
    @FXML
    public void goRoad() throws IOException {
        changeRooms("road", "road.fxml");
    }
}