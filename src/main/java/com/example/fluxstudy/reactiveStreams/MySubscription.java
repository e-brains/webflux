package com.example.fluxstudy.reactiveStreams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독 정보 ( 구독자 정보, 어떤 데이터를 구독할지 정보 두 가지를 들고 있어야 함)
public class MySubscription implements Subscription {

    private Subscriber s;
    private Iterator<Integer> it;

    public MySubscription(Subscriber s, Iterable<Integer> its){
        this.s = s;
        this.it = its.iterator();

    }

    @Override
    public void request(long n) {
        // 뉴스 요청 갯수 만큼 응답
        while (n > 0){
            if (it.hasNext()){
                s.onNext(it.next());

            }else {
                s.onComplete(); // 응답할 데이터가 더 이상 없으면 완료 처리
                break;
            }

            n--;
        }
    }

    @Override
    public void cancel() {

    }
}
