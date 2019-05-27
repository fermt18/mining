package classifiers.cart;

import java.util.List;

public class Gini {

    private List<Integer> training;

    Gini(List<Integer> training){
        this.training = training;
    }

    Integer computeCoefficient(){
        if(training.get(0).equals(training.get(1)))
            return 0;
        return 1;
    }
}
