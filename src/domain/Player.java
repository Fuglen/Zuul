package domain;

import java.util.Scanner;

class Player {
    private String playerName;
    public Player() {
    }

    public void createPlayer() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Check if input contains a space, if not then uppercase the name. Meme is intended :)
        if (input.contains(" ")) {
            System.out.println("Only your first name, please. (No space)");
            createPlayer();
        } else {
            if(input.isEmpty()){
                System.out.println("Are you nameless? Try again.");
                createPlayer();
            } else {
                input = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
                switch (input) {
                    case "Wisely":
                        System.out.println("Ha-ha. Very funny.");
                        System.out.println("Choose wisely, as it can't be changed.");
                        createPlayer();
                        break;
                    case "Changed":
                        System.out.println("It can't be changed either, please type another.");
                        createPlayer();
                        break;
                    case "Another":
                        System.out.println("I'm getting sick of you, get some help.");
                        createPlayer();
                        break;
                    case "Help":
                        System.out.println("Are your name Help or are you asking for help?");
                        System.out.println("It's too confusing. Choose another name!");
                        createPlayer();
                        break;
                    case "No":
                        System.out.println("Yes...");
                        createPlayer();
                        break;
                    case "Ass":
                        System.out.println("Hello there Ass, we hope... NOPE can't do it. Choose another name!");
                        System.out.println("Ass... pfft");
                        createPlayer();
                        break;
                    default:
                        playerName = input;
                        System.out.println("Hello there " + playerName + ", we hope you enjoy our game!");
                        System.out.println("You are late for work! Put on your shoes and go to work!");
                }
            }
        }
    }

    public String getPlayerName() {
        return playerName;

    }
}
