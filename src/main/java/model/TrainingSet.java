package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingSet {

    private List<Point> trainingSet;
    private Integer numberOfClasses;
    private Double classA;
    private Double classB;

    public List<Point> getTrainingSet(){return this.trainingSet;}
    public Integer getNumberOfClasses(){return this.numberOfClasses;}
    public Double getClassA(){return this.classA;}
    public Double getClassB(){return this.classB;}

    public TrainingSet(List<Point> trainingSet){
        this.trainingSet = trainingSet;
        fetchClassifierClasses();
    }

    public Double getValueFromCoordinates(Double x, Double y){
        return trainingSet.stream()
                .filter(p->p.equals(new Point(x,y)))
                .findAny()
                .orElse(new Point(0,0))
                .getValue();
    }

    private void fetchClassifierClasses(){
        List<Double> classList = new ArrayList<>();
        for(Point p : this.trainingSet) {
            if(!classList.contains(p.getValue()))
                classList.add(p.getValue());
        }
        if(classList.size() > 2)
            throw new IllegalArgumentException("Training set has more than two classes");

        this.numberOfClasses = classList.size();
        this.classA = Collections.min(classList);
        this.classB = Collections.max(classList);
    }
}
