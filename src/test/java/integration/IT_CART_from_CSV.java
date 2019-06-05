package integration;

import classifiers.cart.CART;
import datafetchers.CSVFetcher;
import model.IndepVariable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IT_CART_from_CSV {

    private CART cart;

    @BeforeEach
    void init() throws Throwable {
        CSVFetcher csvFetcher = new CSVFetcher("datafetchers/cart_data.csv");
        cart = new CART(csvFetcher.getTrainingSet());
    }

    @Test
    void test_cart_one_split() {
        // According to
        // https://ocw.mit.edu/courses/sloan-school-of-management/15-062-data-mining-spring-2003/lecture-notes/L3ClassTrees.pdf
        // first split is Y=19.0, being X=84.75 the second one. However, impurity reduction is higher with X=84.75
        cart.nextSplit();
        assertThat(cart.getSplitLineList().get(1).getSplitVariable(), is(IndepVariable.X));
        assertThat(cart.getSplitLineList().get(1).getSplitValue(), is(84.75));
    }
/*
    @Test
    void test_cart_two_splits(){
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(IndepVariable.X));
        assertThat(cart.getSplitValue(), is(84.75));
        cart.nextSplit();
        assertThat(cart.getSplitVariable(), is(IndepVariable.Y));
        assertThat(cart.getSplitValue(), is(19.0));
    }*/
}
