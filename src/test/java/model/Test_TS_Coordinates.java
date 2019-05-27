package model;

import classifiers.knn.Knn;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_TS_Coordinates {

    private TrainingSet trainingSet;

    @BeforeEach
    void init(){
        List<Point> training = new ArrayList<>();
        Point p;
        p = new Point(0,0);
        p.setValue(1.0);
        training.add(p);
        p = new Point(3,4);
        p.setValue(1.0);
        training.add(p);
        p = new Point(1,6.9);
        p.setValue(80.0);
        training.add(p);
        trainingSet = new TrainingSet(training);
    }

    @Test
    void coordinates_zero(){
        assertThat(trainingSet.getValueFromCoordinates(0.0,0.0), is(1.0));}

    @Test
    void coordinates_single_point_integer_coordinates(){
        assertThat(trainingSet.getValueFromCoordinates(3.0,4.0), is(1.0));}

    @Test
    void coordinates_single_point_double_coordinates(){
        assertThat(trainingSet.getValueFromCoordinates(1.0,6.9), is(80.0));}


    @Test
    void coordinates_non_existing_point(){
        assertThat(trainingSet.getValueFromCoordinates(10.0,10.0), is(nullValue()));}
}
