package model;

public class Point {

    private Integer x;
    private Integer y;
    private Double value;

    public Integer getX(){return this.x;}
    public Integer getY(){return this.y;}
    public Double getValue(){return this.value;}
    public void setValue(Double value){this.value = value;}

    public Point(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
/*
    @Override
    public boolean equals(Object object){
        if(this==object)
            return true;

        Point other = (Point)object;
        if(this.x.equals(other.x) && this.y.equals(other.y))
            return true;

        return false;
    }*/
}
