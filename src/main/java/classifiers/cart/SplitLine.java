package classifiers.cart;

import model.IndepVariable;

public class SplitLine {

    private IndepVariable splitVariable;
    private Double splitValue;
    private Double highLimit;
    private Double lowLimit;

    public IndepVariable getSplitVariable() {
        return splitVariable;
    }
    public void setSplitVariable(IndepVariable splitVariable) {
        this.splitVariable = splitVariable;
    }

    public Double getSplitValue() {
        return splitValue;
    }
    public void setSplitValue(Double splitValue) {
        this.splitValue = splitValue;
    }

    public Double getHighLimit() {return highLimit;}
    public void setHighLimit(Double highLimit) {this.highLimit = highLimit;}

    public Double getLowLimit() {return lowLimit;}
    public void setLowLimit(Double lowLimit) {this.lowLimit = lowLimit;}
}
