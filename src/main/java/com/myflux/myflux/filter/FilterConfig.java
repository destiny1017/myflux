package com.myflux.myflux.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public EventNotify eventNotify() {
        return new EventNotify();
    }

    @Bean
    public FilterRegistrationBean<Filter> addMyFilter1() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>(new MyFilter1(eventNotify()));
        filterBean.addUrlPatterns("/sse");
        return filterBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> addMyFilter2() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>(new MyFilter2(eventNotify()));
        filterBean.addUrlPatterns("/add");
        return filterBean;
    }
}
