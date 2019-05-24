package datafetchers;

import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_DataFetcher {
    private List<Point> trainingSet;

    @BeforeEach
    void init() throws Throwable {
        String csvFile = "datafetchers/knn_data_double_coordinates.csv";
        CSVFetcher csvFetcher = new CSVFetcher(csvFile);
        trainingSet = csvFetcher.getTrainingSet();
    }

    @Test
    void get_data_from_csv_assert_coordinates() {
        List<Point> expected_trainingSet = new ArrayList<>();
        expected_trainingSet.add(new Point(85.5, 16.8));
        expected_trainingSet.add(new Point(64.8, 21));
        expected_trainingSet.add(new Point(87,   23.6));
        expected_trainingSet.add(new Point(4, 5));
        assertThat(trainingSet, equalTo(expected_trainingSet));
    }
}

class Test_DataFetcher_integer_coordinates {
    private List<Point> trainingSet;

    @BeforeEach
    void init() throws Throwable {
        String csvFile = "datafetchers/knn_data_integer_coordinates.csv";
        CSVFetcher csvFetcher = new CSVFetcher(csvFile);
        trainingSet = csvFetcher.getTrainingSet();
    }

    @Test
    void get_data_from_csv_assert_coordinates() {
        List<Point> expected_trainingSet = new ArrayList<>();
        expected_trainingSet.add(createPoint(4, 9, 1.0));
        expected_trainingSet.add(createPoint(6, 8, 2.0));
        assertThat(trainingSet, equalTo(expected_trainingSet));
    }

    @Test
    void get_data_from_csv_assert_values() {
        assertThat(trainingSet.get(0).getValue(), is(1.0));
        assertThat(trainingSet.get(1).getValue(), is(2.0));
    }

    private Point createPoint(Integer x, Integer y, Double value){
        Point p = new Point(x, y);
        p.setValue(value);
        return p;
    }
}
