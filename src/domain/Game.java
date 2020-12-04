package domain;

import java.util.ArrayList;

class Game {
    private Parser parser;
    private Room currentRoom;
    //Player inventory
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private Inventory inventory = new Inventory(inventoryItems);
    private Player player = new Player();

    //Quests
    private QuestList questList = new QuestList();


    public Game() {
        createRooms();
        parser = new Parser();
    }

    // Create rooms and define their exits
    private void createRooms() {
        // Create all the rooms
        Room home, beach, forest, city, workplace, McDonalds, park, road, recycle, metal, glass, plastic;

        // Initialize all the rooms with a description
        home = new Room("at home");
        beach = new Room("at the beach");
        forest = new Room("in the forest");
        city = new Room("in the city");
        workplace = new Room("at work");
        McDonalds = new Room("at the McDonalds");
        park = new Room("at the park");
        road = new Room("on the road again");
        recycle = new Room ("at the recycle center.\nYou see three types of containers");
        metal = new Room ("at the metal container");
        glass = new Room ("at the glass container");
        plastic = new Room ("at the plastic container");

        // Define exits to all rooms
        home.setExit("road", road);

        road.setExit("home", home);
        road.setExit("forest", forest);
        road.setExit("city", city);
        road.setExit("beach", beach);
        road.setExit("recycling", recycle);

        city.setExit("work", workplace);
        city.setExit("park", park);
        city.setExit("mcdonalds", McDonalds);

        forest.setExit("road", road);

        workplace.setExit("city", city);

        beach.setExit("road", road);

        park.setExit("city", city);

        McDonalds.setExit("city", city);

        recycle.setExit("road", road);
        recycle.setExit("metal", metal);
        recycle.setExit("glass", glass);
        recycle.setExit("plastic", plastic);

        metal.setExit("recycling", recycle);

        glass.setExit("recycling", glass);

        plastic.setExit("recycling", plastic);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory
        home.setRoomItem(new Item("tool"));
        home.setRoomItem(new Item("tool"));

        //Player inventory
        Item plasticItem, glassItem, metalItem;
        plasticItem = new Item("plastic");
        glassItem = new Item("glass");
        metalItem = new Item("metal");
        Item[] items = new Item[]{plasticItem, glassItem, metalItem};
        for (Item item : items) {
            inventory.addItem(item);
        }

        //Create NPC object
        NPC npcs = new NPC();
    }

    public void play() {
        printWelcome();


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to our game!");
        System.out.println("Our game is a new, recycling adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("Choose a first name for your character.");
        System.out.println("Choose wisely, as it can't be changed.");
        player.createPlayer();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            inventory.printInventory();
        } else if (commandWord == CommandWord.DROP) {
            dropItem(command);
        } else if (commandWord == CommandWord.COLLECT) {
            collectItem(command);
        } else if (commandWord == CommandWord.QUESTS) {
            questList.addQuest(new Quest()); //Create Quests - Will be added when talking to NPC
            questList.printQuests();
        }
        return wantToQuit;
    }

    //Help command
    private void printHelp() {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    //Drops an item from the player inventory to the room inventory
    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else {
            for (int i = 0; i < inventoryItems.size(); i++) {
                if (inventoryItems.get(i).getName().equals(command.getSecondWord())) {
                    currentRoom.setRoomItem(inventoryItems.get(i));
                    inventory.removeItem(i);
                    System.out.println("You dropped: " + command.getSecondWord());
                }
            }
        }
    }

    //Collect an item from the room inventory and puts it into the player inventory
    private void collectItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Collect what?");
        } else {
            for (int i = 0; i < currentRoom.getRoomItems(); i++) {
                if (currentRoom.getRoomItem(i).getName().equals(command.getSecondWord())) {
                    inventory.addItem(currentRoom.getRoomItem(i));
                    System.out.println("You collected: " + currentRoom.getRoomItem(i).getName());
                    currentRoom.removeRoomItem(i);
                }
            }
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

}