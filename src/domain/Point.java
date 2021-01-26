package domain;

public class Point {

    //Attributes
    private static int point = 0;

    //Constructor
    public Point(){
    }

    //Methods
    public static int getPoint() {
        return point;
    }

    public static void addPoint(int point){
        Point.point = getPoint() + point;
    }
    
}
