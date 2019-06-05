package integration;

import classifiers.cart.CART;
import datafetchers.CSVFetcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IT_CART_from_CSV {

    @Test
    void test_cart() throws Throwable {
        CSVFetcher csvFetcher = new CSVFetcher("datafetchers/cart_data.csv");
        CART cart = new CART(csvFetcher.getTrainingSet());
        cart.nextSplit();
        //assertThat(cart.getSplitVariable(), is(IndepVariable.Y));
        assertThat(cart.getSplitValue(), is(19.0));
    }
}
