package integration;

import classifiers.knn.Knn;
import datafetchers.CSVFetcher;
import model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IT_UseCase_CSV {

    private List<Point> validationSet;

    @BeforeEach
    void init(){
        validationSet = new ArrayList<>();
        validationSet.add(createNewPoint(new Point(110.1, 19.2), 1.0));
        validationSet.add(createNewPoint(new Point(108, 17.6), 1.0));
        validationSet.add(createNewPoint(new Point(81, 20), 1.0));
        validationSet.add(createNewPoint(new Point(52.8, 20.8), 2.0));
        validationSet.add(createNewPoint(new Point(59.4, 16), 2.0));
        validationSet.add(createNewPoint(new Point(66, 18.4), 2.0));
    }

    @Test
    void use_case_csv_data() throws Throwable {
        String csvFile = "datafetchers/knn_data.csv";
        CSVFetcher csvFetcher = new CSVFetcher(csvFile);
        List<Point> trainingSet = csvFetcher.getTrainingSet();
        Knn knn = new Knn(trainingSet);
        for(Point p : validationSet)
            System.out.println(knn.classify(1, p));
    }

    private Point createNewPoint(Point p, Double value){
        p.setValue(value);
        return p;
    }
}
