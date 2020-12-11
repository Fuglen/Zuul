package domain;

public class NPC {
    private String name; // name of NPC
    private String description; // Introduction from first the player talks to the NPC
    private boolean questActive; // Tells if the player already has an active quest from the NPC
    private boolean met; // Checks if introduction is needed
    private boolean questGiver; // Is the NPC able to give quests

    public NPC(String name, String description, boolean questGiver){
        this.name = name;
        this.description = description;
        this.questActive = false;
        this.met = false;
        this.questGiver = questGiver;
    }

    public String getName() {
        return name;
    }

    public void setMet(){
        this.met = !this.met;
    }

    public boolean getMet(){
        return met;
    }

    public boolean getQuestActive(){
        return questActive;
    }

    public void setQuestActive(){
        this.questActive = !this.questActive;
    }

    public String getDescription() {
        return description;
    }

    public boolean isQuestGiver() {
        return questGiver;
    }
}
