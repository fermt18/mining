package classifiers.knn;

import model.Point;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.IntStream;

class Knn {

    Integer[][] training;
    public Knn(Integer[][] training){
        this.training = training;
    }

    Integer getPointValue(Point p){
        return training[p.getX()][p.getY()];
    }

    Double getPointDistance(Point p, Point q){
        return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow((q.getY() - p.getY()), 2));
    }

    List<Point> getKNNValues(int k, Point p){
        List<Point> pointList = new ArrayList<>();
        IntStream.range(0 , this.training.length).forEach( i -> {
            IntStream.range(0, this.training[i].length).forEach(j -> {
                Point q = new Point(i, j);
                if (getPointDistance(p, q) <= k)
                    pointList.add(q);
            });
        });
        return pointList;
    }

    Integer classify(int k, Point p){
        List<Point> pointList = getKNNValues(k, p);
        Predicate<Point> countZeroes = point -> getPointValue(point) == 0;
        Predicate<Point> countOnes   = point -> getPointValue(point) == 1;

        return pointList.stream().filter(countZeroes).count() >
                pointList.stream().filter(countOnes).count() ?
                0 : 1;
    }
}
