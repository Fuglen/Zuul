package worldofzuul;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Inventory items = new Inventory();
    private Player player = new Player();


    public Game() {
        createRooms();
        parser = new Parser();
    }

    // Create rooms and define their exits
    private void createRooms() {
        // Create all the rooms
        Room home, beach, forest, city, workplace, McDonalds, park, road;

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
        city.setExit("MacDonalds", McDonalds);

        forest.setExit("Road", road);

        workplace.setExit("Road", road);

        beach.setExit("Road", road);

        park.setExit("Road", road);

        // Set the starting room to home
        currentRoom = home;
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
        } else if (commandWord == CommandWord.INVENTORY){
            items.printInventory();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
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

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
    
}
