package classifiers.cart;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_SelectNodeSplit {

    private List<Point> training;

    @BeforeEach
    void init(){
        training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,0), 1.0));
        training.add(Utils.createPoint(new Point(0,2), 2.0));
        training.add(Utils.createPoint(new Point(1,2), 2.0));
    }

    @Test
    void choose_split_by_independent_variable_and_value(){
        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is("y"));
        assertThat(cart.getSplitValue(), is(1.0));
    }
}
