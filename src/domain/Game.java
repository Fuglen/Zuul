package domain;

import java.util.ArrayList;
import java.util.Random;

class Game {
    private Parser parser;
    private Room currentRoom;
    //Player inventory
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private Inventory inventory = new Inventory(inventoryItems);
    private Player player = new Player();
    private Random rand = new Random();

    //Quests and list of item names and item spawns
    private QuestList questList = new QuestList();
    private QuestList finishedQuestList = new QuestList();
    private boolean tutorial = false;

    public Game() {
        createRooms();
        parser = new Parser();

    }

    // Create rooms and define their exits
    private void createRooms() {
        // Create all the rooms
        Room home, beach, forest, city, work, mcdonalds, park, road, recycle, metal, glass, plastic;

        // Initialize all the rooms with a description
        home = new Room("at home");
        beach = new Room("at the beach");
        forest = new Room("in the forest");
        city = new Room("in the city");
        work = new Room("at work");
        mcdonalds = new Room("at the McDonalds");
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

        city.setExit("work", work);
        city.setExit("park", park);
        city.setExit("mcdonalds", mcdonalds);
        city.setExit("road", road);

        forest.setExit("road", road);

        work.setExit("city", city);

        beach.setExit("road", road);

        park.setExit("city", city);

        mcdonalds.setExit("city", city);

        recycle.setExit("road", road);
        recycle.setExit("metal", metal);
        recycle.setExit("glass", glass);
        recycle.setExit("plastic", plastic);

        metal.setExit("recycling", recycle);

        glass.setExit("recycling", recycle);

        plastic.setExit("recycling", recycle);

        Room.addRoomToList(beach);
        Room.addRoomToList(forest);
        Room.addRoomToList(city);
        Room.addRoomToList(mcdonalds);
        Room.addRoomToList(park);
        Room.addRoomToList(road);
        Room.addRoomToContainerList(glass);
        Room.addRoomToContainerList(metal);
        Room.addRoomToContainerList(plastic);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory
        home.setRoomItem(new Item("shoes"));

        //Player inventory
        /*Item plasticItem, glassItem, metalItem;
        plasticItem = new Item("plastic");
        glassItem = new Item("glass");
        metalItem = new Item("metal");
        Item[] items = new Item[]{plasticItem, glassItem, metalItem};
        for (Item item : items) {
            inventory.addItem(item);
        }*/

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
        questList.addQuest(new Quest("Put on your shoes and go to work!\nHINT: maybe the commands 'collect' and 'use' are useful here.", 100)); // Start the tutorial
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
        } else if (commandWord == CommandWord.USE){
            useItem(command);
        }
        else if (commandWord == CommandWord.TEST){ // Will be removed
            for(int i = 0 ; i < finishedQuestList.getCurrentQuests().size() ; i++){
                System.out.println(finishedQuestList.getCurrentQuests().get(i));
            }
            startQuest();
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

        if(!tutorial){
            System.out.println("You need to put on your shoes before you can go outside.");
        } else {
            Room nextRoom = currentRoom.getExit(direction);

            if (nextRoom == null) {
                System.out.println("There is no door!");
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
            }
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
                    break;
                }
            }
        }
    }

    //Drops an item from the player inventory to the room inventory
    private void useItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
        } else {
            for (int i = 0; i < inventoryItems.size(); i++) {
                if (inventoryItems.get(i).getName().equals(command.getSecondWord())) { // If the written item is in inventory
                    if(!tutorial){
                        if(inventoryItems.get(i).getName().equals("shoes")){ // If "use shoes" tutorial is complete
                            System.out.println("You have put on your shoes, and are ready to go to work!");
                            tutorial = true;
                            Point.addPoint(50);
                            inventory.removeItem(i);
                            questList.getCurrentQuests().get(0).setComplete();
                            finishQuest();
                        } else { // Only when tutorial is ongoing
                            System.out.println("This is not your shoes!");
                        }
                    } else { // If tutorial is complete
                        if(currentRoom == Room.getContainerList().get(0) || currentRoom == Room.getContainerList().get(1) || currentRoom == Room.getContainerList().get(2)){ // Checks if 'use' command is used in one of the container rooms
                            System.out.println("You have put "+inventoryItems.get(i).getName()+" in the container. Good job!");

                            for(int j = 0 ; j < questList.getCurrentQuests().size() ; j++){
                                if(questList.getCurrentQuests().get(j).getQuestType() == 0){
                                    questList.getCurrentQuests().get(j).setRecycleAmount(1);
                                    if(inventoryItems.get(i).getRoom() == currentRoom){
                                        questList.getCurrentQuests().get(j).setRecycleRight(1);
                                        System.out.println(questList.getCurrentQuests().get(j).getRecycleRight());
                                    } else {
                                        questList.getCurrentQuests().get(j).setRecycleWrong(1);
                                        System.out.println(questList.getCurrentQuests().get(j).getRecycleWrong());
                                    }
                                }
                            }
                            inventory.removeItem(i);
                            break;

                        } else { // If not in a container room
                            System.out.println("You can't use that item here.");
                            break;
                        }
                    }
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

    private void startQuest(){ // Starts new quest.
        questList.addQuest(new Quest()); //Create Quests - Will be added when talking to NPC
        Quest questSetting = questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1); // Gets the latest added quest and call it questSetting
        // Creates glass items
        for(int i = 0 ; i < questSetting.getGlassNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                room.setRoomItem(new Item("glass", Room.getContainerList().get(0)));
            } else {
                i--;
            }
        }
        // Creates metal items
        for(int i = 0 ; i < questSetting.getMetalNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                room.setRoomItem(new Item("metal", Room.getContainerList().get(1)));
            } else {
                i--;
            }
        }
        // Creates plastic items
        for(int i = 0 ; i < questSetting.getPlasticNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                room.setRoomItem(new Item("plastic", Room.getContainerList().get(2)));
            } else {
                i--;
            }
        }

    }
    // when quest is completed. Add finished quests to a new list for end screen, and change description.
    private void finishQuest(){
        for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){
            if(questList.getCurrentQuests().get(i).isComplete()){ // Checks boolean complete
                if(questList.getCurrentQuests().get(i).getQuestType() == 100){ // Only for the tutorial
                    questList.getCurrentQuests().get(i).setDescription("Tutorial.");
                }
                if(questList.getCurrentQuests().get(i).getQuestType() == 0){ // for type 0 quests
                    questList.getCurrentQuests().get(i).setDescription("Collect and recycle.");
                }
                finishedQuestList.getCurrentQuests().add(questList.getCurrentQuests().get(i));
                questList.getCurrentQuests().remove(i);
            }
        }

    }

}