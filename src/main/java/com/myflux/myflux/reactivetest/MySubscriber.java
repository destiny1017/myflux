package com.myflux.myflux.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<Integer> {

    private Subscription s;
    private int amount = 3;
    private int bufferSize;

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        System.out.println("구독자 : 구독 정보 잘 받았습니다.");
        System.out.println("구독자 : 매일 신문 " + amount + "부씩 신청하겠습니다.");
        bufferSize = amount;
        s.request(amount);
    }

    @Override
    public void onNext(Integer n) {
        System.out.println("신문사 : 신문 " + n + "부 전달 완료...");
        if(--bufferSize == 0) {
            System.out.println("1 DAYS LATER..");
            bufferSize = amount;
            s.request(bufferSize);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("구독 중 에러");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료");
    }
}
