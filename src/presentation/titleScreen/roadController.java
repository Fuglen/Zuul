package presentation.titleScreen;

import interfaceI.DomainI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Main;

import java.beans.EventHandler;
import java.io.IOException;

public class roadController extends Main {
    //Hover-effect for buttons
    @FXML
    private Node tempNode;
    @FXML
    public void nonHover() {
        tempNode.setOpacity(1);
    }
    @FXML
    public void hover(MouseEvent event){
        Node tempNode = event.getPickResult().getIntersectedNode();
        this.tempNode = tempNode;
        event.getPickResult().getIntersectedNode().setOpacity(0.75);
    }

    //Changing rooms
    @FXML
    public void goForest() throws IOException {
        changeRooms("forest", "forest.fxml");
    }
    @FXML
    public void goCity() throws IOException {
        changeRooms("city", "city.fxml");
    }
    @FXML
    public void goBeach() throws IOException {
        changeRooms("beach", "beach.fxml");
    }
    @FXML
    public void goRecyclingHome() throws IOException {
        changeRooms("recycling", "recyclingHome.fxml");
    }
    @FXML
    public void goHome() throws IOException {
        changeRooms("home", "homeScreen.fxml");
    }

}
