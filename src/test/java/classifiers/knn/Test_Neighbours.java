package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class Test_Neighbours {

    private List<Point> expected;
    private Knn knn;

    @BeforeEach
    void init(){
        List<Point> training = new ArrayList<>();
        training.add(createNewPoint(new Point(0,8), 1.0));
        training.add(createNewPoint(new Point(0,7), 1.0));
        training.add(createNewPoint(new Point(0,6), 1.0));
        knn = new Knn(training);
        expected = new ArrayList<>();
    }

    @Test
    void neighbours_of_middle_point_with_k_equal_to_1() {
        expected.add(createNewPoint(new Point(0, 7), 0.0));
        assertThat(knn.computeNN(1, new Point(1, 7)), equalTo(expected));
    }

    @Test
    void neighbours_of_top_right_point_with_k_equal_to_1() {
        assertThat(knn.computeNN(1, new Point(8,8)), equalTo(expected));
    }

    @Test
    void neighbours_of_bottom_left_with_k_equal_to_1(){
        assertThat(knn.computeNN(1, new Point(0,0)), equalTo(expected));
    }

    @Test
    void neighbours_of_bottom_left_with_k_equal_higher_than_1(){
        expected.add(new Point(0,6));
        assertThat(knn.computeNN(6, new Point(0,0)), equalTo(expected));
    }

    @Test
    void neighbours_of_non_existing_point(){
        assertThat(knn.computeNN(1, new Point(10,9)), equalTo(expected));
    }

    @Test
    void validation_point_belongs_to_training_set_integer_coordinates(){
        Point p = createNewPoint(new Point(10, 10), 1.0);
        expected = Collections.singletonList(p);
        knn = new Knn(expected);
        assertThat(knn.computeNN(1, p), equalTo(expected));
    }

    @Test
    void validation_point_belongs_to_training_set_double_coordinates(){
        Point p = createNewPoint(new Point(10.1, 10.2), 1.0);
        expected = Collections.singletonList(p);
        knn = new Knn(expected);
        assertThat(knn.computeNN(1, p), equalTo(expected));
    }


    private Point createNewPoint(Point p, Double value){
        p.setValue(value);
        return p;
    }
}
