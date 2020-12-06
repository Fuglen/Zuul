package domain;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
        Timer.getWorkHome().add(work);
        Timer.getWorkHome().add(home);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory
        home.setRoomItem(new Item("shoes"));

        //Create NPC object
        NPC npcs = new NPC();
    }
    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            if(Timer.isFired()){
                break;
            }
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        // Shows endscreen
        while(Timer.isFired() && !finished){
            System.out.println("You got fired...");
            System.out.println("Final score: "+Point.getPoint());
            System.out.println("You kept your job in "+Timer.getDay()+" day(s).");
            System.out.println("You completed "+finishedQuestList.getCurrentQuests().size()+" quests:");
            for(int i = 0 ; i < finishedQuestList.getCurrentQuests().size() ; i++){
                System.out.println(finishedQuestList.getCurrentQuests().get(i));
            }
            while(!finished){
                System.out.println("Type 'retry' to try again.");
                System.out.println("Type 'quit' to quit the game.");
                Scanner scannerRetry = new Scanner(System.in);
                String retry = scannerRetry.nextLine();
                if(retry.equals("retry")){
                    Timer.setFired();
                    Game woz = new Game();
                    woz.play();
                } else if (retry.equals("quit")){
                    finished = true;
                    break;
                } else {
                    System.out.println("I didn't get that.");
                }
            }

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
        currentRoom.printRoomItems();
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
        } else if (commandWord == CommandWord.TEST){ // Will be removed
            startQuest();
        } else if(commandWord == CommandWord.TESTER){
            for(int i = 0 ; i < finishedQuestList.getCurrentQuests().size() ; i++){
                System.out.println(finishedQuestList.getCurrentQuests().get(i));
            }
        } else if(commandWord == CommandWord.TEST2){
            Timer.setFired();
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
                if(currentRoom == Timer.getWorkHome().get(0)){
                    currentRoom = Timer.getWorkHome().get(1);
                    newDay();
                }
                Timer.setMovesMade();
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
                    Timer.setMovesMade();
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
                    Timer.setMovesMade();
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
                            archiveQuest();
                        } else { // Only when tutorial is ongoing
                            System.out.println("This is not your shoes!");
                        }
                    } else { // If tutorial is complete
                        if(currentRoom == Room.getContainerList().get(0) || currentRoom == Room.getContainerList().get(1) || currentRoom == Room.getContainerList().get(2)){ // Checks if 'use' command is used in one of the container rooms
                            System.out.println("You have put "+inventoryItems.get(i).getName()+" in the container. Good job!");
                            for(int j = 0 ; j < questList.getCurrentQuests().size() ; j++){
                                if(questList.getCurrentQuests().get(j).getQuestType() == 0){
                                    Quest ThisQuest = questList.getCurrentQuests().get(j);
                                    ThisQuest.setRecycleAmount(1); // Increases recycleAmount by 1
                                    if(inventoryItems.get(i).getRoom() == currentRoom){ // Checks if the item is correctly recycled
                                        ThisQuest.setRecycleRight(1);
                                    } else {
                                        ThisQuest.setRecycleWrong(1);
                                    }
                                    if(ThisQuest.getRecycleAmount() == ThisQuest.getCollectAmount()){ // If all the trash has been recycled
                                        ThisQuest.setDescription("You recycled all the trash you were asked to. Talk to NPC to complete the quest.");
                                        System.out.println(ThisQuest.getDescription());
                                    } else {
                                        ThisQuest.updateDescriptionZero(); // Zero for the quest type 0
                                    }
                                    j = questList.getCurrentQuests().size() - 1;
                                }
                            }
                            inventory.removeItem(i);
                            Timer.setMovesMade();
                        } else { // If not in a container room
                            System.out.println("You can't use that item here.");
                        }
                        break;
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
        Quest newQuest = new Quest();
        int count = 0;
        for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){ // Checks if the type of quest already exists
            if(questList.getCurrentQuests().get(i).getQuestType() == newQuest.getQuestType()){
                count = count + 1;
            }
        }
        if(count > 0){ // If the quest type already exists, start the method over.
            if(questList.getCurrentQuests().size() == Quest.getMaxQuests()){
                System.out.println("Something went wrong. Too many quest is active.");
            } else {
                startQuest();
            }

        } else {
            if(newQuest.getQuestType() == 0){
                questList.addQuest(newQuest); //Create Quests - Will be added when talking to NPC
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
            if(newQuest.getQuestType() == 1){
                questList.addQuest(newQuest);
            }
            if(newQuest.getQuestType() == 2){
                questList.addQuest(newQuest);
            }
        }
    }
    // Completes the quest
    private void completeQuest(){
        for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){
            if(questList.getCurrentQuests().get(i).getQuestType() == 0){ // type 0 quests
                if(questList.getCurrentQuests().get(i).getCollectAmount() == questList.getCurrentQuests().get(i).getRecycleAmount()){
                    Quest completeQuest = questList.getCurrentQuests().get(i);
                    completeQuest.setComplete();
                    completeQuest.setPoints((completeQuest.getRecycleRight() * 10) + (completeQuest.getRecycleWrong() * 2));
                    System.out.println("You recycled "+completeQuest.getRecycleRight()+" things correct.");
                    System.out.println("You recycled "+completeQuest.getRecycleWrong()+" things wrong.");
                    System.out.println("You get "+ completeQuest.getPoints() +" points for completing the quest.");
                    Point.addPoint(completeQuest.getPoints());
                }
            }
        }
    }

    // when quest is completed. Add finished quests to a new list for end screen, and change description.
    private void archiveQuest(){
        for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){
            if(questList.getCurrentQuests().get(i).isComplete()){ // Checks boolean complete
                Quest questDone = questList.getCurrentQuests().get(i);
                if(questDone.getQuestType() == 100){ // Only for the tutorial
                    questDone.setDescription("Tutorial.");
                }
                if(questDone.getQuestType() == 0){ // for type 0 quests
                    questDone.setDescription("Collect and recycle - Completed at day "+Timer.getDay());
                } else if(questDone.getQuestType() == 1){ // for type 0 quests
                    questDone.setDescription("Type 1 - Completed at day "+Timer.getDay());
                } else if(questDone.getQuestType() == 2){ // for type 0 quests
                    questDone.setDescription("Type 2 - Completed at day "+Timer.getDay());
                }
                finishedQuestList.getCurrentQuests().add(questDone);
                questList.getCurrentQuests().remove(i);
            }
        }
    }

    // New day
    private void newDay(){
        int questsBeforeNewDay = finishedQuestList.getCurrentQuests().size();
        archiveQuest();
        archiveQuest();
        int questsAfterNewDay = finishedQuestList.getCurrentQuests().size();
        Timer.setDay();
        int pointsGainedThisDay = 0;
        int movesLate = 0;
        if(Timer.getWorkTimer() < Timer.getMovesMade()){
            movesLate = Timer.getMovesMade() - Timer.getWorkTimer();
            Timer.setWorkEffort(Timer.getWorkEffort() + movesLate);
            System.out.println("You are late for work. If you keep getting late, you will get fired.");
            System.out.println("Today you were "+movesLate+" moves late. And you are a total of "+Timer.getWorkEffort()+" moves late.");
            System.out.println("If your total exceeds "+Timer.getWorkEffortThreshold()+" you will get fired.");
        } else {
            pointsGainedThisDay = Timer.getWorkTimer() - Timer.getMovesMade();
            Point.addPoint(pointsGainedThisDay);
            System.out.println("You came early to work and got "+pointsGainedThisDay+" points.");
            System.out.println("A new day has started, and you are ready for work.");
        }
        if(Timer.getWorkEffortThreshold() < Timer.getWorkEffort()){
            Timer.setFired();
        }
        Timer.setWorkTimer(Timer.getWorkTimer() - 1);
        Timer.setMovesMade(-1);
        System.out.println("You completed "+(questsAfterNewDay - questsBeforeNewDay)+" quests yesterday.");
    }
}