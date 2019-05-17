package datafetchers;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_DataFetcher {

    @Test
    void get_data_from_csv() throws Throwable {
        String csvFile = "datafetchers/knn_data_below_one.csv";

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
        Integer[][] training_actual = csvFetcher.getDataSet();
        assertThat(training_actual, is(training_expected));
    }
}
