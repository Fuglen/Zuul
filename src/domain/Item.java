package domain;

import java.util.Random;

class Item {
    //Attributes
    private String name; // Name and type of the item
    private Room room; // When used checks container



    //Constructor
    public Item(String name) {
        this.name = name;
    }

    public Item(String name, Room room){
        Random rand = new Random();
        this.name = name;
        this.room = room;
    }

    //Methods
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}