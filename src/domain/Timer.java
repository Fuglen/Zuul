package domain;

public class Timer {

    private static int day = 0; // The current day
    private static int movesMade = 0; // Amount of moves made this day
    private static int workTimer = 30; // Moves that can make before being to late for work
    private static int workEffort = 0; // If movesMade > workTimer = workEffort moves up. The lower the better
    private static int workEffortThreshold = 30; // If workEffort > workEffortThreshold the game is lost
    private static boolean fired = false; // If true, go to end screen

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
        Timer.fired = true;
    }
}
