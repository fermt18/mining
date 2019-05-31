package classifiers.cart;

import junit.providers.TrainingForSplitProvider;
import model.IndepVariable;
import model.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_SelectNodeSplit {

    @ParameterizedTest
    @ArgumentsSource(TrainingForSplitProvider.class)
    void choose_split_by_independent_variable_and_value(List<Point> training, IndepVariable expSplitVar, Double expSplitVal){
        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(expSplitVar));
        assertThat(cart.getSplitValue(), is(expSplitVal));
    }
}
