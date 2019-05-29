package classifiers.cart;

import model.Point;
import model.TrainingSet;

import java.util.*;
import java.util.stream.Collectors;


public class CART {

    TrainingSet trainingSet;
    private String splitVariable;
    private Double splitValue;

    String getSplitVariable() {return splitVariable;}
    Double getSplitValue() {return splitValue;}

    CART(){}
    CART(List<Point> training){
        trainingSet = new TrainingSet(training);
    }

    void nextSplit(){
        List<Double> sortedSplitValuesX = new ArrayList<>();
        List<Double> sortedSplitValuesY = new ArrayList<>();
        for(int i=0; i<trainingSet.getNumberOfClasses(); i++) {
            // find possible split values -> mid points between pairs of consecutive values of each variable
            List<Double> valuesXAxis = obtainAxisValues(trainingSet.getTrainingSet(), "x");
            List<Double> valuesYAxis = obtainAxisValues(trainingSet.getTrainingSet(), "y");
            sortedSplitValuesX = computeMidPoints(valuesXAxis);
            sortedSplitValuesY = computeMidPoints(valuesYAxis);
        }

        for(Double splitValue : sortedSplitValuesY) {
            // rank according how much reduce impurity
            // impurity(rectangle before the split) - SUM(impurity(new rectangle left) + impurity(new rectangle left))
            Gini gini = new Gini();
            //gini.setInputNodePropList(obtainProportionsFromValues(sortedSplitValuesX));
            gini.setLeftNodePropList(obtainProportionsFromValues(obtainPointsLeftToPoint(trainingSet.getTrainingSet(), "y", splitValue)));
            gini.setLeftNodeObservedProp(2.0/3);
            gini.setRightNodePropList(obtainProportionsFromValues(obtainPointsRightToPoint(trainingSet.getTrainingSet(), "y", splitValue)));
            gini.setRightNodeObservedProp(1.0/3);
        }
        // split variable is the one that has the minimum impurity taking into account both variables
        splitVariable = "y";
        splitValue = Collections.min(sortedSplitValuesY);
    }

    List<Double> obtainProportionsFromValues(List<Point> pointList){
        double classA = pointList.get(0).getValue();
        long counterA = pointList.stream()
                .filter(p->p.getValue()==classA)
                .count();
        return Arrays.asList(
                (double)(counterA)/pointList.size(),
                (double)(pointList.size()-counterA)/pointList.size());
    }

    List<Double> obtainAxisValues(List<Point> pointList, String axis){
        List<Double> axisPoints = new ArrayList<>();
        if(axis.equals("x"))
            axisPoints = pointList.stream()
                .map(Point::getX)
                .collect(Collectors.toList());
        else if(axis.equals("y"))
            axisPoints = pointList.stream()
                    .map(Point::getY)
                    .collect(Collectors.toList());
        return axisPoints;
    }

    List<Double> computeMidPoints(List<Double> valueList){
        List<Double> uniqueValueList = new ArrayList<>(new HashSet<>(valueList));
        Collections.sort(uniqueValueList);
        List<Double> midPointList = new ArrayList<>();
        for(int i=0; i<uniqueValueList.size()-1; i++){
            midPointList.add((uniqueValueList.get(i) + uniqueValueList.get(i+1))/2);
        }
        return midPointList;
    }

    List<Point> obtainPointsLeftToPoint(List<Point> pointList, String splitAxis, Double splitValue){
        List<Point> filteredPoints = null;
        if(splitAxis.equals("x"))
            filteredPoints = pointList.stream()
                .filter(p->p.getX()<splitValue)
                .collect(Collectors.toList());
        else if(splitAxis.equals("y"))
            filteredPoints = pointList.stream()
                    .filter(p->p.getY()<splitValue)
                    .collect(Collectors.toList());
        return filteredPoints;
    }

    List<Point> obtainPointsRightToPoint(List<Point> pointList, String splitAxis, Double splitValue){
        List<Point> filteredPoints = null;
        if(splitAxis.equals("x"))
            filteredPoints = pointList.stream()
                    .filter(p->p.getX()>splitValue)
                    .collect(Collectors.toList());
        else if(splitAxis.equals("y"))
            filteredPoints = pointList.stream()
                    .filter(p->p.getY()>splitValue)
                    .collect(Collectors.toList());
        return filteredPoints;
    }
}
