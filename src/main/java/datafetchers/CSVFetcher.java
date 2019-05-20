package datafetchers;

import model.Point;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVFetcher {

    private URI csvPath;

    CSVFetcher(String csvPath) throws URISyntaxException {
        this.csvPath = ClassLoader.getSystemResource(csvPath).toURI();
    }

    Point[][] getDataSet() throws IOException {
        Double maxX = 0.0;
        Double maxY = 0.0;
        List<String> lines = Files.lines(Paths.get(csvPath)).collect(Collectors.toList());
        List<Point> points = new ArrayList<>();
        for(String line : lines.subList(1, lines.size())) {
            Double xCoordinate = Double.valueOf(new DecimalFormat("##.##").format(line.split(",")[0]));
            if( xCoordinate > maxX )
                maxX = xCoordinate;

            Double yCoordinate = Double.valueOf(new DecimalFormat("##.##").format(line.split(",")[0]));
            if( yCoordinate > maxY )
                maxY = yCoordinate;
            Double value = Double.valueOf(new DecimalFormat("##.##").format(line.split(",")[0]));
            Point p = new Point(((Double)(xCoordinate*10)).intValue(), ((Double)(yCoordinate*10)).intValue());
            p.setValue(value);
            points.add(p);
        }
        Point[][] dataSet = createAndFillArray(
                (int)Math.round(maxX), (int)Math.round(maxY),
                points);
        return dataSet;
    }

    Point[][] createAndFillArray(Integer maxX, Integer maxY, List<Point> points){
        Point[][] array = new Point[maxX][maxY];
        IntStream.range(0, maxX).forEach(i-> {
            IntStream.range(0, maxY).forEach(j->{
                array[i][j] = null;
            });
        });
        return array;
    }
}
