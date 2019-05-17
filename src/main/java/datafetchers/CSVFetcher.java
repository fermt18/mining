package datafetchers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVFetcher {

    private URI csvPath;

    CSVFetcher(String csvPath) throws URISyntaxException {
        this.csvPath = ClassLoader.getSystemResource(csvPath).toURI();
    }

    Integer[][] getDataSet() throws IOException {
        Double maxX = 0.0;
        Double maxY = 0.0;
        List<String> lines = Files.lines(Paths.get(csvPath)).collect(Collectors.toList());
        for(String line : lines.subList(1, lines.size())) {
            Double xCoordinate = Double.valueOf(line.split(",")[0]);
            if( xCoordinate > maxX )
                maxX = xCoordinate;

            Double yCoordinate = Double.valueOf(line.split(",")[1]);
            if( yCoordinate > maxY )
                maxY = yCoordinate;
            Double value = Double.valueOf(line.split(",")[2]);
        }
        Integer[][] dataSet = createAndFillArray(
                (int)Math.round(maxX), (int)Math.round(maxY),
                -1);
        return dataSet;
    }

    Integer[][] createAndFillArray(Integer maxX, Integer maxY, Integer deaultValue){
        Integer[][] array = new Integer[maxX][maxY];
        IntStream.range(0, maxX).forEach(i-> {
            IntStream.range(0, maxY).forEach(j->{
                array[i][j] = deaultValue;
            });
        });
        return array;
    }
}
