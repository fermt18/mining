package classifiers.cart;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_Gini {

    @Test
    void max_equality(){
        List<Double> proportionList = Arrays.asList(0.0, 1.0);
        Gini gini = new Gini(proportionList);
        assertThat(gini.computeCoefficient(), is(0.0));
    }

    @Test
    void max_inequality(){
        List<Double> proportionList = Arrays.asList(0.5, 0.5);
        Gini gini = new Gini(proportionList);
        assertThat(gini.computeCoefficient(), is(0.5));
    }

    @Test
    void medium_range_equality_two_variables(){
        List<Double> proportionList = Arrays.asList(0.74, 0.26);
        Gini gini = new Gini(proportionList);
        assertThat(gini.computeCoefficient(), is(0.3848));
    }

    @Test
    void medium_range_equality_more_than_two_variables(){
        List<Double> proportionList = Arrays.asList(0.08, 0.13, 0.29, 0.5);
        Gini gini = new Gini(proportionList);
        assertThat(gini.computeCoefficient(), is(0.6426));
    }
}
