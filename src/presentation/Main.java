package presentation;

import interfaceI.DomainI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import domain.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static DomainI game;
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private Inventory inventory = new Inventory(inventoryItems);

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

    //Collect Items with these methods
    public void collectBottle() {
        getGame().collectItem(new Command(CommandWord.COLLECT, "waterBottle"));
        bottle.setVisible(false);
        Item item = new Item("bottle");
        getGame().getInventory().addItem(item);
        getGame().printInventory();
    }

    public void collectShoes(){
        getGame().collectItem(new Command(CommandWord.COLLECT, "shoes"));
        shoes.setVisible(false);
        Item item = new Item("shoes");
        getGame().getInventory().addItem(item);
    }

    public void setLabelText() {
        label.setText(getGame().printInventory());
    }

    public static void main(String[] args) {
        game = new Game();
        launch(args);
    }
}
