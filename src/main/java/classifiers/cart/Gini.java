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

    private List<Double> proportionList;

    Gini(List<Double> proportionList){
        this.proportionList = proportionList;
    }

    Double computeCoefficient(){
        Double addition = 0.0;
        for(Double proportion : proportionList){
            addition += Math.pow(proportion, 2);
        }
        return Utils.roundToDecimals(1 - addition, 4);
    }
}
