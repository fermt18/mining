package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_CoordinateSystem {

    private Knn knn;
    private List<Point> training;
    private Point p;

    @BeforeEach
    void init(){
        training = new ArrayList<>();
        p = new Point(0,0);
        p.setValue(1.0);
        training.add(p);
        p = new Point(3,4);
        p.setValue(7.3);
        training.add(p);
        knn = new Knn(training);
    }

    @Test
    void coordinates_zero(){
        assertThat(knn.getValueFromCoordinates(0,0), is(1.0));}

    @Test
    void coordinates_single_point(){
        assertThat(knn.getValueFromCoordinates(3,4), is(7.3));}

    @Test
    void coordinates_non_existing_point(){
        assertThat(knn.getValueFromCoordinates(10,10), is(nullValue()));}
}
