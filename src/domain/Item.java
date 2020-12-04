package domain;

class Item {
    //Attributes
    private String name; // Name of the item
    private Room room; // Item added to room
    private String type; // What kind of item is it
    static final String[] typeList = {"metal", "glass", "plastic"};
    static final String[] roomList = {""};



    //Constructor
    public Item(String name) {
        this.name = name;
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