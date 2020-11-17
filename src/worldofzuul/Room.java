package worldofzuul;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> roomItems = new ArrayList<>();
    private Inventory roomInventory = new Inventory(roomItems);

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += " | List of room items: " + printRoomItems();
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
    public Item getRoomItem(int i) {
        return roomItems.get(i);
    }
    public int getRoomItems() {
        return roomItems.size();
    }
    public void setRoomItem(Item item) {
        roomItems.add(item);
    }
    public void removeRoomItem(int i) {
        roomItems.remove(i);
    }
    public String printRoomItems() {
        String roomItemsText = "";
        for (int i = 0; i < roomItems.size(); i++) {
            roomItemsText += roomItems.get(i).getName() + " ";
        } return roomItemsText;
    }
}

