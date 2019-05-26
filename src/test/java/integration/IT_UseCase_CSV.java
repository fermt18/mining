package integration;

import classifiers.knn.Knn;
import datafetchers.CSVFetcher;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class IT_UseCase_CSV {

    private List<Point> validationSet;
    private List<Point> trainingSet;

    @BeforeEach
    void init() throws Throwable {
        String csvFile = "datafetchers/knn_data.csv";
        CSVFetcher csvFetcher = new CSVFetcher(csvFile);
        trainingSet = csvFetcher.getTrainingSet();
        validationSet = new ArrayList<>();
        validationSet.add(createNewPoint(new Point(110.1, 19.2), 1.0));
        validationSet.add(createNewPoint(new Point(108, 17.6), 1.0));
        validationSet.add(createNewPoint(new Point(81, 20), 1.0));
        validationSet.add(createNewPoint(new Point(52.8, 20.8), 2.0));
        validationSet.add(createNewPoint(new Point(59.4, 16), 2.0));
        validationSet.add(createNewPoint(new Point(66, 18.4), 2.0));
    }

    @Test
    void test_different_ks(){
        //IntStream.range(0, 15).forEach(this::use_case_csv_data_k_1);
        use_case_csv_data_k_1(3);
    }

    void use_case_csv_data_k_1(int k) {
        Integer wrongClassifications = 0;
        Knn knn = new Knn(trainingSet);
        for(Point p : validationSet) {
            if (!knn.classify(k, p).equals(p.getValue()))
                wrongClassifications++;
        }
        System.out.println("k=" + k + ", error=" + Double.valueOf(wrongClassifications) / validationSet.size());
    }

    private Point createNewPoint(Point p, Double value){
        p.setValue(value);
        return p;
    }
}
