package classifiers.cart;

import junit.providers.TrainingForSplitProvider;
import model.IndepVariable;
import model.Point;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
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
    void get_midpoints_from_different_points() {
        CART cart = new CART(Arrays.asList(new Point(0, 1), new Point(1, 2)));
        MultiValuedMap<IndepVariable, Double> expectedMap = new ArrayListValuedHashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.Y, 1.5);
        assertThat(cart.computeMidPointsFromTrainingSet(), equalTo(expectedMap));
    }

    @Test
    void get_midpoints_from_repeated_points() {
        CART cart = new CART(Arrays.asList(
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 3)));
        MultiValuedMap<IndepVariable, Double> expectedMap = new ArrayListValuedHashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.Y, 2.0);
        assertThat(cart.computeMidPointsFromTrainingSet(), equalTo(expectedMap));
    }

    @Test
    void get_midpoints_from_consecutive_points() {
        CART cart = new CART(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)));
        MultiValuedMap<IndepVariable, Double> expectedMap = new ArrayListValuedHashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.X, 1.5);
        expectedMap.put(IndepVariable.X, 2.5);
        expectedMap.put(IndepVariable.Y, 0.5);
        expectedMap.put(IndepVariable.Y, 1.5);
        expectedMap.put(IndepVariable.Y, 2.5);
        MultiValuedMap<IndepVariable, Double> map = cart.computeMidPointsFromTrainingSet();
        assertThat(map.entries().size(), is(6));
        assertThat(map, equalTo(expectedMap));
    }

    @Test
    void get_midpoints_from_unsorted_points(){
        CART cart = new CART(Arrays.asList(
                new Point(2, 2),
                new Point(1, 1),
                new Point(3, 3),
                new Point(0, 0)));
        MultiValuedMap<IndepVariable, Double> expectedMap = new ArrayListValuedHashMap<>();
        expectedMap.put(IndepVariable.X, 0.5);
        expectedMap.put(IndepVariable.X, 1.5);
        expectedMap.put(IndepVariable.X, 2.5);
        expectedMap.put(IndepVariable.Y, 0.5);
        expectedMap.put(IndepVariable.Y, 1.5);
        expectedMap.put(IndepVariable.Y, 2.5);
        MultiValuedMap<IndepVariable, Double> map = cart.computeMidPointsFromTrainingSet();
        assertThat(map.entries().size(), is(6));
        assertThat(map, equalTo(expectedMap));
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
    void divide_rectangle_into_left_and_right_from_split_point(){
        List<Point> rectangle = Arrays.asList(
                Utils.createPoint(new Point(1,1), 10.0),
                Utils.createPoint(new Point(2,2), 20.0));
        CART cart = new CART(rectangle);
        List<Point> leftRectangle = Collections.singletonList(Utils.createPoint(new Point(1,1), 10.0));
        List<Point> rightRectangle = Collections.singletonList(Utils.createPoint(new Point(2,2), 20.0));
        List<List<Point>> splitedRectangles = cart.divideRectangle(rectangle, IndepVariable.X, 1.5);
        assertThat(splitedRectangles.size(), is(2));
        assertThat(splitedRectangles.get(0), equalTo(leftRectangle));
        assertThat(splitedRectangles.get(1), equalTo(rightRectangle));
    }

    @Test
    void get_rectangle_list_from_split_list(){
        List<Point> rectangle = Arrays.asList(
                Utils.createPoint(new Point(0,0), 1.0),
                Utils.createPoint(new Point(0,1), 2.0));
        List<Point> rectangle1 = Collections.singletonList(new Point(0,0));
        List<Point> rectangle2 = Collections.singletonList(new Point(1,1));
        CART cart = new CART(rectangle);
        cart.nextSplit();
        assertThat(cart.createRectangleListFromSplitList(), equalTo(Arrays.asList(rectangle1, rectangle2)));
    }

    @ParameterizedTest
    @ArgumentsSource(TrainingForSplitProvider.class)
    void choose_split_by_independent_variable_and_value(
            List<Point> training, IndepVariable expSplitVar, Double expSplitVal){
        CART cart = new CART(training);
        cart.nextSplit();
        assertThat(cart.getSplitLineList().get(1).getSplitVariable(), is(expSplitVar));
        assertThat(cart.getSplitLineList().get(1).getSplitValue(), is(expSplitVal));
    }
}
