package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


class Test_kNN {
/*
    private Integer[][] training_1d = new Integer[][]{{0, 1, 2, 3, 4, 5, 6, 7}};
    private Integer[][] training_2d = new Integer[][]{
            {1, 2, 3},
            {4, 5, 6}};

    @Test
    void one_dimensional_array_1nn_middle_point() {
        Knn knn = new Knn(training_1d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(0, 3));
        assertThat(knn.getKNNValues(1, new Point(0, 2)), equalTo(expected));
    }

    @Test
    void one_dimensional_array_1nn_next_to_left_point() {
        Knn knn = new Knn(training_1d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        assertThat(knn.getKNNValues(1, new Point(0,1)), equalTo(expected));
    }

    @Test
    void two_dimensional_array_1nn_middle_point(){
        Knn knn = new Knn(training_2d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,1));
        assertThat(knn.getKNNValues(1, new Point(0,1)), equalTo(expected));
    }

    @Test
    void two_dimensional_array_1nn_top_right_point(){
        Knn knn = new Knn(training_2d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,2));
        assertThat(knn.getKNNValues(1, new Point(0,2)), equalTo(expected));
    }

    @Test
    void two_dimensional_array_2nn_top_right_point(){
        Knn knn = new Knn(training_2d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,1));
        expected.add(new Point(1,2));
        assertThat(knn.getKNNValues(2, new Point(0,2)), equalTo(expected));
    }

    @Test
    void two_dimensional_array_3nn_top_right_point(){
        Knn knn = new Knn(training_2d);
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0,0));
        expected.add(new Point(0,1));
        expected.add(new Point(0,2));
        expected.add(new Point(1,0));
        expected.add(new Point(1,1));
        expected.add(new Point(1,2));
        assertThat(knn.getKNNValues(3, new Point(0,2)), equalTo(expected));
    }*/
}
