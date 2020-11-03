<<<<<<< Updated upstream
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
=======
package worldofzuul;

public class Point {

    private int point;

    public Point(){
        this.point = 0;
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
>>>>>>> Stashed changes
