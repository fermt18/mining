package classifiers.knn;

import model.Point;
import utils.Utils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

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
    }

    public Double classify(int k, Point p){
        List<Point> neighbouringPointList = computeNN(k, p);
        if(neighbouringPointList.size() == 0)
            return null; // this should happen only when training set is empty
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
        Map<Point, Double> pointDistance = new LinkedHashMap<>();
        if(isPointWithinBounds(p)) {
            IntStream.range(0, dataSet.size()).forEach(i -> {
                pointDistance.put(dataSet.get(i), Utils.computePointDistance(p, dataSet.get(i)));
            });
        }
        Map<Point, Double> pointDistanceSorted = pointDistance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        List<Point> nnList = new ArrayList<>();
        for (Map.Entry<Point, Double> entry : pointDistanceSorted.entrySet()) {
            nnList.add(entry.getKey());
        }
        return nnList;
    }

    private void fetchClassifierClasses(List<Point> trainingSet){
        List<Double> classList = new ArrayList<>();
        for(Point p : trainingSet) {
            if(!classList.contains(p.getValue()))
                classList.add(p.getValue());
        }
        if(classList.size() > 2)
            throw new IllegalArgumentException("Training set has more than two classes");

        this.classA = Collections.min(classList);
        this.classB = Collections.max(classList);
    }

    private List<Point> createDataSet(List<Point> trainingSet){
        dataSetSize = computeSizeOfSquareDataSet(trainingSet);
        return trainingSet;
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
