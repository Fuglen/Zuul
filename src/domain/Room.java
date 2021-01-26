package domain;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private NPC npc;
    public static ArrayList<Item> roomItems = new ArrayList<>();
    public static Inventory roomInventory = new Inventory(roomItems);
    private static ArrayList<Room> roomList = new ArrayList<Room>();
    private static ArrayList<Room> containerList = new ArrayList<Room>();
    private static ArrayList<Room> allRoomList = new ArrayList<Room>();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        addToAllRoomList(this);
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
        returnString += "\nList of room items: " + printRoomItems() + "\nScore: " + Point.getPoint() + "\nDay: " + Timer.getDay()+"\nPeople in the area: ";
        if(this.npc != null){
            returnString += getNPC().getName();
        } else {
            returnString += "None";
        }

        if(Timer.getDay() != 0){
            returnString += "\nMoves: " + Timer.getMovesMade() + "/" + Timer.getWorkTimer();
        }
        return returnString;
    }

    public void addNPC(NPC npc) {
        this.npc = npc;
    }

    public NPC getNPC() {
        return npc;
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
        }
        return roomItemsText;
    }

    public static void addRoomToList(Room room){
        Room.roomList.add(room);
    }

    public static ArrayList<Room> getRoomList() {
        return roomList;
    }

    public static void addRoomToContainerList(Room room){
        Room.containerList.add(room);
    }

    public static ArrayList<Room> getContainerList() {
        return containerList;
    }

    public static void addToAllRoomList(Room room){
        Room.allRoomList.add(room);
    }

    public static ArrayList<Room> getAllRoomList() {
        return allRoomList;
    }
}

