package domain;

public class NPC {
    private String name;
    private String description;
    private boolean questActive;
    private boolean met;
    private boolean questGiver;

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
