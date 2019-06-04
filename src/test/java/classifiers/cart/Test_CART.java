package classifiers.cart;

import junit.providers.TrainingForSplitProvider;
import model.IndepVariable;
import model.Point;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import utils.Utils;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_CART {

    @Test
    void get_midpoints_from_different_points(){
        CART cart = new CART(Arrays.asList(new Point(0, 1), new Point(1,2)));
        Map<IndepVariable, Double> expectedMap = new HashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.Y, 1.5);
        assertThat(cart.computeMidPointsFromTrainingSet(), equalTo(expectedMap));
    }

    @Test
    void get_midpoints_from_repeated_points(){
        CART cart = new CART(Arrays.asList(
                new Point(0, 1),
                new Point(1,1),
                new Point(1,3)));
        Map<IndepVariable, Double> expectedMap = new HashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.Y, 2.0);
        assertThat(cart.computeMidPointsFromTrainingSet(), equalTo(expectedMap));
    }

    @Test
    void compute_impurity_for_a_rectangle_with_max_impurity(){
        List<Point> rectangle = Arrays.asList(
                Utils.createPoint(new Point(1,1), 10.0),
                Utils.createPoint(new Point(2,2), 20.0));
        CART cart = new CART(rectangle);
        assertThat(cart.computeImpurity(rectangle), is(0.5));
    }

    @Test
    void get_rectangle_from_split_point(){
        List<Point> rectangle = Arrays.asList(
                Utils.createPoint(new Point(1,1), 10.0),
                Utils.createPoint(new Point(2,2), 20.0));
        CART cart = new CART(rectangle);
        List<Point> leftRectangle = Collections.singletonList(Utils.createPoint(new Point(1,1), 10.0));
        List<Point> rightRectangle = Collections.singletonList(Utils.createPoint(new Point(2,2), 20.0));
        List<List<Point>> splitedRectangles = cart.divideRectangle(IndepVariable.X, 1.5);
        assertThat(splitedRectangles.size(), is(2));
        assertThat(splitedRectangles.get(0), equalTo(leftRectangle));
        assertThat(splitedRectangles.get(1), equalTo(rightRectangle));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(TrainingForSplitProvider.class)
    void choose_split_by_independent_variable_and_value(
            List<Point> training, IndepVariable expSplitVar, Double expSplitVal){
        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(expSplitVar));
        assertThat(cart.getSplitValue(), is(expSplitVal));
    }
}
