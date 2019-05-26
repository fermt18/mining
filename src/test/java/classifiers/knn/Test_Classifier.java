package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class Test_Classifier {

    private Knn knn;

    @BeforeEach
    void init(){
        List<Point> training = new ArrayList<>();
        training.add(createPoint(6, 1, 0.0));
        training.add(createPoint(7, 1, 0.0));
        training.add(createPoint(8, 2, 0.0));
        training.add(createPoint(9, 2, 0.0));
        training.add(createPoint(1, 6, 1.0));
        training.add(createPoint(1, 7, 1.0));
        training.add(createPoint(2, 8, 1.0));
        training.add(createPoint(2, 9, 1.0));
        knn = new Knn(training);
    }

    @Test
    void get_class_a_from_training_set(){
        assertThat(knn.getClassA(), is(0.0));
    }

    @Test
    void get_class_b_from_training_set(){
        assertThat(knn.getClassB(), is(1.0));
    }

    @Test
    void classify_single_point_existing_in_training_set_surrounded_by_class_a(){
        assertThat(knn.classify(1, new Point(2,9)), is(1.0));
    }

    @Test
    void classify_single_point_existing_in_training_set_surrounded_by_class_b(){
        assertThat(knn.classify(1, new Point(9,2)), is(0.0));
    }

    @Test
    void classify_single_point_non_existing_in_training_set_surrounded_by_class_a(){
        assertThat(knn.classify(1, new Point(1,9)), is(1.0));
    }

    @Test
    void classify_single_point_non_existing_in_training_set_surrounded_by_class_b_with_high_k(){
        assertThat(knn.classify(6, new Point(9,0)), is(0.0));
    }

    @Test
    void classify_single_point_non_existing_in_training_set_surrounded_by_class_b(){
        assertThat(knn.classify(1, new Point(9,1)), is(0.0));
    }

    @Test
    void classify_single_point_without_any_nearest_neighbour(){
        assertThat(knn.classify(1, new Point(5,5)), is(nullValue()));
    }

    @Test
    void classify_single_point_different_classes(){
        List<Point> training = new ArrayList<>();
        training.add(createPoint(1, 0, 2.0));
        training.add(createPoint(0, 5, 3.5));
        knn = new Knn(training);
        assertThat(knn.classify(1, new Point(1,0)), is(2.0));
    }

    private Point createPoint(Integer x, Integer y, Double value){
        Point p = new Point(x, y);
        p.setValue(value);
        return p;
    }
}
