package classifiers.cart;

import model.IndepVariable;
import model.Point;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_SelectNodeSplit {

    @Test
    void choose_split_by_independent_variable_y_and_value(){
        List<Point> training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,0), 1.0));
        training.add(Utils.createPoint(new Point(0,2), 2.0));
        training.add(Utils.createPoint(new Point(1,2), 2.0));

        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(IndepVariable.X));
        assertThat(cart.getSplitValue(), is(0.5));
    }

    @Test
    void choose_split_by_independent_variable_x_and_value(){
        List<Point> training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,1), 1.0));
        training.add(Utils.createPoint(new Point(2,1), 2.0));

        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(IndepVariable.X));
        assertThat(cart.getSplitValue(), is(1.0));
    }
}
