package integration;

import classifiers.knn.Knn;
import datafetchers.CSVFetcher;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
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
        // point values are only added for test validation purposes
        validationSet.add(createNewPoint(new Point(110.1, 19.2), 1.0));
        validationSet.add(createNewPoint(new Point(108, 17.6), 1.0));
        validationSet.add(createNewPoint(new Point(81, 20), 1.0));
        validationSet.add(createNewPoint(new Point(52.8, 20.8), 2.0));
        validationSet.add(createNewPoint(new Point(59.4, 16), 2.0));
        validationSet.add(createNewPoint(new Point(66, 18.4), 2.0));
    }

    @ParameterizedTest
    @CsvSource({"1,0.33", "3,0.33", "5,0.33", "7,0.33", "9,0.33", "11,0.17", "13,0.17", "18,0.5"})
    void test_all_ks(Integer k, Double expectedError){
        int wrongClassifications = 0;
        Knn knn = new Knn(trainingSet);
        for(Point p : validationSet) {
            Double classification = knn.classify(k, p);
            if (!classification.equals(p.getValue()))
                wrongClassifications++;
        }
        Double error = roundTwoDecimals((double) wrongClassifications / validationSet.size());
        assertThat(error, is(expectedError));
    }

    private Point createNewPoint(Point p, Double value){
        p.setValue(value);
        return p;
    }

    private Double roundTwoDecimals(Double value){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
