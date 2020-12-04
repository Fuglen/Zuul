package domain;

class Item {
    //Attributes
    private String name; // Name and type of the item
    private Room room; // When used checks container



    //Constructor
    public Item(String name) { // For test items
        this.name = name;
    }

    public Item(String name, Room room){
        this.name = name;
        this.room = room;
    }

    //Methods
    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}