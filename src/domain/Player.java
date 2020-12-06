package domain;

import java.util.Scanner;

class Player {
    private String playerName;
    private int playerAge;
    private String playerGender;

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
                    System.out.println("I'm getting sick of you, get some help");
                    createPlayer();
                    break;
                default:
                    playerName = input;
                    System.out.println("Hello there " + playerName + ", we hope you enjoy our game!");
            }
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerGender() {
        return playerGender;
    }

    public void setPlayerGender(String playerGender) {
        this.playerGender = playerGender;
    }

    public String getPlayerName() {
        return playerName;

    }
}
