package junit.providers;

import model.IndepVariable;
import model.Point;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainingForSplitProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context){
        List<SplitProvider> training = createTrainingList();
        return training.stream()
                .flatMap(p-> Stream.of(Arguments.of(p.getTraining(), p.getIndepVar(), p.getSplitVal())))
                .collect(Collectors.toList())
                .stream();
    }

    private List<SplitProvider> createTrainingList(){
        List<SplitProvider> trainingList = new ArrayList<>();
        List<Point> training;

        training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,0), 1.0));
        training.add(Utils.createPoint(new Point(0,2), 2.0));
        training.add(Utils.createPoint(new Point(1,2), 2.0));
        trainingList.add(new SplitProvider(training, IndepVariable.Y, 1.0));

        training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,1), 1.0));
        training.add(Utils.createPoint(new Point(2,1), 2.0));
        trainingList.add(new SplitProvider(training, IndepVariable.X, 1.0));

        training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,0), 1.0));
        training.add(Utils.createPoint(new Point(1,1), 1.0));
        training.add(Utils.createPoint(new Point(1,0), 2.0));
        training.add(Utils.createPoint(new Point(2,1), 2.0));
        trainingList.add(new SplitProvider(training, IndepVariable.X, 1.5));// X = 0.5 also valid

        training = new ArrayList<>();
        training.add(Utils.createPoint(new Point(0,0), 1.0));
        training.add(Utils.createPoint(new Point(1,1), 1.0));
        training.add(Utils.createPoint(new Point(2,2), 2.0));
        training.add(Utils.createPoint(new Point(3,3), 2.0));
        trainingList.add(new SplitProvider(training, IndepVariable.X, 1.5));// Y = 1.5 also valid

        return trainingList;
    }
}

class SplitProvider{

    private List<Point> training;
    private IndepVariable indepVar;
    private Double splitVal;

    List<Point> getTraining() {return training;}
    IndepVariable getIndepVar() {return indepVar;}
    Double getSplitVal() {return splitVal;}

    SplitProvider(List<Point> training, IndepVariable indepVar, Double splitVal){
        this.training = training;
        this.indepVar = indepVar;
        this.splitVal = splitVal;
    }
}
