package com.myflux.myflux.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

public class MySubscription implements Subscription {

    private Subscriber s;
    private Iterator<Integer> it;

    public MySubscription(Subscriber s, Iterable<Integer> its) {
        this.s = s;
        this.it = its.iterator();
    }

    @Override
    public void request(long n) {
        while (0 < n--) {
            if(it.hasNext()) {
                s.onNext(it.next());
            } else {
                s.onComplete();
                break;
            }
        }
    }

    @Override
    public void cancel() {

    }
}
