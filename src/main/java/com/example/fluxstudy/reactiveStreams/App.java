package com.example.fluxstudy.reactiveStreams;


// WebFlux는 단일 스레드와 비동기를 지원, Stream을 통해 백프레셔가 적용된 데이터 만큼 지속적인 응답이 가능하고
// 데이터 소비가 끝나면 응답이 종료
// SSE를 적용하면 데이터 소비가 끝나도 Stream을 유지한다.
public class App {
    public static void main(String[] args){
        MyPublisher myPublisher = new MyPublisher(); // 신문사 생성
        MySubscriber mySubscriber = new MySubscriber(); // 구독자 생성

        myPublisher.subscribe(mySubscriber); // 구독자가 발행자에게 구독 요청
    }
}
