package datagenerators;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class RandomGen extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Randomization Demo");
        final ScatterChart<Number, Number> chart = plotRandom();
        VBox.setVgrow(chart, Priority.ALWAYS);
        final VBox pane = new VBox(new Label("Random Chart"), chart);

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ScatterChart<Number, Number> plotRandom(){
        final NumberAxis xAxis = new NumberAxis(0, 100, 10);
        final NumberAxis yAxis = new NumberAxis(0, 100, 25);
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);
        final Random random = new Random();

        final XYChart.Series randomSeries = new XYChart.Series();
        randomSeries.setName("Random Int");
        populate(randomSeries, ()->random.nextInt(100));

        final int gaussianStdDev = 5;
        final int gaussianMean = 50;

        final XYChart.Series gaussianSeries = new XYChart.Series();
        gaussianSeries.setName("Gaussian Int, std dev " + gaussianStdDev + " near " + gaussianMean);
        populate(gaussianSeries, ()->(int)random.nextGaussian() * gaussianStdDev + gaussianMean);

        sc.getData().addAll(randomSeries, gaussianSeries);
        return sc;
    }

    private void populate(XYChart.Series series, IFNumGen ifNumGen){
        for(int i=0; i<100; i++)
            series.getData().add(new XYChart.Data<Number,Number>(i, ifNumGen.generate()));
    }
}
