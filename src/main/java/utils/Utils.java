package utils;

import model.Point;

public final class Utils {

    public static Double computePointDistance(Point p, Point q){
        return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow((q.getY() - p.getY()), 2));
    }
}
