package classifiers.cart;

import model.IndepVariable;
import model.Point;
import model.TrainingSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;

public class CART {

    private TrainingSet trainingSet;
    private List<SplitLine> splitLineList;

    public List<SplitLine> getSplitLineList() {return splitLineList;}

    public CART(List<Point> training){
        trainingSet = new TrainingSet(training);
        splitLineList = new ArrayList<>();
        splitLineList.add(new SplitLine());
    }

    public void nextSplit() {
        SplitLine potentialSplitLine = new SplitLine();
        for(List<Point> rectangle : createRectangleListFromSplitList()) {
            Map.Entry<IndepVariable, Double> highestReduction = computeImpurityMap(rectangle);
            potentialSplitLine.setSplitVariable(highestReduction.getKey());
            potentialSplitLine.setSplitValue(highestReduction.getValue());
            potentialSplitLine.setHighLimit(null);
            potentialSplitLine.setLowLimit(0.0);
        }
        //SplitLine nextSplitLine = new SplitLine();
        //nextSplitLine.setSplitVariable(highestReduction.getKey());
        //nextSplitLine.setSplitValue(highestReduction.getValue());
        splitLineList.add(potentialSplitLine);
    }

    List<List<Point>> createRectangleListFromSplitList(){
        List<List<Point>> rectangleList = new ArrayList<>();
        for(SplitLine splitLine : splitLineList) {
            if(splitLine.getSplitVariable()==null && splitLine.getSplitValue()==null)
                rectangleList.add(trainingSet.getTrainingSet());
            else {
                //rectangleList.add(Collections.singletonList(new Point(0,0)));
                //rectangleList.add(Collections.singletonList(new Point(1,1)));
                List<List<Point>> dividedRectangles = divideRectangle(trainingSet.getTrainingSet(), splitLine);
                rectangleList.add(dividedRectangles.get(0));
                rectangleList.add(dividedRectangles.get(1));
            }
        }
        return rectangleList;
    }

    Map.Entry<IndepVariable, Double> computeImpurityMap(List<Point> rectangle){
        Map<Map.Entry<IndepVariable, Double>, Double> impurityMap = new HashMap<>();
        computeMidPointsFromTrainingSet().entries().forEach(mp -> {
            SplitLine splitLine = new SplitLine();
            splitLine.setSplitVariable(mp.getKey());
            splitLine.setSplitValue(mp.getValue());
            Double IOriginalRect = computeImpurity(rectangle);
            Double ILeftRect = computeImpurity(divideRectangle(rectangle, splitLine).get(0));
            Double IRightRect = computeImpurity(divideRectangle(rectangle, splitLine).get(1));
            impurityMap.put(mp, IOriginalRect - (ILeftRect + IRightRect));
        });
        return impurityMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
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

    List<List<Point>> divideRectangle(List<Point> rectangle, SplitLine splitLine){
        List<Point> leftRectangle = new ArrayList<>();
        List<Point> rightRectangle = new ArrayList<>();
        rectangle.forEach(p-> {
            switch (splitLine.getSplitVariable()) {
                case X:
                    if (p.getX() < splitLine.getSplitValue())  leftRectangle.add(p);
                    else
                        rightRectangle.add(p);
                    break;

                case Y:
                    if (p.getY() < splitLine.getSplitValue())  leftRectangle.add(p);
                    else
                        rightRectangle.add(p);
                    break;
            }
        });
        return Arrays.asList(leftRectangle, rightRectangle);
    }
}
