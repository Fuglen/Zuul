package domain;

import java.util.ArrayList;

public class QuestList {

    private ArrayList<Quest> currentQuests;

    public QuestList() {
        this.currentQuests = new ArrayList<Quest>();
    }

    public void printQuests() {
        if (currentQuests.size() == 0) {
            System.out.println("You currently have none active quests.");
        } else {
            System.out.println("Your active quests are:");
            for (Quest quest : currentQuests) {
                System.out.println(quest);
            }
        }
    }

    public void addQuest(Quest quest) {
        if (!currentQuests.contains(quest) && currentQuests.size() < 1) {
            currentQuests.add(quest);
        } else {
            System.out.println("You have too many active quests.");
        }
    }

}
