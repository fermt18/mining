package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class Test_Neighbours {

    private List<Point> expected;
    private Knn knn;

    @BeforeEach
    void init(){
        List<Point> training = new ArrayList<>();
        training.add(new Point(0,8));
        knn = new Knn(training);
        expected = new ArrayList<>();
    }

    @Test
    void neighbours_of_middle_point_with_k_equal_to_1() {
        expected.add(new Point(3, 5));
        expected.add(new Point(4, 4));
        expected.add(new Point(4, 5));
        expected.add(new Point(4, 6));
        expected.add(new Point(5, 5));
        assertThat(knn.computeNN(1, new Point(4, 5)), equalTo(expected));
    }

    @Test
    void neighbours_of_top_right_point_with_k_equal_to_1() {
        expected.add(new Point(7,8));
        expected.add(new Point(8,7));
        expected.add(new Point(8,8));
        assertThat(knn.computeNN(1, new Point(8,8)), equalTo(expected));
    }

    @Test
    void neighbours_of_bottom_left_with_k_equal_to_1(){
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(1,0));
        assertThat(knn.computeNN(1, new Point(0,0)), equalTo(expected));
    }

    @Test
    void neighbours_of_bottom_left_with_k_equal_to_2(){
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,0));
        expected.add(new Point(1,1));
        expected.add(new Point(2,0));
        assertThat(knn.computeNN(2, new Point(0,0)), equalTo(expected));
    }

    @Test
    void neighbours_of_non_existing_point(){
        assertThat(knn.computeNN(1, new Point(0,9)), equalTo(expected));
    }
}
