package com.myflux.myflux.reactivetest;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPublisher implements Publisher<Integer> {

    Iterable<Integer> its = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        System.out.println("구독자 : 신문 구독 신청좀 할게요");
        System.out.println("신문사 : 네 구독 정보를 생성해드리겠습니다. 잠시만 기다려주세요.");
        MySubscription subscription = new MySubscription(s, its);
        System.out.println("신문사 : 구독 정보 생성을 완료했습니다.");
        s.onSubscribe(subscription);
    }
}
