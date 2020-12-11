package domain;

import java.util.Scanner;

public class Player {
    private String playerName;
    public Player() {
    }

    // Creates the player
    public void createPlayer() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Check if input contains a space, if not then uppercase the name. Meme is intended :)
        if (input.contains(" ")) {
            System.out.println("Only your first name, please. (No space)");
            createPlayer();
        } else {
            // If nothing is written. Otherwise the game crashes
            if(input.isEmpty()){
                System.out.println("Are you nameless? Try again.");
                createPlayer();
            } else {
                // Changes first letter to uppercase and the rest to lowercase
                input = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
                switch (input) {
                    // Easter eggs
                    case "Wisely" -> {
                        System.out.println("Ha-ha. Very funny.");
                        System.out.println("Choose wisely, as it can't be changed.");
                        createPlayer();
                    }
                    case "Changed" -> {
                        System.out.println("It can't be changed either, please type another.");
                        createPlayer();
                    }
                    case "Another" -> {
                        System.out.println("I'm getting sick of you, get some help.");
                        createPlayer();
                    }
                    case "Help" -> {
                        System.out.println("Are your name Help or are you asking for help?");
                        System.out.println("It's too confusing. Choose another name!");
                        createPlayer();
                    }
                    case "No" -> {
                        System.out.println("Yes...");
                        createPlayer();
                    }
                    case "Ass" -> {
                        System.out.println("Hello there Ass, we hope... NOPE can't do it. Choose another name!");
                        System.out.println("Ass... pfft");
                        createPlayer();
                    }
                    default -> {
                        // Prints a welcome text to the player
                        playerName = input;
                        System.out.println("Hello there " + playerName + ".");
                        System.out.println("Sadly, you got fired yesterday, and you need to find a new job.");
                        System.out.println("You heard about a place that is hiring. Go check it out!");
                        System.out.println("Before you can go outside, you need to put your shoes on.");
                        System.out.println("Type 'help' to see all available commands. Type 'quests' to see your active quests.");
                    }
                }
            }
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
