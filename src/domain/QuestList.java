package domain;

import java.util.ArrayList;

public class QuestList {

    private ArrayList<Quest> currentQuests;

    public QuestList() {
        this.currentQuests = new ArrayList<Quest>();
    }

    // Prints ArrayList containing active quests
    public void printQuests() {
        if (currentQuests.size() == 0) {
            System.out.println("You currently have none active quests.");
        } else {
            System.out.println("Your active quests are:");
            int i = 1;
            for (Quest quest : currentQuests) {
                System.out.println(i+") "+quest+" "+quest.isRewarded()+" "+quest.isComplete());
                i++;
            }
        }
    }

    // Adds quest to ArrayList
    public void addQuest(Quest quest) {
        if (!currentQuests.contains(quest) && currentQuests.size() < Quest.getMaxQuests()) {
            currentQuests.add(quest);
            /*if(currentQuests.get(0).getQuestType() != 100){
                System.out.println("You have started a new quest.");
            }*/

        } else {
            System.out.println("You have too many active quests.");
        }
    }

    public ArrayList<Quest> getCurrentQuests() {
        return currentQuests;
    }
}
