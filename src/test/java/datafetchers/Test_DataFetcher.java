package datafetchers;

import model.Point;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_DataFetcher {

    @Test
    void get_data_from_csv() throws Throwable {
        String csvFile = "datafetchers/knn_data_below_one.csv";
        //Point[][] training_expected = new Point[6][9];
        //training_expected[6][8] = new Poinr()
        Integer[][] training_expected = new Integer[][]{
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1, 1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1, 2,-1}
        };
        CSVFetcher csvFetcher = new CSVFetcher(csvFile);
        Point[][] training_actual = csvFetcher.getDataSet();
        assertThat(training_actual, is(training_expected));
    }

    @Test
    void convert_double_to_int(){
        Double d = 2.3;
        Double d_by_10 = d *10;
        Integer i = d_by_10.intValue();
        assertThat(23, is(i));
    }
}
