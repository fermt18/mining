package neuralnetworks;

import java.util.List;

class Neuron {

    private List<NeuronInput> inputList;
    private Double bias;
    private Double inputAdderOutput;
    private Double output;

    Double getInputAdderOutput() {return inputAdderOutput;}
    Double getOutput(){return this.output;}

    Neuron(Double bias, List<NeuronInput> inputList){
        this.inputList = inputList;
        this.bias = bias;
        computeInputAdderOutput();
        computeOutput();
    }

    private void computeInputAdderOutput(){
        inputAdderOutput = bias;
        for(NeuronInput input : inputList){
            inputAdderOutput += input.getValue() * input.getWeight();
        }
    }

    private void computeOutput(){
        // identity function -> g(v) = v
        output = inputAdderOutput;
    }
}
