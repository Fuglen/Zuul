package domain;

import java.util.ArrayList;

public class Achievements {

    // List containing achievements
    private static ArrayList<Achievements> achievementList = new ArrayList<Achievements>();

    // Attributes
    private final String name;
    private final String description;
    private final int numberToComplete;
    private int count;
    private boolean complete;

    // Achievement 0 settings (Book worm) - Read *numToComplete* facts
    private static final int numToComplete0 = 40;
    private static final String zeroDescription = "Read "+Achievements.getNumToComplete0()+" facts. Good job!";

    // Achievement 1 settings (Workaholic) - Go to work for *numToComplete* days
    private static final int numToComplete1 = 15;
    private static final String firstDescription = "Keep your work for "+Achievements.getNumToComplete1()+" days.";

    // Achievement 2 settings (Mother Nature’s champion) - Collect *numToComplete* pieces og trash
    private static final int numToComplete2 = 45;
    private static final String secondDescription = "Collect "+Achievements.getNumToComplete2()+" pieces of trash.";

    // Achievement 3 settings (Friend of the people) - Complete *numToComplete* quests
    private static final int numToComplete3 = 15;
    private static final String ThirdDescription = "At the end of the day. Have completed "+Achievements.getNumToComplete3()+" quests.";

    // Default description if achievement is not complete
    private static final String defaultDescription = "You did not complete this achievement.";

    // Constructor
    public Achievements(String name, String description, int numberToComplete){
        this.name = name;
        this.description = description;
        this.numberToComplete = numberToComplete;
        this.count = 0;
        this.complete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberToComplete() {
        return numberToComplete;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count = this.count + 1;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete() {
        this.complete = !this.complete;
    }

    public static String getDefaultDescription() {
        return defaultDescription;
    }

    public static ArrayList<Achievements> getAchievementList() {
        return achievementList;
    }

    public static int getNumToComplete0() {
        return numToComplete0;
    }

    public static String getZeroDescription() {
        return zeroDescription;
    }

    public static int getNumToComplete1() {
        return numToComplete1;
    }

    public static String getFirstDescription() {
        return firstDescription;
    }

    public static int getNumToComplete2() {
        return numToComplete2;
    }

    public static String getSecondDescription() {
        return secondDescription;
    }

    public static int getNumToComplete3() {
        return numToComplete3;
    }

    public static String getThirdDescription() {
        return ThirdDescription;
    }
}
