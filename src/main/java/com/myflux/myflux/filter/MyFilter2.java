package com.myflux.myflux.filter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.*;
import java.io.IOException;

@AllArgsConstructor
public class MyFilter2 implements Filter {

    private final EventNotify eventNotify;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("필터2 실행됨");
        eventNotify.add("NEW DATA");
    }
}
