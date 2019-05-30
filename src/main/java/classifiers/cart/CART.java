package classifiers.cart;

import model.IndepVariable;
import model.Point;
import model.TrainingSet;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


class CART {

    TrainingSet trainingSet;
    private Enum splitVariable;
    private Double splitValue;

    Enum getSplitVariable() {return splitVariable;}
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
            List<Double> valuesXAxis = obtainAxisValues(trainingSet.getTrainingSet(), IndepVariable.X);
            List<Double> valuesYAxis = obtainAxisValues(trainingSet.getTrainingSet(), IndepVariable.Y);
            sortedSplitValuesX = computeMidPoints(valuesXAxis);
            sortedSplitValuesY = computeMidPoints(valuesYAxis);
        }
        Map.Entry<Double, Double> minCoefficientForXVariable = obtainMinCoefficientForVariable(sortedSplitValuesX, IndepVariable.X);
        Map.Entry<Double, Double> minCoefficientForYVariable = obtainMinCoefficientForVariable(sortedSplitValuesY, IndepVariable.Y);
        if(minCoefficientForXVariable == null){
            splitVariable = IndepVariable.Y;
            splitValue = minCoefficientForYVariable.getKey();
        }
        else if(minCoefficientForYVariable == null){
            splitVariable = IndepVariable.X;
            splitValue = minCoefficientForXVariable.getKey();
        }
        else{
            splitVariable = (minCoefficientForXVariable.getValue() < minCoefficientForYVariable.getValue())
                    ? IndepVariable.X : IndepVariable.Y;
            splitValue = (minCoefficientForXVariable.getValue() < minCoefficientForYVariable.getValue())
                    ? minCoefficientForXVariable.getKey() : minCoefficientForYVariable.getKey();
        }
    }

    Map.Entry<Double, Double> obtainMinCoefficientForVariable(List<Double> sortedSplitValues, Enum splitAxis){
        Map<Double, Double> sortedCoefficientMapForVariable = new HashMap<>();
        for(Double splitValue : sortedSplitValues) {
            Gini gini = new Gini();
            gini.setInputNodePropList(obtainProportionsFromValues(trainingSet.getTrainingSet()));
            gini.setLeftNodePropList(obtainProportionsFromValues(obtainPointsLeftToPoint(trainingSet.getTrainingSet(), splitAxis, splitValue)));
            gini.setLeftNodeObservedProp(2.0/3);
            gini.setRightNodePropList(obtainProportionsFromValues(obtainPointsRightToPoint(trainingSet.getTrainingSet(), splitAxis, splitValue)));
            gini.setRightNodeObservedProp(1.0/3);
            sortedCoefficientMapForVariable.put(splitValue, gini.computeSplitCoefficient());
        }
        return sortedCoefficientMapForVariable.entrySet().stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))
                .entrySet().stream()
                .findFirst()
                .orElse(null);
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

    List<Double> obtainAxisValues(List<Point> pointList, Enum axis){
        List<Double> axisPoints = new ArrayList<>();
        if(axis.equals(IndepVariable.X))
            axisPoints = pointList.stream()
                .map(Point::getX)
                .collect(Collectors.toList());
        else if(axis.equals(IndepVariable.Y))
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

    List<Point> obtainPointsLeftToPoint(List<Point> pointList, Enum splitAxis, Double splitValue){
        List<Point> filteredPoints = null;
        if(splitAxis.equals(IndepVariable.X))
            filteredPoints = pointList.stream()
                .filter(p->p.getX()<splitValue)
                .collect(Collectors.toList());
        else if(splitAxis.equals(IndepVariable.Y))
            filteredPoints = pointList.stream()
                    .filter(p->p.getY()<splitValue)
                    .collect(Collectors.toList());
        return filteredPoints;
    }

    List<Point> obtainPointsRightToPoint(List<Point> pointList, Enum splitAxis, Double splitValue){
        List<Point> filteredPoints = null;
        if(splitAxis.equals(IndepVariable.X))
            filteredPoints = pointList.stream()
                    .filter(p->p.getX()>splitValue)
                    .collect(Collectors.toList());
        else if(splitAxis.equals(IndepVariable.Y))
            filteredPoints = pointList.stream()
                    .filter(p->p.getY()>splitValue)
                    .collect(Collectors.toList());
        return filteredPoints;
    }
}
