package domain;

class Point {

    //Attributes
    private int point;

    //Constructors
    public Point(){
        this.point = 0;
    }

    //Methods
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
