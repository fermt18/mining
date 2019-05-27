package classifiers.cart;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_Gini {

    @Test
    void max_inequality(){
        List<Integer> training = Arrays.asList(0,1);
        Gini gini = new Gini(training);
        assertThat(gini.computeCoefficient(), is(1));
    }

    @Test
    void max_equality(){
        List<Integer> training = Arrays.asList(1,1);
        Gini gini = new Gini(training);
        assertThat(gini.computeCoefficient(), is(0));
    }
}
