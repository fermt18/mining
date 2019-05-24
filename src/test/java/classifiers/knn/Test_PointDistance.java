package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_PointDistance {

    private Knn knn;

    @BeforeEach
    void init(){
        knn = new Knn(new ArrayList<>());
    }

    @Test
    void distance_00_to_11() {
        assertThat(knn.computePointDistance(new Point(0, 0), new Point(1, 1)), is(Math.sqrt(2)));
    }

    @Test
    void distance_11_to_22() {
        assertThat(knn.computePointDistance(new Point(1, 1), new Point(2, 2)), is(Math.sqrt(2)));
    }

    @Test
    void distance_01_to_02() {
        assertThat(knn.computePointDistance(new Point(0,1), new Point(0,2)), is(1.0));
    }

    @Test
    void distance_two_points_away(){
        assertThat(knn.computePointDistance(new Point(0,0), new Point(2,2)), is(Math.sqrt(8)));
    }

    @Test
    void distance_22_to_22(){
        assertThat(knn.computePointDistance(new Point(2, 2), new Point(2, 2)), is(0.0));
    }
}
