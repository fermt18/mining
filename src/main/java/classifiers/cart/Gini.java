package classifiers.cart;

import utils.Utils;

import java.util.List;
/*
GINI of a split
GINI(s,t) = GINI(t) – PL*GINI(tL) – PR*GINI(tR)
Where
s                              : split
t                              : node
GINI (t)                 : Gini Index of input node t
PL                            : Proportion of observation in Left Node after split, s
GINI (tL)                : Gini of Left Node after split, s
PR                            : Proportion of observation in Right Node after split, s
GINI (tR)               : Gini of Right Node after split, s*/
class Gini {

    private List<Double> inputNodePropList;
    private List<Double> leftNodePropList;
    private List<Double> rightNodePropList;
    private Double leftNodeObservedProp;
    private Double rightNodeObservedProp;

    void setInputNodePropList(List<Double> inputNodePropList) {
        this.inputNodePropList = inputNodePropList;
    }

    void setLeftNodePropList(List<Double> leftNodePropList) {
        this.leftNodePropList = leftNodePropList;
    }

    void setRightNodePropList(List<Double> rightNodePropList) {
        this.rightNodePropList = rightNodePropList;
    }

    void setLeftNodeObservedProp(Double leftNodeObservedProp) {
        this.leftNodeObservedProp = leftNodeObservedProp;
    }

    void setRightNodeObservedProp(Double rightNodeObservedProp) {
        this.rightNodeObservedProp = rightNodeObservedProp;
    }

    Double computeNodeCoefficient(List<Double> proportionList){
        double addition = 0.0;
        for(Double proportion : proportionList){
            addition += Math.pow(proportion, 2);
        }
        return Utils.roundToDecimals(1 - addition, 4);
    }

    Double computeSplitCoefficient(){
        Double giniInitialNode = computeNodeCoefficient(inputNodePropList);
        double giniLeftNode = computeNodeCoefficient(leftNodePropList) * leftNodeObservedProp;
        double giniRightNode = computeNodeCoefficient(rightNodePropList) * rightNodeObservedProp;
        return Utils.roundToDecimals(giniInitialNode - giniLeftNode - giniRightNode, 4);
    }
}
