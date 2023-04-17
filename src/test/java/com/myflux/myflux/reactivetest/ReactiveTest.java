package com.myflux.myflux.reactivetest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ReactiveTest {

    @Test
    void reactiveTest() {
        MyPublisher myPublisher = new MyPublisher();
        MySubscriber mySubscriber = new MySubscriber();
        myPublisher.subscribe(mySubscriber);
    }
}
