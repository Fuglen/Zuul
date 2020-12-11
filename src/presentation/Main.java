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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import domain.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static Stage stage = new Stage();
    private static DomainI game;
    public ArrayList<Item> inventoryItems = new ArrayList<>();
    public Inventory inventory = new Inventory(inventoryItems);
/*    public TextField inventoryField = new TextField();*/

    @FXML
    public ImageView bottle, shoes, shoesInv;

    @FXML
    public Button use, drop;

    @FXML
    public Label label;

    @FXML
    public ScrollPane inventoryScroll;

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
        game.getInventory().addItem(item);
    }

    public void collectShoes(){
        getGame().collectItem(new Command(CommandWord.COLLECT, "shoes"));
        shoes.setVisible(false);
        shoesInv.setVisible(true);

    }

    //Use items
    public void useShoes() {
        game.useItem(new Command(CommandWord.USE, "shoes"));
    }

    //show use and drop button
    public void showUseDrop (){
        use.setVisible(true);
        drop.setVisible(true);
    }


    public static void main(String[] args) {
        game = new Game();
        launch(args);
    }
}
