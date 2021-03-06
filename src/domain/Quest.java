package domain;

import java.util.Random;

public class Quest {

    private String description; // Description of what to do
    private int questType; // Different types of quests
    private int points; // Points after completion
    private NPC questGiver; // NPC who gave the quest. And NPC who ends the quest
    private boolean complete = false; // True when complete
    private int metalNeed; // Amount of metal needed, and spawned in the world
    private int glassNeed; // Amount of glass needed, and spawned in the world
    private int plasticNeed; // Amount of plastic needed, and spawned in the world
    private int paperNeed; // Amount of plastic needed, and spawned in the world
    private int organicNeed; // Amount of plastic needed, and spawned in the world
    private int collectAmount; // Amount of trash total needed, and spawned in the world
    private int recycleAmount; // Amount of trash that has been recycled. When recycleAmount == collectAmount change description to "talk to 'questGiver'"
    private int recycleRight; // +1 if you recycle right
    private int recycleWrong; // +1 if you recycle wrong
    private static final int maxQuests = 2; // Number of quests that can be active at the same time
    private boolean rewarded = false; // True when talking to NPC after completion


    public Quest(NPC questGiver){
        Random rand = new Random();
        int random = rand.nextInt(2); // Chooses a random number 0-1 and decides the quest type
        this.questGiver = questGiver;

        if(random == 0){ // Type zero quest (collect and recycle)
            this.questType = random;
            this.collectAmount = 5 + rand.nextInt(4); // The amount of items spawned. From 5 to 8.
            this.metalNeed = 1 + rand.nextInt(2);
            this.glassNeed = 1 + rand.nextInt(2);
            this.paperNeed = 1 + rand.nextInt(2);
            this.organicNeed = 1 + rand.nextInt(2);
            this.plasticNeed = collectAmount - glassNeed - metalNeed - paperNeed - organicNeed;
            this.description = "Collect and recycle "+collectAmount+" pieces of trash.\nYou have recycled "+recycleAmount+"/"+collectAmount;
        }

        if(random == 1){
            this.questType = random;
            this.collectAmount = 3 + rand.nextInt(3);
            this.description = "Collect "+collectAmount+" pieces of clothing, and drop it in the park.\nI will get someone to pick it up later.";
        }

    }

    // Used for the shoes item for tutorial
    public Quest(String text, int questType){
        this.description = text;
        this.questType = questType;
        this.complete = false;
        this.rewarded = false;
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

    public NPC getQuestGiver() {
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

    public static int getMaxQuests() {
        return maxQuests;
    }

    public void setComplete() {
        this.complete = true;
    }

    public void setRecycleAmount(int recycleAmount) {
        this.recycleAmount = this.recycleAmount + recycleAmount;
    }

    public void setRecycleRight(int recycleRight) {
        this.recycleRight = this.recycleRight + recycleRight;
    }

    public void setRecycleWrong(int recycleWrong) {
        this.recycleWrong = this.recycleWrong + recycleWrong;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateDescriptionZero() {
        this.description = "Collect and recycle "+collectAmount+" pieces of trash.\nYou have recycled "+recycleAmount+"/"+collectAmount;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPaperNeed() {
        return paperNeed;
    }

    public int getOrganicNeed() {
        return organicNeed;
    }

    public boolean isRewarded() {
        return rewarded;
    }

    public void setRewarded() {
        this.rewarded = true;
    }

    @Override
    public String toString() {
        return description;
    }
}
