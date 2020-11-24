package worldofzuul;

import java.util.ArrayList;

public class Quest {

    private int type;
    private String description;
    private int points;
    private int id;

    public Quest(String desc, int points, int type){
        this.description = desc;
        this.points = points;
        this.type = type;
    }

    public void newQuest(String desc, int points, int type) {
        this.description = desc;
        this.points = points;
        this.type = type;
    }

    public void removeCurrentQuests() {

    }

    @Override
    public String toString() {
        return "'" + description + '\'';
    }
}
