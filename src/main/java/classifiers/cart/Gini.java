package classifiers.cart;

import utils.Utils;

import java.util.List;

class Gini {

    // Impurity of a rectangle
    // Ig(p) = 1-SUM(Pi^2); Pi fraction of items labeled with class i
    Double computeNodeImpurity(List<Double> proportionList){
        double addition = 0.0;
        for(Double proportion : proportionList){
            addition += Math.pow(proportion, 2);
        }
        return Utils.roundToDecimals(1 - addition, 4);
    }

    // Impurity of a split -> impurity reduction must be highest
    // Impurity Reduction = Ig(original rectangle) - (Ig(left rectangle) + Ig(right rectangle))

}
