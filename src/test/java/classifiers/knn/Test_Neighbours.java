package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


class Test_Neighbours {

    List<Point> training;
    @BeforeEach
    void init(){
        training = new ArrayList<>();
        Point p;
        for(Integer i=0; i<8; i++){
            p = new Point(0,i);
            training.add(p);
        }
        p = new Point(1,1);
        training.add(p);
    }

    @Test
    void neighbours_1d_of_middle_point_with_k_equal_to_1() {
        Knn knn = new Knn(training);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 4));
        expected.add(new Point(0, 5));
        expected.add(new Point(0, 6));
        assertThat(knn.computeNN(1, new Point(0, 5)), equalTo(expected));
    }

    @Test
    void neighbours_1d_of_next_to_top_right_point_with_k_equal_to_1() {
        Knn knn = new Knn(training);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,5));
        expected.add(new Point(0,6));
        expected.add(new Point(0,7));
        assertThat(knn.computeNN(1, new Point(0,6)), equalTo(expected));
    }

    @Test
    void neighbours_2d_of_middle_point_with_k_equal_to_1(){
        Knn knn = new Knn(training);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,1));
        assertThat(knn.computeNN(1, new Point(0,1)), equalTo(expected));
    }
}
