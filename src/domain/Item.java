package domain;

class Item {
    //Attributes
    private String name; // Name and type of the item
    private Room room; // When used checks container
    private static String[] glassTypes = {"windowPiece", "beerBottle", "brokenLightBulb"};
    private static String[] metalTypes = {"spoon", "fork", "cookingPot", "keyLock", "screw", "can", "nail"};
    private static String[] plasticTypes = {"waterBottle", "bottleCap", "foodWrapper", "groceryBag", "straw"};


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

    public static String[] getGlassTypes() {
        return glassTypes;
    }

    public static String[] getMetalTypes() {
        return metalTypes;
    }

    public static String[] getPlasticTypes() {
        return plasticTypes;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}