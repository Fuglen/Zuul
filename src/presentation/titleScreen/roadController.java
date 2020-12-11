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
/*    //Hover-effect for buttons
    @FXML
    private Node tempNode;
    @FXML
    private StackPane tempStackPane;
    @FXML
    private StackPane forestButton;
    @FXML
    private StackPane cityButton;
    @FXML
    private StackPane beachButton;
    @FXML
    private StackPane recyclingHomeButton;
    @FXML
    private StackPane homeButton;

    @FXML
    public void anchorPaneHover(MouseEvent event) {
        stackPaneHover(event);
    }
    @FXML
    public void stackPaneHover(MouseEvent event){
        StackPane stackPane = (StackPane) event.getPickResult().getIntersectedNode();
        this.tempStackPane = stackPane;
        if (tempStackPane.getOpacity() == 1) {
            tempStackPane.setOpacity(0.75);
        } else {
            tempStackPane.setOpacity(1);
        }
    }*/

    //Changing rooms
    @FXML
    public void goForest(MouseEvent event) throws IOException {
        changeRooms("forest", "forest.fxml");
    }
    @FXML
    public void goCity(MouseEvent event) throws IOException {
        changeRooms("city", "city.fxml");
    }
    @FXML
    public void goBeach(MouseEvent event) throws IOException {
        changeRooms("beach", "beach.fxml");
    }
    @FXML
    public void goRecyclingHome(MouseEvent event) throws IOException {
        changeRooms("recycle", "recyclingHome.fxml");
    }
    @FXML
    public void goHome(MouseEvent event) throws IOException {
        changeRooms("home", "homeScreen.fxml");
    }
}
