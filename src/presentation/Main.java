package presentation;

import interfaceI.DomainI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import domain.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static DomainI game;
    public ArrayList<Item> inventoryItems = new ArrayList<>();
    public Inventory inventory = new Inventory(inventoryItems);
    public TextField inventoryField = new TextField();

    @FXML
    public ImageView bottle, shoes;

    @FXML
    public Button printInventory;

    @FXML
    public Label label;

    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("titleScreen/titleScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public DomainI getGame() {
        return game;
    }

    public static Stage getStage() {return stage;}

    public void changeRooms(String roomName, String filepath) throws IOException {
        getGame().goRoom(new Command(CommandWord.GO, roomName));
        Parent loader = FXMLLoader.load(getClass().getResource(filepath));
        stage.setScene(new Scene(loader));
    }

    @FXML
    private void EndGame () {
        Platform.exit();
    }

<<<<<<< HEAD
=======
    //Collect Items with these methods
    public void collectBottle() {
        getGame().collectItem(new Command(CommandWord.COLLECT, "waterBottle"));
        bottle.setVisible(false);
        Item item = new Item("bottle");
        game.getInventory().addItem(item);

    }

    public void setTextfieldText() {
        inventoryField.setText("Your inventory: " + game.getInventory().printInventory());
    }

>>>>>>> 59b13ea465bdb17651ad3d5753ee4feb57c25234
    public static void main(String[] args) {
        game = new Game();
        launch(args);
    }
}
