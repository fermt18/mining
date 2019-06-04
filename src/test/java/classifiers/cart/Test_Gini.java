package classifiers.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_Gini {

    private Gini gini;
    @BeforeEach
    void init(){
        gini = new Gini();
    }

    @Test
    void gini_node_max_equality(){
        List<Double> proportionList = Arrays.asList(0.0, 1.0);
        assertThat(gini.computeNodeImpurity(proportionList), is(0.0));
    }

    @Test
    void gini_node_max_inequality(){
        List<Double> proportionList = Arrays.asList(0.5, 0.5);
        assertThat(gini.computeNodeImpurity(proportionList), is(0.5));
    }

    @Test
    void gini_node_medium_range_equality_two_variables(){
        List<Double> proportionList = Arrays.asList(0.74, 0.26);
        assertThat(gini.computeNodeImpurity(proportionList), is(0.3848));
    }

    @Test
    void gini_node_medium_range_equality_more_than_two_variables(){
        List<Double> proportionList = Arrays.asList(0.08, 0.13, 0.29, 0.5);
        assertThat(gini.computeNodeImpurity(proportionList), is(0.6426));
    }
}
