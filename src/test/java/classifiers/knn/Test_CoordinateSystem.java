package classifiers.knn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_CoordinateSystem {

    private Knn knn;
    private Integer[][] training;

    @BeforeEach
    void init(){
        training = new Integer[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        knn = new Knn(training);
    }

    @Test
    void coordinates_top_left(){
        assertThat(knn.getPointValue(new Point(0,0)), is(1));}

    @Test
    void coordinates_bottom_left(){
        assertThat(knn.getPointValue(new Point(training.length-1,0)), is(7));}

    @Test
    void coordinates_top_right(){
        assertThat(knn.getPointValue(new Point(0,training[0].length-1)), is(3));}

    @Test
    void coordinates_bottom_right(){
        assertThat(knn.getPointValue(new Point(training.length-1,training[2].length-1)), is(9));}

    @Test
    void distance_zero_points_away(){
        assertThat(knn.getPointDistance(new Point(2, 2), new Point(2, 2)), is(0.0));
    }

    @Test
    void distance_one_point_away() {
        assertThat(knn.getPointDistance(new Point(0, 0), new Point(1, 1)), is(Math.sqrt(2)));
        assertThat(knn.getPointDistance(new Point(1, 1), new Point(2, 2)), is(Math.sqrt(2)));
        assertThat(knn.getPointDistance(new Point(0,1), new Point(0,2)), is(1.0));
    }

    @Test
    void distance_two_points_away(){
        assertThat(knn.getPointDistance(new Point(0,0), new Point(2,2)), is(Math.sqrt(8)));
    }
}
