package domain;

public enum CommandWord {

    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), DROP("drop"), COLLECT("collect"), QUESTS("quests"), USE("use"),TALK("talk"),FACT("fact"), CHEAT("cheat");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
