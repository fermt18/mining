package classifiers.cart;

import utils.Utils;

import java.util.List;
/*
GINI of a split
G = ((1 – (g1_1^2 + g1_2^2)) * (ng1/n)) + ((1 – (g2_1^2 + g2_2^2)) * (ng2/n))
Where
Where G is the Gini index for the split point
g1_1 is the proportion of instances in left group for class 1
g1_2 is the proportion of instances in left group for class 2
g2_1 is the proportion of instances in right group for class 1
g2_2 is the proportion of instances in right group for class 2
ng1 is the total number of instances in left group
ng2 is the total number of instances in right group
n is the total number of instances we are trying to group from the parent node*/
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
