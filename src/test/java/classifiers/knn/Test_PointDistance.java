package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_PointDistance {

    private Knn knn;

    @BeforeEach
    void init(){
        knn = new Knn();
    }

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
