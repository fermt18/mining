package datafetchers;

import model.Point;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFetcher {

    private URI csvPath;

    CSVFetcher(String csvPath) throws URISyntaxException {
        this.csvPath = ClassLoader.getSystemResource(csvPath).toURI();
    }

    List<Point> getTrainingSet() throws IOException {
        List<String> lines = Files.lines(Paths.get(csvPath)).collect(Collectors.toList());
        List<Point> points = new ArrayList<>();
        for(String line : lines.subList(1, lines.size())) {
            Integer xCoordinate = Integer.valueOf(line.split(",")[0]);
            Integer yCoordinate = Integer.valueOf(line.split(",")[1]);
            Double value = Double.valueOf(line.split(",")[2]);
            Point p = new Point(xCoordinate, yCoordinate);
            p.setValue(value);
            points.add(p);
        }
        return points;
    }
}
