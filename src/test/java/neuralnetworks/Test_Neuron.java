package neuralnetworks;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Test_Neuron {

    @Test
    void neuron_check_adder_output(){
        Neuron neuron = new Neuron(0.0, Arrays.asList(
                new NeuronInput(3.0, 0.5),
                new NeuronInput(1.0, 0.5)));
        assertThat(neuron.getInputAdderOutput(), is(2.0));
    }

    @Test
    void neuron_check_output_as_identity_function(){
        Neuron neuron = new Neuron(0.0, Arrays.asList(
                new NeuronInput(3.0, 0.5),
                new NeuronInput(1.0, 0.5)));
        assertThat(neuron.getInputAdderOutput(), is(2.0));
        assertThat(neuron.getInputAdderOutput(), is(neuron.getOutput()));
    }
}
