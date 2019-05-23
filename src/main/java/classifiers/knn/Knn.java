package classifiers.knn;

import model.Point;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.IntStream;

class Knn {

    private List<Point> dataSet;
    private Integer dataSetSize;
    Knn(){}
    Knn(List<Point> training){
        this.dataSet = createDataSet(training);
        addTrainingSetToDataSet(training);
    }

    private List<Point> createDataSet(List<Point> trainingSet){
        List<Point> dataSet = new ArrayList<>();
        dataSetSize = computeSizeOfSquareDataSet(trainingSet);
        IntStream.range(0, dataSetSize).forEach(i->{
            IntStream.range(0, dataSetSize).forEach(j-> {
                Point p = new Point(i, j);
                p.setValue(0.0);
                dataSet.add(p);
            });
        });
        return dataSet;
    }

    private Integer computeSizeOfSquareDataSet(List<Point> trainingSet){
        Integer xMax = 0;
        Integer yMax = 0;
        for(Point p : trainingSet){
            if(p.getX() > xMax) xMax = p.getX();
            if(p.getY() > yMax) yMax = p.getY();
        }
        return (xMax > yMax) ? xMax+1 : yMax+1;
    }

    private void addTrainingSetToDataSet(List<Point> trainingSet){
        IntStream.range(0, dataSet.size()).forEach(i->{
            IntStream.range(0, trainingSet.size()).forEach(j->{
                if(dataSet.get(i).equals(trainingSet.get(j)))
                    dataSet.get(i).setValue(trainingSet.get(j).getValue());
            });
        });
    }

    Double getValueFromCoordinates(Integer x, Integer y){
        for(Point p : dataSet){
           if(p.getX().equals(x) && p.getY().equals(y))
               return p.getValue();
        }
        return null;
    }

    Double computePointDistance(Point p, Point q){
        return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow((q.getY() - p.getY()), 2));
    }

    List<Point> computeNN(int k, Point p){
        List<Point> pointList = new ArrayList<>();
        if(isPointWithinBounds(p)) {
            IntStream.range(0, dataSet.size()).forEach(i -> {
                Point q = dataSet.get(i);
                if (computePointDistance(p, q) <= k)
                    pointList.add(q);
            });
        }
        return pointList;
    }

    Integer classify(int k, Point p){
        List<Point> pointList = computeNN(k, p);
        Predicate<Point> countZeroes = point -> getValueFromCoordinates(point.getX(), point.getY()) == 0.0;
        Predicate<Point> countOnes   = point -> getValueFromCoordinates(point.getX(), point.getY()) == 1.0;

        return pointList.stream().filter(countZeroes).count() >
                pointList.stream().filter(countOnes).count() ?
                0 : 1;
    }

    private boolean isPointWithinBounds(Point p){
        if(p.getY()>this.dataSetSize || p.getX()>this.dataSetSize)
            return false;

        return false;
    }
}
