package model;

import classifiers.knn.Knn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_TS_Classes {

    private TrainingSet trainingSet;

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
        trainingSet = new TrainingSet(training);
    }

    @Test
    void get_class_a_from_training_set(){
        assertThat(trainingSet.getClassA(), is(0.0));
    }

    @Test
    void get_class_b_from_training_set(){
        assertThat(trainingSet.getClassB(), is(1.0));
    }

    private Point createPoint(int x, int y, Double value){
        Point p = new Point(x, y);
        p.setValue(value);
        return p;
    }
}
