package domain;

import java.util.Random;

public class Quest {

    private String description; // Description of what to do
    private int questType; // Different types of quests
    private int points; // Points after completion
    private String questGiver; // NPC who gave the quest. And NPC who ends the quest
    private boolean complete = false; // True when complete
    private int metalNeed; // Amount of metal needed, and spawned in the world
    private int glassNeed; // Amount of glass needed, and spawned in the world
    private int plasticNeed; // Amount of plastic needed, and spawned in the world
    private int collectAmount; // Amount of trash total needed, and spawned in the world
    private int recycleAmount; // Amount of trash that has been recycled. When recycleAmount == collectAmount change description to "talk to 'questGiver'"
    private int recycleRight; // +1 if you recycle right
    private int recycleWrong; // +1 if you recycle wrong

    public Quest(){
        Random rand = new Random();
        int random = rand.nextInt(2);

        if(random == 0){ // Type zero quest (collect and recycle)
                        this.questType = random;
            this.points = 100;
            this.collectAmount = 6 + rand.nextInt(4); // From 6 to 9.
            this.metalNeed = 2 + rand.nextInt(2);
            this.glassNeed = 2 + rand.nextInt(2);
            this.plasticNeed = collectAmount - glassNeed - metalNeed;
            this.description = "#Collect and recycle "+collectAmount+" pieces of trash.\n#You have recycled "+recycleAmount+"/"+collectAmount;
        }

        this.questGiver = ""; // Use the set method

    }

    public String getDescription() {
        return description;
    }

    public int getQuestType() {
        return questType;
    }

    public int getPoints() {
        return points;
    }

    public String getQuestGiver() {
        return questGiver;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getMetalNeed() {
        return metalNeed;
    }

    public int getGlassNeed() {
        return glassNeed;
    }

    public int getPlasticNeed() {
        return plasticNeed;
    }

    public int getCollectAmount() {
        return collectAmount;
    }

    public int getRecycleAmount() {
        return recycleAmount;
    }

    public int getRecycleRight() {
        return recycleRight;
    }

    public int getRecycleWrong() {
        return recycleWrong;
    }

    public void setComplete() {
        this.complete = true;
    }

    public void setRecycleAmount(int recycleAmount) {
        this.recycleAmount = recycleAmount;
    }

    public void setRecycleRight(int recycleRight) {
        this.recycleRight = recycleRight;
    }

    public void setRecycleWrong(int recycleWrong) {
        this.recycleWrong = recycleWrong;
    }

    public void setQuestGiver(String questGiver) {
        this.questGiver = questGiver;
    }

    public void removeCurrentQuests() {

    }

    @Override
    public String toString() {
        return description;
    }
}
