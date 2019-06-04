package classifiers.cart;

import model.IndepVariable;
import model.Point;
import model.TrainingSet;

import java.util.*;

public class CART {

    private TrainingSet trainingSet;
    private IndepVariable splitVariable;
    private Double splitValue;

    public IndepVariable getSplitVariable() {return splitVariable;}
    public Double getSplitValue() {return splitValue;}

    public CART(List<Point> training){
        trainingSet = new TrainingSet(training);
    }

    public void nextSplit(){
        //take mid points
        // for each mid point
            // compute gini original rectangle
            // compute gini new left rectangle
            // compute gini new right rectangle
            // add Impurity reduction(gini original - (gini new left + gini new right))

        // choose mid point with highest impurity reduction
    }

    Map<IndepVariable, Double> computeMidPointsFromTrainingSet(){
        Map<IndepVariable, Double> midPointsMap = new HashMap<>();
        List<Double> xAxisPointList = new ArrayList<>();
        List<Double> yAxisPointList = new ArrayList<>();
        trainingSet.getTrainingSet().forEach(p->{
            xAxisPointList.add(p.getX());
            yAxisPointList.add(p.getY());
        });
        midPointsMap.putAll(computeMidPoints(new ArrayList<>(new HashSet<>(xAxisPointList)), IndepVariable.X));
        midPointsMap.putAll(computeMidPoints(new ArrayList<>(new HashSet<>(yAxisPointList)), IndepVariable.Y));
        return midPointsMap;
    }

    private Map<IndepVariable, Double> computeMidPoints(List<Double> pointList, IndepVariable indepVariable){
        Map<IndepVariable, Double> midPointsMap = new HashMap<>();
        for(int i=0; i<pointList.size()-1; i++){
            midPointsMap.put(indepVariable, (pointList.get(i) + pointList.get(i+1))/2);
        }
        return midPointsMap;
    }

    Double computeImpurity(List<Point> rectangle){
        long counterA = rectangle.stream()
                .filter(p->p.getValue().equals(trainingSet.getClassA()))
                .count();
        return new Gini().computeNodeImpurity(Arrays.asList(
                (double)(counterA)/rectangle.size(),
                (double)(rectangle.size()-counterA)/rectangle.size()));
    }

    List<List<Point>> divideRectangle(IndepVariable splitVariable, Double splitValue){
        List<Point> leftRectangle = new ArrayList<>();
        List<Point> rightRectangle = new ArrayList<>();
        trainingSet.getTrainingSet().forEach(p->{
            if(splitVariable.equals(IndepVariable.X)){
                if(p.getX()<splitValue)
                    leftRectangle.add(p);
                else
                    rightRectangle.add(p);
            }
            else if(splitVariable.equals(IndepVariable.Y)){
                if(p.getY()<splitValue)
                    leftRectangle.add(p);
                else
                    rightRectangle.add(p);
            }
        });
        return Arrays.asList(leftRectangle, rightRectangle);
    }
}
