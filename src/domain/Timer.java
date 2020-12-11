package domain;

import java.util.ArrayList;

public class Timer {

    private static int day = 0; // The current day
    private static int movesMade = 0; // Amount of moves made this day
    private static int workTimer = 36; // Moves that can make before being to late for work
    private static int workEffort = 0; // If movesMade > workTimer = workEffort moves up. The lower the better
    private static int workEffortThreshold = 20; // If workEffort > workEffortThreshold the game is lost
    private static boolean fired = false; // If true, go to end screen
    private static ArrayList<Room> WorkHome = new ArrayList<Room>();

    public Timer(){
    }

    public static int getDay() {
        return day;
    }

    public static void setDay() {
        Timer.day = Timer.day + 1;
    }

    public static int getMovesMade() {
        return movesMade;
    }

    public static void setMovesMade(int movesMade) {
        Timer.movesMade = movesMade;
    }

    // Not if it is day 0
    public static void setMovesMade() {
        if(Timer.getDay() > 0){
            Timer.movesMade = Timer.movesMade + 1;
        }
    }

    public static int getWorkTimer() {
        return workTimer;
    }

    public static void setWorkTimer(int workTimer) {
        Timer.workTimer = workTimer;
    }

    public static int getWorkEffort() {
        return workEffort;
    }

    public static void setWorkEffort(int workEffort) {
        Timer.workEffort = workEffort;
    }

    public static int getWorkEffortThreshold() {
        return workEffortThreshold;
    }

    public static boolean isFired() {
        return fired;
    }

    public static void setFired() {
        Timer.fired = !isFired();
    }

    public static ArrayList<Room> getWorkHome() {
        return WorkHome;
    }
}
