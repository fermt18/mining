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
        training.add(createPoint(6, 0));
        training.add(createPoint(7, 0));
        training.add(createPoint(8, 0));
        training.add(createPoint(9, 0));
        training.add(createPoint(7, 1));
        training.add(createPoint(8, 1));
        training.add(createPoint(9, 1));
        training.add(createPoint(5, 2));
        training.add(createPoint(8, 2));
        training.add(createPoint(9, 2));
        training.add(createPoint(5, 3));
        training.add(createPoint(9, 3));
        training.add(createPoint(6, 4));
        training.add(createPoint(7, 4));
        knn = new Knn(training);
    }

    private Point createPoint(Integer x, Integer y){
        Point p = new Point(x, y);
        p.setValue(1.0);
        return p;
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
