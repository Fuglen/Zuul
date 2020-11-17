package worldofzuul;

public class Point {

    private int point;

    public Point(){
        this.point = 0;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point){
        this.point = getPoint() + point;
    }

    public void removePoint(int point){
        this.point = getPoint() - point;
    }
}
