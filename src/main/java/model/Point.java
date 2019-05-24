package model;

public class Point {

    private Double x;
    private Double y;
    private Double value;

    public Double getX(){return this.x;}
    public Double getY(){return this.y;}
    public Double getValue(){return this.value;}
    public void setValue(Double value){this.value = value;}

    public Point(Integer x, Integer y){
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
    }

    public Point(Double x, Double y){
        this.x = x;
        this.y = y;
    }

    public Point(Double x, Integer y){
        this.x = x;
        this.y = Double.valueOf(y);
    }

    public Point(Integer x, Double y){
        this.x = Double.valueOf(x);
        this.y = y;
    }

    @Override
    public boolean equals(Object object){
        if(this==object)
            return true;

        Point other = (Point)object;
        if(this.x.equals(other.x) && this.y.equals(other.y))
            return true;

        return false;
    }
}
