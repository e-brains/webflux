package com.example.fluxstudy.reactiveStreams;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Arrays;

public class MyPublisher implements Publisher<Integer> {

    // 발행자가 들고 있는 정보
    Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    @Override
    public void subscribe(Subscriber s) {
        System.out.println("1. 구독자 : 너희  발행자의 뉴스 좀 볼께");
        System.out.println("2. 발행자 : 구독정보를 만들어서 알려 줄테니 기다려");
        MySubscription mySubscription = new MySubscription(s, its);
        System.out.println("3. 발행자 : 구독정보 생성 완료되었음 !!!");
        s.onSubscribe(mySubscription);
    }
}
