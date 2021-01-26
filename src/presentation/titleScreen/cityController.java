package presentation.titleScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.io.IOException;

public class cityController extends Main {
    //Hover-effect for buttons

    @FXML
    private Node tempNode;
    @FXML
    private void nonHover() {
        tempNode.setOpacity(1);
    }
    @FXML
    private void hover(MouseEvent event){
        Node tempNode = event.getPickResult().getIntersectedNode();
        this.tempNode = tempNode;
        event.getPickResult().getIntersectedNode().setOpacity(0.75);
    }
    //Changing rooms
    @FXML
    public void goRoad() throws IOException {
        changeRooms("road", "road.fxml");
    }
    @FXML
    public void goWork() throws IOException {
        changeRooms("work", "work.fxml");
    }
    @FXML
    public void goMcdonalds() throws IOException {
        changeRooms("mcdonalds", "mcdonalds.fxml");
    }
    @FXML
    public void goPark() throws IOException {
        changeRooms("park", "park.fxml");
    }

    //City quests
    @FXML
    private ImageView NaomiKlein;

    public void cityQuest() {
        getGame().startQuest();
    }
}
