package classifiers.knn;

import model.Point;
import model.TrainingSet;
import utils.Utils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Knn {

    private TrainingSet trainingSet;

    public Knn(List<Point> training){
        trainingSet = new TrainingSet(training);
    }

    public Double classify(int k, Point p){
        List<Point> neighbouringPointList = computeNN(k, p);
        if(neighbouringPointList.size() == 0)
            return null; // this should happen only when training set is empty
        long counterClassA = neighbouringPointList.stream().filter(byClass(trainingSet.getClassA())).count();
        long counterClassB = neighbouringPointList.stream().filter(byClass(trainingSet.getClassB())).count();
        return counterClassA > counterClassB ? trainingSet.getClassA() : trainingSet.getClassB();
    }

    List<Point> computeNN(int k, Point p){
        Map<Point, Double> pointDistance = new LinkedHashMap<>();
        IntStream.range(0, trainingSet.getTrainingSet().size()).forEach(i -> {
            pointDistance.put(
                    trainingSet.getTrainingSet().get(i),
                    Utils.computePointDistance(p, trainingSet.getTrainingSet().get(i)));
        });
        return pointDistance.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    private Predicate<Point> byClass(Double classification){
        return (Point p) -> {
            try {
                return trainingSet.getValueFromCoordinates(p.getX(), p.getY()).equals(classification);
            } catch (NullPointerException e) {
                return false;
            }
        };
    }
}
