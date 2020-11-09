package worldofzuul;

import java.util.ArrayList;

public class Item {
    //Attributes
    private String name;
    private Room room;

    //Constructor
    public Item(String name) {
        this.name = name;
    }
    public Item(String name, Room room) {
        this.name = name;
        this.room = room;
    }

    //Methods
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "'" + name + '\'';
    }
}