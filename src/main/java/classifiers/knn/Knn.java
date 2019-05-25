package classifiers.knn;

import model.Point;
import utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Knn {

    private List<Point> dataSet;
    private Integer dataSetSize;
    private Double classA;
    private Double classB;

    Double getClassA(){return this.classA;}
    Double getClassB(){return this.classB;}

    public Knn(List<Point> training){
        fetchClassifierClasses(training);
        this.dataSet = createDataSet(training);
        addTrainingSetToDataSet(training);
    }

    public Double classify(int k, Point p){
        List<Point> neighbouringPointList = computeNN(k, p);
        long counterClassA = neighbouringPointList.stream().filter(byClass(this.classA)).count();
        long counterClassB = neighbouringPointList.stream().filter(byClass(this.classB)).count();
        return counterClassA > counterClassB ? this.classA : this.classB;
    }

    Integer computeSizeOfSquareDataSet(List<Point> trainingSet){
        Double max = 0.0;
        for(Point p : trainingSet){
            if(p.getX() > max) max = p.getX();
            if(p.getY() > max) max = p.getY();
        }
        return max.intValue()+1;
    }

    Double getValueFromCoordinates(Double x, Double y){
        return dataSet.stream()
                .filter(p->p.equals(new Point(x,y)))
                .findAny()
                .orElse(new Point(0,0))
                .getValue();
    }

    List<Point> computeNN(int k, Point p){
        List<Point> nnList = new ArrayList<>();
        if(isPointWithinBounds(p)) {
            IntStream.range(0, dataSet.size()).forEach(i -> {
                Point q = dataSet.get(i);
                if (q.getValue()!= null && Utils.computePointDistance(p, q) <= k)
                    nnList.add(q);
            });
        }
        return nnList;
    }

    private void fetchClassifierClasses(List<Point> training){
        List<Double> classList = new ArrayList<>();
        for(Point p : training) {
            if(!classList.contains(p.getValue()))
                classList.add(p.getValue());
        }
        if(classList.size() > 2)
            throw new IllegalArgumentException("Training set has more than two classes");

        this.classA = Collections.min(classList);
        this.classB = Collections.max(classList);
    }

    private List<Point> createDataSet(List<Point> trainingSet){
        List<Point> dataSet = new ArrayList<>();
        dataSetSize = computeSizeOfSquareDataSet(trainingSet);
        IntStream.range(0, dataSetSize).forEach(i->
                IntStream.range(0, dataSetSize).forEach(j-> {
            Point p = new Point(i, j);
            dataSet.add(p);
        }));
        return dataSet;
    }

    private void addTrainingSetToDataSet(List<Point> trainingSet){
        IntStream.range(0, dataSet.size()).forEach(i->{
            IntStream.range(0, trainingSet.size()).forEach(j->{
                if(dataSet.get(i).equals(trainingSet.get(j)))
                    dataSet.get(i).setValue(trainingSet.get(j).getValue());
            });
        });
    }

    private boolean isPointWithinBounds(Point p){
        return p.getY()<this.dataSetSize && p.getX()<this.dataSetSize;
    }

    private Predicate<Point> byClass(Double classification){
        return (Point p) -> {
            try {
                return getValueFromCoordinates(p.getX(), p.getY()).equals(classification);
            } catch (NullPointerException e) {
                return false;
            }
        };
    }
}
