package worldofzuul;

public class Point {

    private int point;

    public Point(){

    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int points){
        this.point += getPoint();
    }

    public void removePoint(int points){
        this.point -= getPoint();
    }
}
