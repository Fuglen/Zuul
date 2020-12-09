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

        // List of rooms items can spawn in
        Room.addRoomToList(beach);
        Room.addRoomToList(forest);
        Room.addRoomToList(city);
        Room.addRoomToList(mcdonalds);
        Room.addRoomToList(park);
        Room.addRoomToList(road);

        // List of rooms with containers
        Room.addRoomToContainerList(glass);
        Room.addRoomToContainerList(metal);
        Room.addRoomToContainerList(plastic);

        // List used for starting a new day
        Timer.getWorkHome().add(work);
        Timer.getWorkHome().add(home);

        // Set the starting room to home
        currentRoom = home;

        //Room inventory for the start of the game
        home.setRoomItem(new Item("shoes"));

        //Create NPC in room
        road.addNPC(new NPC("Tommy", "I'm a weird motherfucker!", true));
        city.addNPC(new NPC("Signe", "I'm a girl. fyldetekst fyldetekst fyldetekst fyldetekst fyldetekst!", true));
        beach.addNPC(new NPC("Dummy 1", "Jeg er ligegyldig", false));
        park.addNPC(new NPC("Dummy 2", "Jeg er ligegyldig", false));
        forest.addNPC(new NPC("Dummy 3", "Jeg er ligegyldig", false));

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
            achievementCheck();
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        // Shows end screen when get fired
        while(Timer.isFired() && !finished){
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
        }else if (commandWord == CommandWord.TALK){
            startQuest();
        }else if (commandWord == CommandWord.CHEAT){ // Will be removed
            boolean cheater = true;
            while (cheater){
                System.out.println("Cheat menu!");
                System.out.println("1) Start a new quest");
                System.out.println("2) List of quests completed");
                System.out.println("3) Get fired");
                System.out.println("4) Complete quest. (Talking to NPC)");
                System.out.println("5) Archive quests");
                System.out.println("6) Back to the game");
                Scanner scanCheat = new Scanner(System.in);
                String cheat = scanCheat.nextLine();
                switch (cheat) {
                    case "1" -> {
                        for(Room rooms : Room.getAllRoomList()){
                            System.out.println(rooms);
                        }
                    }
                    case "2" -> {
                        for (int i = 0; i < finishedQuestList.getCurrentQuests().size(); i++) {
                            System.out.println(finishedQuestList.getCurrentQuests().get(i));
                        }
                    }
                    case "3" -> {
                        Timer.setFired();
                    }
                    case "4" -> {
                        completeQuest();
                    }
                    case "5" -> {
                        archiveQuest();
                    }
                    case "6" -> {
                        cheater = false;
                    }
                    default -> System.out.println("No cheat");
                }
            }
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
                // If you go to work. Set currentRoom to home and start new day
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
                    if(!currentRoom.getRoomItem(i).getName().equals("shoes")){
                        Achievements.getAchievementList().get(2).setCount();
                    }
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
                // If the written item is in inventory
                if (inventoryItems.get(i).getName().equals(command.getSecondWord())) {
                    // Checks for tutorial completion
                    if(!tutorial){
                        // If "use shoes" tutorial is complete
                        if(inventoryItems.get(i).getName().equals("shoes")){
                            System.out.println("You have put on your shoes, and are ready to go to work!");
                            tutorial = true;
                            Point.addPoint(50);
                            inventory.removeItem(i);
                            questList.getCurrentQuests().get(0).setComplete();
                            archiveQuest();
                        } else {
                            // Only when tutorial is ongoing
                            System.out.println("This is not your shoes!");
                        }
                    } else {
                        // If tutorial is complete
                        // Checks if 'use' command is used in one of the container rooms
                        if(currentRoom == Room.getContainerList().get(0) || currentRoom == Room.getContainerList().get(1) || currentRoom == Room.getContainerList().get(2)){
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
                                    // If all the trash from quest has been recycled
                                    if(ThisQuest.getRecycleAmount() == ThisQuest.getCollectAmount()){
                                        ThisQuest.setDescription("You recycled all the trash you were asked to. Talk to "+ThisQuest.getQuestGiver().getName()+" to complete the quest.");
                                        System.out.println(ThisQuest.getDescription());
                                    } else {
                                        ThisQuest.updateDescriptionZero(); // Zero for the quest type 0
                                    }
                                    j = questList.getCurrentQuests().size() - 1;
                                }
                            }
                            inventory.removeItem(i);
                            Timer.setMovesMade();
                            // If not in a container room
                        } else {
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

    // Starts new quest
    private void startQuest(){
        if(currentRoom.getNPC() != null){
            // Greetings from npc
            System.out.println("Hello "+player.getPlayerName()+".");
            // Prints description first time you talk to this NPC
            if(!currentRoom.getNPC().getMet()){
                System.out.println(currentRoom.getNPC().getDescription());
                currentRoom.getNPC().setMet();
            } else {
                System.out.println("Good to see you again!");
            }
            if(questList.getCurrentQuests().size() == Quest.getMaxQuests()){
                System.out.println("I'm sorry, but I'm busy right now. Talk to you later.");
            } else {
                // If you already have an active quest from this NPC, you get a random fact instead
                if(currentRoom.getNPC().getQuestActive() || !currentRoom.getNPC().isQuestGiver()){
                    System.out.println("Did you know:");
                    randomFact();
                } else{
                    currentRoom.getNPC().setQuestActive();
                    Quest newQuest = new Quest(currentRoom.getNPC());
                    int count = 0;
                    for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){ // Checks if the type of quest already exists
                        if(questList.getCurrentQuests().get(i).getQuestType() == newQuest.getQuestType()){
                            count = count + 1;
                        }
                    }
                    if(count > 0){ // If the quest type already exists, start the method over.
                        if(questList.getCurrentQuests().size() == Quest.getMaxQuests()){
                            System.out.println("Something went wrong.");
                        } else {
                            startQuest();
                        }
                    } else {
                        if(newQuest.getQuestType() == 0){
                            createTypeZeroQuest(newQuest);
                        }
                        if(newQuest.getQuestType() == 1){
                            questList.addQuest(newQuest);
                        }
                        if(newQuest.getQuestType() == 2){
                            questList.addQuest(newQuest);
                        }
                        System.out.println("I need you to do something for me.");
                        System.out.println(questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1).getDescription());
                    }
                }
            }
        } else {
            System.out.println("You are talking to yourself.");
        }
    }

    // Completes the quest
    private void completeQuest(){
        int completeCount = 0;
        for(int i = 0 ; i < questList.getCurrentQuests().size() ; i++){
            if(questList.getCurrentQuests().get(i).getQuestGiver() == currentRoom.getNPC()){
                completeCount += 1;
                if(questList.getCurrentQuests().get(i).getQuestType() == 0){ // type 0 quests
                    if(questList.getCurrentQuests().get(i).getCollectAmount() == questList.getCurrentQuests().get(i).getRecycleAmount()){
                        Quest completeQuest = questList.getCurrentQuests().get(i);
                        completeQuest.setComplete();
                        completeQuest.setPoints((completeQuest.getRecycleRight() * 10) + (completeQuest.getRecycleWrong() * 2));
                        System.out.println("Thank you for your help "+player.getPlayerName()+"!");
                        System.out.println("You recycled "+completeQuest.getRecycleRight()+" things correct.");
                        System.out.println("You recycled "+completeQuest.getRecycleWrong()+" things wrong.");
                        System.out.println("You get "+ completeQuest.getPoints() +" points for completing the quest.");
                        Point.addPoint(completeQuest.getPoints());
                    }
                }
            }
        }
        if(completeCount != 1){
            System.out.println("You can't complete your quest here.");
        }
    }

    // when quest is completed. Add finished quests to a new list for end screen, and change description.
    private void archiveQuest(){
        for(int i = questList.getCurrentQuests().size() - 1 ; i >= 0 ; i--){
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
                for(int j = 0 ; j < Room.getAllRoomList().size() - 1 ; j++){
                    if(questDone.getQuestGiver() != null){
                        if(Room.getAllRoomList().get(j).getNPC() == questDone.getQuestGiver()){
                            Room.getAllRoomList().get(j).getNPC().setQuestActive();
                            System.out.println("Hej");
                        }
                    }
                }
                finishedQuestList.getCurrentQuests().add(questDone);
                questList.getCurrentQuests().remove(i);
                Achievements.getAchievementList().get(3).setCount();
            }
        }
    }

    // New day
    private void newDay(){
        int questsBeforeNewDay = finishedQuestList.getCurrentQuests().size();
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
            if(Timer.getDay() > 1){
                pointsGainedThisDay = Timer.getWorkTimer() - Timer.getMovesMade();
                Point.addPoint(pointsGainedThisDay);
                System.out.println("You came early to work and got "+pointsGainedThisDay+" points.");
            }
            System.out.println("A new day has started, and you are ready for work.");
        }
        if(Timer.getWorkEffortThreshold() < Timer.getWorkEffort()){
            Timer.setFired();
        }
        Timer.setWorkTimer(Timer.getWorkTimer() - 1);
        Timer.setMovesMade(-1);
        System.out.println("You completed "+(questsAfterNewDay - questsBeforeNewDay)+" quests yesterday.");
        Achievements.getAchievementList().get(1).setCount();
    }

    private void randomFact(){
        System.out.println(Fact.getFact()[rand.nextInt(Fact.getFact().length)]);
        Achievements.getAchievementList().get(0).setCount();
    }

    private void achievementCheck(){
        // Checks for book worm
        for(int i = 0 ; i < Achievements.getAchievementList().size() ; i++){
            Achievements thisAchievement = Achievements.getAchievementList().get(i);
            if(thisAchievement.getCount() >= thisAchievement.getNumberToComplete() && !thisAchievement.isComplete()){
                thisAchievement.setComplete();
                System.out.println("ACHIEVEMENT UNLOCKED - "+thisAchievement.getName());
                System.out.println(thisAchievement.getDescription());
                            }
        }
    }

    private void printEndScreen(){
        System.out.println(">You got fired...");
        System.out.println(">Final score: "+Point.getPoint());
        System.out.println(">You kept your job in "+Timer.getDay()+" day(s).");
        System.out.println("-Achievements unlocked:");
        for(int i = 0 ; i < Achievements.getAchievementList().size() ; i++){
            Achievements achievement = Achievements.getAchievementList().get(i);
            if(achievement.isComplete()){
                System.out.println("-Name: "+achievement.getName()+" - Complete.");
                System.out.println("-"+achievement.getDescription());
            } else {
                System.out.println("-Name: "+achievement.getName());
                System.out.println("-"+Achievements.getDefaultDescription());
            }
        }
        System.out.println("#You completed "+finishedQuestList.getCurrentQuests().size()+" quests:");
        for(int i = 0 ; i < finishedQuestList.getCurrentQuests().size() ; i++){
            System.out.println("# "+finishedQuestList.getCurrentQuests().get(i));
        }
    }
    // Type 0 quest. Collect and recycle
    private void createTypeZeroQuest(Quest newQuest){
        questList.addQuest(newQuest); //Create Quests - Will be added when talking to NPC
        Quest questSetting = questList.getCurrentQuests().get(questList.getCurrentQuests().size() - 1); // Gets the latest added quest and call it questSetting
        // Creates glass items
        for(int i = 0 ; i < questSetting.getGlassNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getGlassTypes().length);
                room.setRoomItem(new Item(Item.getGlassTypes()[itemNumber], Item.getGlassTypesBtn()[itemNumber], Room.getContainerList().get(0)));
            } else {
                i--;
            }
        }
        // Creates metal items
        for(int i = 0 ; i < questSetting.getMetalNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getMetalTypes().length);
                room.setRoomItem(new Item(Item.getMetalTypes()[itemNumber], Item.getMetalTypesBtn()[itemNumber], Room.getContainerList().get(1)));
            } else {
                i--;
            }
        }
        // Creates plastic items
        for(int i = 0 ; i < questSetting.getPlasticNeed() ; i++ ){
            Room room = Room.getRoomList().get(rand.nextInt(Room.getRoomList().size())); // Select a random room
            if(room != currentRoom){
                int itemNumber = rand.nextInt(Item.getPlasticTypes().length);
                room.setRoomItem(new Item(Item.getPlasticTypes()[itemNumber], Item.getPlasticTypesBtn()[itemNumber], Room.getContainerList().get(2)));
            } else {
                i--;
            }
        }
    }
}