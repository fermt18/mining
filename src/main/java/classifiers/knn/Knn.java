package classifiers.knn;

import model.Point;
import utils.Utils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Knn {

    private List<Point> training;
    private Double classA;
    private Double classB;

    Double getClassA(){return this.classA;}
    Double getClassB(){return this.classB;}

    public Knn(List<Point> training){
        fetchClassifierClasses(training);
        this.training = training;
    }

    public Double classify(int k, Point p){
        List<Point> neighbouringPointList = computeNN(k, p);
        if(neighbouringPointList.size() == 0)
            return null; // this should happen only when training set is empty
        long counterClassA = neighbouringPointList.stream().filter(byClass(this.classA)).count();
        long counterClassB = neighbouringPointList.stream().filter(byClass(this.classB)).count();
        return counterClassA > counterClassB ? this.classA : this.classB;
    }

    Double getValueFromCoordinates(Double x, Double y){
        return training.stream()
                .filter(p->p.equals(new Point(x,y)))
                .findAny()
                .orElse(new Point(0,0))
                .getValue();
    }

    List<Point> computeNN(int k, Point p){
        Map<Point, Double> pointDistance = new LinkedHashMap<>();
        IntStream.range(0, training.size()).forEach(i -> {
            pointDistance.put(training.get(i), Utils.computePointDistance(p, training.get(i)));
        });
        Map<Point, Double> pointDistanceSorted = pointDistance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        List<Point> nnList = new ArrayList<>();
        int i=0;
        for (Map.Entry<Point, Double> entry : pointDistanceSorted.entrySet()) {
            nnList.add(entry.getKey());
            i++;
            if(i==k)
                break;
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
