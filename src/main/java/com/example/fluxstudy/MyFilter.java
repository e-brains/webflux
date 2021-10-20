package com.example.fluxstudy;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter implements Filter {

    private EventNotify eventNotify;

    public MyFilter(EventNotify eventNotify){
        this.eventNotify = eventNotify;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터 실행됨");

        //  ServletResponse response를 HttpServletResponse로 다운 캐스팅
        HttpServletResponse servletResponse = (HttpServletResponse)  response;

        // 한글 깨짐 방지
        servletResponse.setContentType("text/event-stream; charset=utf-8");
        // text/plain은 전송이 마지막에 한번 된다.
        // text/event-stream이라고 하면 스트림을 열어 놓고 매번 전송한다.

        PrintWriter out = servletResponse.getWriter();

        for (int i=0 ; i<5; i++) {
            out.println("응답 == " + i);
            out.flush(); // 버퍼를 비운다.

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (true){
            if (eventNotify.getChange()){
                int lastIndex = eventNotify.getEvents().size()-1;
                out.println("응답 == " + eventNotify.getEvents().get(lastIndex));
                out.flush(); // 버퍼를 비운다.
                eventNotify.setChange(false);
            }
            Thread.sleep(1);
        }


    }
}
