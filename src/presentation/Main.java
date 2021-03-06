package presentation;

import interfaceI.DomainI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import domain.*;
import presentation.titleScreen.beachController;
import presentation.titleScreen.cityController;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public static Stage stage = new Stage();
    public static DomainI game;

    @FXML
    public ImageView bottle, shoes, shoesInv, oldShoes, dirtyScarf;

    @FXML
    public ImageView[] images = {oldShoes, dirtyScarf};

    public String[] itemNames = {"dirtyScarf", "oldShoes"};

    @FXML
    public String[] agd;

    @FXML
    public Button use, drop;

    @FXML
    public Label points;

    @FXML
    public ScrollPane inventoryScroll;

    @FXML
    public TextArea gameText;

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
        game.store();
    }

    public void collectShoes(){
        getGame().collectItem(new Command(CommandWord.COLLECT, "shoes"));
        shoes.setVisible(false);
        shoesInv.setVisible(true);
    }

    public void pointsText(){
        points.setText("Score: " + Integer.toString(Point.getPoint()));
    }

    public void setGameText(){
        gameText.setText(game.getCurrentRoom().getLongDescription());
    }

    //Use items
    public void useShoes() {
        game.useItem(new Command(CommandWord.USE, "shoes"), game.getCurrentRoom());
        shoesInv.setVisible(false);
    }

   public void getInventory(){
        game.getInventory();
   }

    public void getCurrentRoom(){
        game.getCurrentRoom();
    }

    public void itemShow(ImageView imageView, String itemName) {
        for (int i = 0; i < game.getCurrentRoom().getRoomItems(); i++) {
            if (game.getCurrentRoom().getRoomItem(i).getName().equals(itemName)) {
                imageView.setVisible(true);
            }
        }
/*        for (int i = 0; i < game.getCurrentRoom().getRoomItems(); i++) {
            for (int j = 0; j < itemNames.length; j++) {
                if (game.getCurrentRoom().getRoomItem(i).getName().equals(itemNames[j])) {
                    images[j].setVisible(true);
                }
            }
        }*/
    }

    //show use and drop button
    public void showUseDrop (){
        use.setVisible(true);
        drop.setVisible(true);
    }

    public static void main(String[] args) {
        game = new Game();
        game.load();
        launch(args);
    }
}
