package worldofzuul;

import java.util.Scanner;

public class Player {
    private String playerName;

    public Player() {


    }

    public void createPlayer() {
        System.out.println("Pick a name for your character.");
        Scanner scanner = new Scanner(System.in);
        playerName = scanner.nextLine();
        System.out.println("Hello there, " + playerName);

    }

    public String getPlayerName() {
        return playerName;

    }
}
