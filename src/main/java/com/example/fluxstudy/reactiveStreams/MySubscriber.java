package com.example.fluxstudy.reactiveStreams;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<Integer> {

    private Subscription s;
    private int bufferSize = 3;

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("4. 구독자 : 구독정보 잘 받음");
        this.s = s;
        System.out.println("5. 구독자 : 지금 부터 매일 뉴스 3개씩 줘 ");
        s.request(bufferSize); // 뉴스 정보 3개씩 매일 줄 것을 요청 (백프레셔) 소비자가 한번에 처리할 수 있는 개수를 요청
    }

    // 2번 실행이 되기 때문에 별도 구분 로직이 추가되어야 함
    @Override
    public void onNext(Integer t) {
        System.out.println("받은 뉴스 :  "+t);

        bufferSize--;
        if (bufferSize == 0){
            System.out.println("하루 지남");
            bufferSize = 3;
            s.request(bufferSize);
        }

    }

    @Override
    public void onError(Throwable t) {
        System.out.println("구독 중 에러 ");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료");
    }
}
