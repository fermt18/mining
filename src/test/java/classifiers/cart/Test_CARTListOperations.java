package classifiers.cart;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_CARTListOperations {

    private CART cart;
    @BeforeEach
    void init(){
        cart = new CART();
    }

    @Test
    void obtain_proportions_from_points(){
        List<Point> valueList = Arrays.asList(
                Utils.createPoint(new Point(0.0, 1.0), 1.0),
                Utils.createPoint(new Point(0.0, 2.0),1.0),
                Utils.createPoint(new Point(0.0, 3.0),2.0),
                Utils.createPoint(new Point(0.0, 4.0),2.0));
        List<Double> expected = Arrays.asList(0.5, 0.5);
        List<Double> proportionList = cart.obtainProportionsFromValues(valueList);
        assertThat(proportionList, is(expected));
    }

    @Test
    void obtain_consecutive_axis_values_x(){
        List<Point> valueList = Arrays.asList(
                new Point(1,0),
                new Point(43.2, 1),
                new Point(3, 2));
        List<Double> expected = Arrays.asList(1.0, 43.2, 3.0);
        assertThat(cart.obtainAxisValues(valueList, "x"), equalTo(expected));
    }

    @Test
    void obtain_consecutive_axis_values_y(){
        List<Point> valueList = Arrays.asList(
                new Point(1,0),
                new Point(43.2, 1),
                new Point(3, 2));
        List<Double> expected = Arrays.asList(0.0, 1.0, 2.0);
        assertThat(cart.obtainAxisValues(valueList, "y"), equalTo(expected));
    }

    @Test
    void obtain_mid_points_with_sorted_list(){
        List<Double> valueList = Arrays.asList(1.0, 3.0, 33.0, 43.2);
        List<Double> expectedMidPointList = Arrays.asList(2.0, 18.0, 38.1);
        assertThat(cart.computeMidPoints(valueList), equalTo(expectedMidPointList));
    }

    @Test
    void obtain_mid_points_with_unsorted_list(){
        List<Double> valueList = Arrays.asList(3.0, 33.0, 1.0, 43.2);
        List<Double> expectedMidPointList = Arrays.asList(2.0, 18.0, 38.1);
        assertThat(cart.computeMidPoints(valueList), equalTo(expectedMidPointList));
    }

    @Test
    void obtain_mid_points_repeated_poits(){
        List<Double> valueList = Arrays.asList(1.0, 2.0, 2.0, 3.0);
        List<Double> expectedMidPointList = Arrays.asList(1.5, 2.5);
        assertThat(cart.computeMidPoints(valueList), equalTo(expectedMidPointList));
    }

    @Test
    void obtain_values_left_to_point_at_x_axis(){
        List<Point> pointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2),
                new Point(2, 3));
        List<Point> expectedPointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2));
        assertThat(cart.obtainPointsLeftToPoint(pointList, "x", 1.5), equalTo(expectedPointList));
    }

    @Test
    void obtain_values_left_to_point_at_y_axis(){
        List<Point> pointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2),
                new Point(2, 3));
        List<Point> expectedPointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2));
        assertThat(cart.obtainPointsLeftToPoint(pointList, "y", 2.5), equalTo(expectedPointList));
    }

    @Test
    void obtain_values_right_to_point_at_x_axis(){
        List<Point> pointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2),
                new Point(2, 3));
        List<Point> expectedPointList = Arrays.asList(
                new Point(2,3));
        assertThat(cart.obtainPointsRightToPoint(pointList, "x", 1.5), equalTo(expectedPointList));
    }

    @Test
    void obtain_values_right_to_point_at_y_axis(){
        List<Point> pointList = Arrays.asList(
                new Point(0,0),
                new Point(1, 2),
                new Point(2, 3));
        List<Point> expectedPointList = Arrays.asList(
                new Point(2, 3));
        assertThat(cart.obtainPointsRightToPoint(pointList, "y", 2.5), equalTo(expectedPointList));
    }
}
