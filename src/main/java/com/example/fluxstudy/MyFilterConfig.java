package com.example.fluxstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class MyFilterConfig {

    @Autowired
    private EventNotify eventNotify;

    // SSE 프로토콜은  스프링 MVC에서도 사용가능하며 멀티 스레드로 동작
    // SSE가 웹 플럭스에서 동작할때는 비동기 단일 스레드로 동작

    @Bean
    public FilterRegistrationBean<Filter> addFilter(){

        System.out.println("필터1 등록됨");
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter(eventNotify));
        bean.addUrlPatterns("/sse");
        return bean;

    }

    @Bean
    public FilterRegistrationBean<Filter> addFilter2(){

        System.out.println("필터2 등록됨");
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new MyFilter2(eventNotify));
        bean.addUrlPatterns("/add");
        return bean;

    }
}
