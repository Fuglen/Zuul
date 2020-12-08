package domain;

class Item {
    //Attributes
    private String name; // Name and type of the item
    private String nameBtn; // Name for buttons
    private Room room; // When used checks container

    private static final String[] glassTypes = {"windowPiece", "beerBottle", "brokenLightBulb"};
    private static final String[] glassTypesBtn = {"Window piece", "Beer bottle", "Broken light bulb"};

    private static final String[] metalTypes = {"spoon", "fork", "cookingPot", "keyLock", "screw", "can", "nail"};
    private static final String[] metalTypesBtn = {"Spoon", "Fork", "Cooking pot", "Key lock", "Screw", "Can", "Nail"};

    private static final String[] plasticTypes = {"waterBottle", "bottleCap", "foodWrapper", "groceryBag", "straw"};
    private static final String[] plasticTypesBtn = {"Water bottle", "Bottle cap", "Food wrapper", "Grocery bag", "Straw"};


    //Constructor
    public Item(String name) { // For test items
        this.name = name;
    }

    public Item(String name, String nameBtn, Room room){
        this.name = name;
        this.nameBtn = nameBtn;
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

    public String getNameBtn() {
        return nameBtn;
    }

    public static String[] getGlassTypesBtn() {
        return glassTypesBtn;
    }

    public static String[] getMetalTypesBtn() {
        return metalTypesBtn;
    }

    public static String[] getPlasticTypesBtn() {
        return plasticTypesBtn;
    }
}