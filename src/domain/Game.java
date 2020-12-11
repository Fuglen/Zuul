package domain;

import interfaceI.DomainI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game implements DomainI {
    private Parser parser;
    private Room currentRoom;
    //Player inventory
    private ArrayList<Item> inventoryItems = new ArrayList<>();
    private Inventory inventory = new Inventory(inventoryItems);
    private Player player = new Player();
    private Random rand = new Random();
    private String filename = "dataFile.txt";


    //Quests and list of item names and item spawns
    private QuestList questList = new QuestList();
    private QuestList finishedQuestList = new QuestList();
    private boolean tutorial = false;


    public Game() {
        createRooms();
        parser = new Parser();

    }

    @Override
    public void load() {
        inventory.load(filename);
    }
    @Override
    public void store() {
        inventory.store(filename);
    }

    // Create rooms and define their exits
    private void createRooms() {
        // Create all the rooms
        Room home, beach, forest, city, work, mcdonalds, park, road, recycle;

        // Initialize all the rooms with a description
        home = new Room("at home");
        beach = new Room("at the beach");
        forest = new Room("in the forest");
        city = new Room("in the city");
        work = new Room("at work");
        mcdonalds = new Room("at the McDonalds");
        park = new Room("at the park");
        road = new Room("on the road again");
        recycle = new Room ("at the recycle center.\nYou see five types of containers:\nType 'use *Item name*' for dropping the trash in a container. ");

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

        // List of rooms items can spawn in
        Room.addRoomToList(beach);
        Room.addRoomToList(forest);
        Room.addRoomToList(city);
        Room.addRoomToList(mcdonalds);
        Room.addRoomToList(park);
        Room.addRoomToList(road);

        // Container room
        Room.addRoomToContainerList(recycle);

        // List used for starting a new day
        Timer.getWorkHome().add(work);
        Timer.getWorkHome().add(home);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory for the start of the game
        home.setRoomItem(new Item("shoes"));

        //Create NPC in room
        road.addNPC(new NPC("Edward Abbey", "My name is Edward Abbey.\nI'm writing a novel. You should check it out when I'm done.\nI think I will call it: The Monkey Wrench Gang.", false));
        city.addNPC(new NPC("Naomi Klein", "My name is Naomi Klein.\nMaybe you have read my book 'No Logo'?", true));
        park.addNPC(new NPC("Mark Lynas", "My name is Mark Lynas.\nI'm a journalist that focus on climate change.\nI think there is a 50–50 chance we can avoid a devastating rise in global temperature.", true));
        mcdonalds.addNPC(new NPC("John Muir", "My name is John Muir.\nBut maybe you know as 'John of the Mountains' or 'Father of the National Parks'.", true));
        forest.addNPC(new NPC("Chico Mendes", "My name is Chico Mendes.\nI once fought to preserve the Amazon rainforest.", false));

        // Create achievements
        Achievements.getAchievementList().add(new Achievements("Book worm.", Achievements.getZeroDescription(),Achievements.getNumToComplete0()));
        Achievements.getAchievementList().add(new Achievements("Workaholic.", Achievements.getFirstDescription(),Achievements.getNumToComplete1()));
        Achievements.getAchievementList().add(new Achievements("Mother Nature’s champion.", Achievements.getSecondDescription(),Achievements.getNumToComplete2()));
        Achievements.getAchievementList().add(new Achievements("Friend of the people.", Achievements.getThirdDescription(),Achievements.getNumToComplete3()));
    }

    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            if(Timer.isFired()){
                break;
            }
            // Checks for achievement completion after every command
            achievementCheck();
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        // Shows end screen when get fired
        while(Timer.isFired() && !finished){
            // Print end screen with all stats throw the game
            printEndScreen();
            // Can start the game over or quit
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
        System.out.println("#########################");
        System.out.println("# THANK YOU FOR PLAYING #");
        System.out.println("#        GOODBYE        #");
        System.out.println("#########################");
    }

    // Welcome text and player creation. Also starts tutorial quest
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
        // Start the tutorial
        questList.addQuest(new Quest("Put on your shoes and get outside!\nHINT: maybe the commands 'collect' and 'use' are useful here.", 100));
    }

    //makes it possible to simply write what command and what to do in it in GUI
    public boolean processCommand(CommandWord commandWord, String secondWord) {
        return processCommand(new Command(commandWord, secondWord));
    }

    public boolean processCommand(Command command) {
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
            useItem(command, currentRoom);
        }else if (commandWord == CommandWord.TALK){
            startQuest();
        }
        return wantToQuit;
    }

    //Help command
    private void printHelp() {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // If tutorial is not complete. When type 'use shoes' the tutorial is complete, and the player is able to go outside
        if(!tutorial){
            System.out.println("You need to put on your shoes before you can go outside.");
        } else {
            // Checks for a room exit
            Room nextRoom = currentRoom.getExit(direction);
            if (nextRoom == null) {
                System.out.println("There is no door!");
            } else {
                // The first day (day 0) the player can't go to work before the player completes 2 quests from NPCs
                // Counts completed quests
                int count = 0;
                for(Quest quest : questList.getCurrentQuests()){
                    if(quest.isRewarded()){
                        count += 1;
                    }
                }
                // Checks for day 0, quests completed and next room is work
                if(Timer.getDay() == 0 && count < 2 && nextRoom.equals(Timer.getWorkHome().get(0))){
                    System.out.println("They are not open yet. You need to kill some time. Time flies when you are in good company. *HINT*");
                    // If one or more is false, use the goRoom command normally
                } else {
                    currentRoom = nextRoom;

                    // If you go to work. Set currentRoom to home and start new day
                    if(currentRoom == Timer.getWorkHome().get(0)){
                        currentRoom = Timer.getWorkHome().get(1);
                        newDay();
                    }
                    // Add moves made everytime the room is changed. Not on day 0
                    Timer.setMovesMade();
                    System.out.println(currentRoom.getLongDescription());
                }
            }
        }
    }

    //Drops an item from the player inventory to the room inventory
    private void dropItem(Command command) {
        // Checks fo a type 1 quest in the quest list
        Quest thisQuest = null;
        for(Quest quest : questList.getCurrentQuests()){
            if(quest.getQuestType() == 1){
                thisQuest = quest;
            }
        }
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else {
            // Goes through inventory and drops the item equals to secondCommandWord
            for (int i = 0; i < inventoryItems.size(); i++) {
                if (inventoryItems.get(i).getName().equals(command.getSecondWord())) {
                    System.out.println("You dropped: " + command.getSecondWord());
                    // If type 1 quest is not active, drop the item on the ground and add it to room inventory
                    if(thisQuest == null){
                        currentRoom.setRoomItem(inventoryItems.get(i));
                    } else {
                        // If type 1 quest is active
                        // If the current room is the park (park is in index 4 in RoomList)
                        if(currentRoom == Room.getRoomList().get(4)){
                            // Adds 1 to the quest counter
                            thisQuest.setRecycleAmount(1);
                            // Updates the description
                            thisQuest.setDescription("Collect "+thisQuest.getRecycleAmount()+"/"+thisQuest.getCollectAmount()+" pieces of clothing, and drop it in the park.");
                            // If quest requirements are met
                            if(thisQuest.getRecycleAmount() == thisQuest.getCollectAmount()){
                                // Change description to meet with the quest giver
                                thisQuest.setDescription("You have delivered all the clothes you were asked to. Talk to "+thisQuest.getQuestGiver().getName()+" to complete the quest.");
                                System.out.println(thisQuest.getDescription());
                                // Change boolean complete to true. Note quest will still be in the quest menu
                                thisQuest.setComplete();
                            }
                        }
                    }
                    // Removes item from inventory at add a move
                    inventory.removeItem(i);
                    Timer.setMovesMade();
                    break;
                }
            }
        }
    }

    // For GUI
    public String printInventory(Inventory inventory) {
        this.inventory = inventory;
        return inventory.printInventory();
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public Inventory getInventory(){
        return inventory;
    }


    //Collect an item from the room inventory and puts it into the player inventory
    public void collectItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Collect what?");
        } else {
            // Goes through room items
            for (int i = 0; i < currentRoom.getRoomItems(); i++) {
                // if secondCommandWord equals a room item
                if (currentRoom.getRoomItem(i).getName().equals(command.getSecondWord())) {
                    // Add item to inventory
                    inventory.addItem(currentRoom.getRoomItem(i));
                    System.out.println("You collected: " + currentRoom.getRoomItem(i).getName());
                    // If item is not shoes, add a count point for achievement 2
                    if(!currentRoom.getRoomItem(i).getName().equals("shoes")){
                        Achievements.getAchievementList().get(2).setCount();
                    }
                    // Removes the item for room inventory and set move timer
                    currentRoom.removeRoomItem(i);
                    Timer.setMovesMade();
                    break;
                }
            }
        }
    }

    // Use the item. Only work if item is shoes or current room is the recycling center
    public void useItem(Command command, Room thisRoom) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
        } else {
            for (int i = 0; i < inventoryItems.size(); i++) {
                // If the written item is in inventory
                if (inventoryItems.get(i).getName().equals(command.getSecondWord()) && inventoryItems.size() != 0) {
                    // Checks for tutorial completion
                    if (!tutorial) {
                        // If the player types "use shoes" and has shoes in the inventory the tutorial is complete
                        if (inventoryItems.get(i).getName().equals("shoes")) {
                            System.out.println("You have put on your shoes, and are ready to go to work!");
                            tutorial = true;
                            Point.addPoint(50);
                            inventory.removeItem(i);
                            questList.getCurrentQuests().get(0).setRewarded();
                            archiveQuest();
                        } else {
                            // Only when tutorial is ongoing
                            System.out.println("This is not your shoes!");
                        }
                    } else {
                        // Checks if the player is at the recycling center and the item has questType 0
                        if(thisRoom.equals(Room.getContainerList().get(0)) && inventoryItems.get(i).getQuestType() == 0){
                            System.out.println("What container will you put "+command.getSecondWord()+" in?");
                            System.out.println("metal");
                            System.out.println("glass");
                            System.out.println("plastic");
                            System.out.println("organic");
                            System.out.println("paper");
                            Scanner scanner = new Scanner(System.in);
                            String scanned = scanner.nextLine();
                            // Sends item name and number i (i is the index number for the item in the inventory) to useContainer method
                            switch (scanned) {
                                case "metal" -> {
                                    useContainer("metal", i);
                                }
                                case "glass" -> {
                                    useContainer("glass", i);
                                }
                                case "plastic" -> {
                                    useContainer("plastic", i);
                                }
                                case "organic" -> {
                                    useContainer("organic", i);
                                }
                                case "paper" -> {
                                    useContainer("paper", i);
                                }
                                default -> System.out.println("I can't find that container.");
                            }
                        } else {
                            System.out.println("You can't use that item here. Maybe it should be used somewhere else?");
                        }
                        break;
                    }
                }
            }
        }
    }

    // Throws item in container
    public void useContainer(String itemName, int i){
        // Feedback to the player
        System.out.println("You have put " + inventoryItems.get(i).getName() + " in the container. Good job!");
        for (int j = 0; j < questList.getCurrentQuests().size(); j++) {
            // Goes through quests and find the type 1 quest
            if (questList.getCurrentQuests().get(j).getQuestType() == 0) {
                Quest ThisQuest = questList.getCurrentQuests().get(j);
                // Increases recycleAmount by 1
                ThisQuest.setRecycleAmount(1);
                // Checks if the item is correctly recycled
                if (inventoryItems.get(i).getContainer().equals(itemName)) {
                    ThisQuest.setRecycleRight(1);
                } else {
                    ThisQuest.setRecycleWrong(1);
                }
                // If all the trash from quest has been recycled
                if (ThisQuest.getRecycleAmount() == ThisQuest.getCollectAmount()) {
                    ThisQuest.setDescription("You recycled all the trash you were asked to. Talk to " + ThisQuest.getQuestGiver().getName() + " to complete the quest.");
                    System.out.println(ThisQuest.getDescription());
                    ThisQuest.setComplete();
                } else {
                    // Zero for the quest type 0
                    ThisQuest.updateDescriptionZero();
                }
                break;
            }
        }
        inventory.removeItem(i);
        Timer.setMovesMade();
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    // Start quest and talk to NPC
    private void startQuest(){
        // Checks for NPC in room
        if(currentRoom.getNPC() != null){
            for(Quest quest : questList.getCurrentQuests()){
                // If the player has met the requirements for the quest, but have not yet finished the quest by talking to the quest giver
                if(!quest.isRewarded() && quest.isComplete()){
                    completeQuest(currentRoom);
                    // Stops the loop if the quest is complete
                    if(currentRoom.getNPC().equals(quest.getQuestGiver()) && quest.isComplete()){
                        return;
                    }
                }
            }
            // If the player has to many active quests (maxQuests attribute in Quest class)
            // Tells a random fact if maxQuests have been reached
            if(questList.getCurrentQuests().size() == Quest.getMaxQuests()){
                System.out.println("Hello "+player.getPlayerName()+".");
                // If it is the first time the player talks to the NPC. The NPC gives a short introduction
                if(!currentRoom.getNPC().getMet()){
                    System.out.println(currentRoom.getNPC().getDescription());
                    currentRoom.getNPC().setMet();
                } else {
                    System.out.println("Good to see you again!");
                }
                System.out.println("Did you know:");
                randomFact();
            } else {
                // Checks for the quest type in questList.
                Quest newQuest = new Quest(currentRoom.getNPC());
                int count = 0;
                for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){ // Checks if the type of quest already exists
                    if(questList.getCurrentQuests().get(i).getQuestType() == newQuest.getQuestType()){
                        count = count + 1;
                    }
                }
                // If the quest type already exists, start the method over.
                if(count > 0){
                    startQuest();
                } else {
                    // Greetings from npc
                    System.out.println("Hello "+player.getPlayerName()+".");
                    // Prints description first time you talk to this NPC
                    if(!currentRoom.getNPC().getMet()){
                        System.out.println(currentRoom.getNPC().getDescription());
                        currentRoom.getNPC().setMet();
                    } else {
                        System.out.println("Good to see you again!");
                    }
                    // If you already have an active quest from this NPC, you get a random fact instead
                    if(currentRoom.getNPC().getQuestActive() || !currentRoom.getNPC().isQuestGiver()){
                        System.out.println("Did you know:");
                        randomFact();
                    } else{
                        // Sets the NPC questActive to true
                        currentRoom.getNPC().setQuestActive();

                        // Starts a type 0 quest (Collect and recycle)
                        if(newQuest.getQuestType() == 0){
                            createTypeZeroQuest(newQuest);
                        }
                        // Starts a type 1 quest (Clothes for the homeless)
                        if(newQuest.getQuestType() == 1){
                            createTypeOneQuest(newQuest);
                        }
                        // Prints the quest description
                        System.out.println("I need you to do something for me.");
                        Quest questAdded = questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1);
                        System.out.println(questAdded.getDescription());
                        if(questAdded.getQuestType() == 1){
                            questAdded.setDescription("Collect "+questAdded.getRecycleAmount()+"/"+questAdded.getCollectAmount()+" pieces of clothing, and drop it in the park.");
                        }
                    }
                }
            }
        }else {
            // If no NPC is in the room
            System.out.println("You are talking to yourself.");
        }
    }

    // Completes the quest
    private void completeQuest(Room thisRoom) {
        // Checks all quests in questList
        for (Quest quest : questList.getCurrentQuests()) {
            // If the quest giver is in the room
            if (quest.getQuestGiver().equals(thisRoom.getNPC())) {
                // If quest requirements are met (complete) and the player not yet has been rewarded
                if (!quest.isRewarded() && quest.isComplete()) {
                    // If type zero quest
                    if (quest.getQuestType() == 0) {
                        quest.setComplete();
                        // Points for right and wrong recycling and points gained
                        quest.setPoints((quest.getRecycleRight() * 9) + (quest.getRecycleWrong() * 2));
                        System.out.println("Thank you for your help " + player.getPlayerName() + "!");
                        System.out.println("You recycled " + quest.getRecycleRight() + " things correct.");
                        System.out.println("You recycled " + quest.getRecycleWrong() + " things wrong.");
                        System.out.println("You get " + quest.getPoints() + " points for completing the quest.");
                        Point.addPoint(quest.getPoints());
                        quest.setDescription("You recycled all the trash you were asked to. The quest will be removed next day.");
                        // Change rewarded to true so the quest can be archived
                        quest.setRewarded();
                        return;
                    }
                    // If type one quest
                    if (quest.getQuestType() == 1) {
                        // Gives 7 points for every item in the quest
                        quest.setPoints(quest.getCollectAmount() * 7);
                        System.out.println("Thank you for your help " + player.getPlayerName() + "!");
                        System.out.println("You get " + quest.getPoints() + " points for completing the quest.");
                        Point.addPoint(quest.getPoints());
                        quest.setDescription("You delivered all the clothes you were asked to. The quest will be removed next day.");
                        // Change rewarded to true so the quest can be archived
                        quest.setRewarded();
                        return;
                    }
                }
            }
        }
    }

    // when quest is completed and rewarded. Add finished quests to a new list for end screen, and change description.
    private void archiveQuest(){
        for(int i = questList.getCurrentQuests().size() - 1 ; i >= 0 ; i--){
            if(questList.getCurrentQuests().get(i).isRewarded()){ // Checks boolean complete
                Quest questDone = questList.getCurrentQuests().get(i);
                if(questDone.getQuestType() == 100){ // Only for the tutorial
                    questDone.setDescription("Tutorial.");
                }
                if(questDone.getQuestType() == 0){ // for type 0 quests
                    questDone.setDescription("Collect and recycle - Completed at day "+Timer.getDay());
                } else if(questDone.getQuestType() == 1){ // for type 0 quests
                    questDone.setDescription("Clothes for the homeless - Completed at day "+Timer.getDay());
                }
                // Makes the quest giver active again so new quests can be acquired from the NPC
                for(int j = 0 ; j < Room.getAllRoomList().size() - 1 ; j++){
                    if(questDone.getQuestGiver() != null){
                        if(Room.getAllRoomList().get(j).getNPC() == questDone.getQuestGiver()){
                            Room.getAllRoomList().get(j).getNPC().setQuestActive();
                        }
                    }
                }
                // Removes quest from questList and adds it to finishedQuestList for end screen
                finishedQuestList.getCurrentQuests().add(questDone);
                questList.getCurrentQuests().remove(i);
                // Adds count point for achievement 3
                Achievements.getAchievementList().get(3).setCount();
            }
        }
    }

    // New day starts when the player goes to work
    private void newDay(){
        // Checks how many quests that was rewarded
        int questsBeforeNewDay = finishedQuestList.getCurrentQuests().size();
        archiveQuest();
        int questsAfterNewDay = finishedQuestList.getCurrentQuests().size();

        // For the first day
        if(Timer.getDay() == 0){
            System.out.println("Congratulation with your new job!");
            System.out.println("You are ready for your first day at work.");
            System.out.println("From now on you should keep track on time, or else you will be late for work.");
            System.out.println("It's okay to be a bit late, but if you are late too often, you will get fired.");
            System.out.println("Today you have time enough to explore the areas and talk to people.");
            System.out.println("But remember to keep track of the timer.");
        } else {
            // Changes the day
            Timer.setDay();
            int pointsGainedThisDay = 0;
            int movesLate = 0;
            // If the player is "late" for work
            if(Timer.getWorkTimer() < Timer.getMovesMade()){
                movesLate = Timer.getMovesMade() - Timer.getWorkTimer();
                Timer.setWorkEffort(Timer.getWorkEffort() + movesLate);

                // Information to player
                System.out.println("You are late for work. If you keep getting late, you will get fired.");
                System.out.println("Today you were "+movesLate+" moves late. And you are a total of "+Timer.getWorkEffort()+" moves late.");
                System.out.println("If your total exceeds "+Timer.getWorkEffortThreshold()+" you will get fired.");
            } else {
                // Gains 1 point for every move the player is "early" for work, but not on the first day
                if(Timer.getDay() > 2){
                    pointsGainedThisDay = Timer.getWorkTimer() - Timer.getMovesMade();
                    Point.addPoint(pointsGainedThisDay);
                    System.out.println("You came early to work and got "+pointsGainedThisDay+" points.");
                }
                System.out.println("A new day has started, and you are ready for work.");
            }
            // If the workEffort exceeds workEffortThreshold the player is fired, and the game ends
            if(Timer.getWorkEffortThreshold() < Timer.getWorkEffort()){
                Timer.setFired();
            }
            // Subtract 1 workTimer (1 less moves every day until fired)
            Timer.setWorkTimer(Timer.getWorkTimer() - 1);
            Timer.setMovesMade(-1);
            System.out.println("You completed "+(questsAfterNewDay - questsBeforeNewDay)+" quests yesterday.");
            // Adds count point for achievement 1
            Achievements.getAchievementList().get(1).setCount();
        }

    }

    // Picks a random fact from the fact array
    private void randomFact(){
        System.out.println(Fact.getFact()[rand.nextInt(Fact.getFact().length)]);
        // Adds count point for achievement 0
        Achievements.getAchievementList().get(0).setCount();
    }

    // Goes through all achievements and prints text if complete
    private void achievementCheck(){
        for(Achievements thisAchievement : Achievements.getAchievementList()){
            if(thisAchievement.getCount() >= thisAchievement.getNumberToComplete() && !thisAchievement.isComplete()){
                thisAchievement.setComplete();
                System.out.println("ACHIEVEMENT UNLOCKED - "+thisAchievement.getName());
                System.out.println(thisAchievement.getDescription());
            }
        }
    }

    // When fired the end screen is printed
    private void printEndScreen(){
        System.out.println(">You got fired...");
        System.out.println(">Final score: "+Point.getPoint());
        System.out.println(">You kept your job in "+Timer.getDay()+" day(s).");
        System.out.println("-Achievements unlocked:");
        // Prints all achievements with description. Default description if not complete
        for(Achievements achievement : Achievements.getAchievementList()){
            if(achievement.isComplete()){
                System.out.println("-Name: "+achievement.getName()+" - Complete.");
                System.out.println("-"+achievement.getDescription());
            } else {
                System.out.println("-Name: "+achievement.getName());
                System.out.println("-"+Achievements.getDefaultDescription());
            }
        }
        // Prints completed quests from finishedQuestList
        System.out.println("#You completed "+finishedQuestList.getCurrentQuests().size()+" quests:");
        for(int i = 0 ; i < finishedQuestList.getCurrentQuests().size() ; i++){
            System.out.println("# "+finishedQuestList.getCurrentQuests().get(i));
        }
    }
    // Type 0 quest. Collect and recycle
    private void createTypeZeroQuest(Quest newQuest){
        //Create Quest and add it to questList
        questList.addQuest(newQuest);
        // Picks the latest added quest to questList
        Quest questSetting = questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1); // Gets the latest added quest and call it questSetting
        // Creates glass items
        for(int i = 0 ; i < questSetting.getGlassNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getGlassTypes().length);
                room.setRoomItem(new Item(Item.getGlassTypes()[itemNumber], Item.getGlassTypesBtn()[itemNumber], "glass", 0));
            } else {
                i--;
            }
        }
        // Creates metal items
        for(int i = 0 ; i < questSetting.getMetalNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getMetalTypes().length);
                room.setRoomItem(new Item(Item.getMetalTypes()[itemNumber], Item.getMetalTypesBtn()[itemNumber], "metal", 0));
            } else {
                i--;
            }
        }
        // Creates plastic items
        for(int i = 0 ; i < questSetting.getPlasticNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getPlasticTypes().length);
                room.setRoomItem(new Item(Item.getPlasticTypes()[itemNumber], Item.getPlasticTypesBtn()[itemNumber], "plastic", 0));
            } else {
                i--;
            }
        }
        // Creates paper items
        for(int i = 0 ; i < questSetting.getPaperNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getPaperTypes().length);
                room.setRoomItem(new Item(Item.getPaperTypes()[itemNumber], Item.getPaperTypesBtn()[itemNumber], "paper", 0));
            } else {
                i--;
            }
        }
        // Creates organic items
        for(int i = 0 ; i < questSetting.getOrganicNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getOrganicTypes().length);
                room.setRoomItem(new Item(Item.getOrganicTypes()[itemNumber], Item.getOrganicTypesBtn()[itemNumber], "organic", 0));
            } else {
                i--;
            }
        }
    }
    // Type one quest. Clothes for the homeless
    private void createTypeOneQuest(Quest newQuest){
        //Create Quests - Will be added when talking to NPC
        questList.addQuest(newQuest);
        // Gets the latest added quest and call it questSetting
        Quest questSetting = questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1);
        for(int i = 0 ; i < questSetting.getCollectAmount() ; i++ ){
            // Select a random room
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size()));
            // Items do not spawn in currentRoom or the park
            if(room != currentRoom && room != Room.getRoomList().get(4)){
                int itemNumber = rand.nextInt(Item.getClothingTypes().length);
                room.setRoomItem(new Item(Item.getClothingTypes()[itemNumber], Item.getClothingTypesBtn()[itemNumber], 1));
            } else {
                i--;
            }
        }
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
}