package neuralnetworks;

class NeuronInput {

    private Double value;
    private Double weight;

    public Double getValue() {return this.value;}
    public Double getWeight() {return this.weight;}

    NeuronInput(Double value, Double weight){
        this.value = value;
        this.weight = weight;
    }
}
