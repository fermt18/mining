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

    List<Point> training;
    Point p;
    @BeforeEach
    void init(){
        /*new Integer[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1}});*/
        training = new ArrayList<>();
        p = new Point(6, 0);
        p.setValue(1.0);
        training.add(p);
        p = new Point(7, 0);
        p.setValue(1.0);
        training.add(p);
        p = new Point(8, 0);
        p.setValue(1.0);
        training.add(p);
        p = new Point(9, 0);
        p.setValue(1.0);
        training.add(p);

        p = new Point(7, 1);
        p.setValue(1.0);
        training.add(p);
        p = new Point(8, 1);
        p.setValue(1.0);
        training.add(p);
        p = new Point(9, 1);
        p.setValue(1.0);
        training.add(p);

        p = new Point(5, 2);
        p.setValue(1.0);
        training.add(p);
        p = new Point(8, 2);
        p.setValue(1.0);
        training.add(p);
        p = new Point(9, 2);
        p.setValue(1.0);
        training.add(p);

        p = new Point(5, 3);
        p.setValue(1.0);
        training.add(p);
        p = new Point(9, 3);
        p.setValue(1.0);
        training.add(p);

        p = new Point(6, 4);
        p.setValue(1.0);
        training.add(p);
        p = new Point(7, 4);
        p.setValue(1.0);
        training.add(p);
        knn = new Knn(training);
    }

    @Test
    void classify_single_point_surrounder_by_class_a(){
        assertThat(knn.classify(1, new Point(0,9)), is(0));
    }

    @Test
    void classify_single_point_surrounded_by_class_b(){
        assertThat(knn.classify(1, new Point(9,0)), is(1));
    }

    @Test
    void classify_single_point_surrounded_by_class_b_with_high_k(){
        assertThat(knn.classify(6, new Point(9,0)), is(0));
    }
}
