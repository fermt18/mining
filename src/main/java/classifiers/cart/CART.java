package classifiers.cart;

import model.IndepVariable;
import model.Point;
import model.TrainingSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

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
        Map<Map.Entry<IndepVariable, Double>, Double> impurityMap = new HashMap<>();
        computeMidPointsFromTrainingSet().entries().forEach(mp->{
            Double IOriginalRect = computeImpurity(trainingSet.getTrainingSet());
            Double ILeftRect = computeImpurity(divideRectangle(mp.getKey(), mp.getValue()).get(0));
            Double IRightRect = computeImpurity(divideRectangle(mp.getKey(), mp.getValue()).get(1));
            impurityMap.put(mp, IOriginalRect - (ILeftRect + IRightRect));
        });
        Map.Entry<IndepVariable, Double> highestReduction = impurityMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .findFirst()
                .get().getKey();
        splitVariable = highestReduction.getKey();
        splitValue = highestReduction.getValue();
    }

    MultiValuedMap<IndepVariable, Double> computeMidPointsFromTrainingSet(){
        MultiValuedMap<IndepVariable, Double> midPointsMap = new ArrayListValuedHashMap<>();
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

    private MultiValuedMap<IndepVariable, Double> computeMidPoints(List<Double> pointList, IndepVariable indepVariable){
        Collections.sort(pointList);
        MultiValuedMap<IndepVariable, Double> midPointsMap = new ArrayListValuedHashMap<>();
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
