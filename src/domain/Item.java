package domain;

class Item {
    //Attributes
    private String name; // Name and type of the item
    private String nameBtn; // Name for buttons
    private String container; // When used checks container

    private static final String[] glassTypes = {"windowPiece", "beerBottle", "brokenLightBulb"};
    private static final String[] glassTypesBtn = {"Window piece", "Beer bottle", "Broken light bulb"};

    private static final String[] metalTypes = {"spoon", "fork", "cookingPot", "keyLock", "screw", "can", "nail"};
    private static final String[] metalTypesBtn = {"Spoon", "Fork", "Cooking pot", "Key lock", "Screw", "Can", "Nail"};

    private static final String[] plasticTypes = {"waterBottle", "bottleCap", "foodWrapper", "groceryBag", "straw"};
    private static final String[] plasticTypesBtn = {"Water bottle", "Bottle cap", "Food wrapper", "Grocery bag", "Straw"};

    private static final String[] paperTypes = {"newspaper", "toiletPaper", "magazine", "envelope"};
    private static final String[] paperTypesBtn = {"News paper", "toilet paper", "Magazine", "Envelope"};

    private static final String[] organicTypes = {"sandwich", "bananaPeel", "appleCarcass", "eggShells"};
    private static final String[] organicTypesBtn = {"Sandwich", "Banana Peel", "Apple Carcass", "Egg Shells"};


    //Constructor
    public Item(String name) { // For test items / tutorial
        this.name = name;
    }

    public Item(String name, String nameBtn, String container){
        this.name = name;
        this.nameBtn = nameBtn;
        this.container = container;
    }

    //Methods
    public String getName() {
        return name;
    }

    public String getContainer() {
        return container;
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

    public static String[] getPaperTypes() {
        return paperTypes;
    }

    public static String[] getPaperTypesBtn() {
        return paperTypesBtn;
    }

    public static String[] getOrganicTypes() {
        return organicTypes;
    }

    public static String[] getOrganicTypesBtn() {
        return organicTypesBtn;
    }
}