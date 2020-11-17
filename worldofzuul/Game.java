package worldofzuul;

import java.util.ArrayList;

public class Game {
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
        Room home, beach, forest, city, workplace, McDonalds, park, road;
        Quest quest1, quest2;

        // Initialize all the rooms with a description
        home = new Room("at home");
        beach = new Room("at the beach");
        forest = new Room("in the forest");
        city = new Room("in the city");
        workplace = new Room("at work");
        McDonalds = new Room("at the McDonalds");
        park = new Room("at the park");
        road = new Room("on the road again");

        // Define exits to all rooms
        home.setExit("Road", road);

        road.setExit("Home", home);
        road.setExit("Forest", forest);
        road.setExit("City", city);
        road.setExit("Beach", beach);

        city.setExit("Work", workplace);
        city.setExit("Park", park);
        city.setExit("McDonalds", McDonalds);

        forest.setExit("Road", road);

        workplace.setExit("City", city);

        beach.setExit("Road", road);

        park.setExit("City", city);

        McDonalds.setExit("City", city);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory
        home.setRoomItem(new Item("tool"));

        //Player inventory
        Item plastic, glass, metal;
        plastic = new Item("plastic");
        glass = new Item("glass");
        metal = new Item("metal");
        Item[] items = new Item[]{plastic, glass, metal};
        for (Item item : items) {
            inventory.addItem(item);
        }

        //Create Quests
        quest1 = new Quest("Open your inventory", 10, 0);
        quest2 = new Quest("Open your inventory2", 10, 0);
        Quest[] quests = new Quest[]{quest1, quest2};
        for (Quest q : quests) {
            questList.addQuest(q);
        }
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