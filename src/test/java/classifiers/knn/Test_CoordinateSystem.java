package classifiers.knn;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_CoordinateSystem {

    private Knn knn;
    private Integer[][] training;

    @BeforeEach
    void init(){
        training = new Integer[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        knn = new Knn(training);
    }

    @Test
    void coordinates_top_left(){
        assertThat(knn.getPointValue(new Point(0,0)), is(1));}

    @Test
    void coordinates_bottom_left(){
        assertThat(knn.getPointValue(new Point(training.length-1,0)), is(7));}

    @Test
    void coordinates_top_right(){
        assertThat(knn.getPointValue(new Point(0,training[0].length-1)), is(3));}

    @Test
    void coordinates_bottom_right(){
        assertThat(knn.getPointValue(new Point(training.length-1,training[2].length-1)), is(9));}
}
