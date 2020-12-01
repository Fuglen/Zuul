package worldofzuul;

<<<<<<< HEAD:worldofzuul/CommandWord.java
public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), DROP("drop"), COLLECT("collect"), QUESTS("quests");
=======
enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), DROP("drop"), COLLECT("collect");
>>>>>>> GUI:src/worldofzuul/CommandWord.java

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
