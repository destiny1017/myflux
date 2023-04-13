package com.myflux.myflux.filter;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@AllArgsConstructor
public class MyFilter1 implements Filter {

    private final EventNotify eventNotify;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("필터1 실행됨");

        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setContentType("text/event-stream; charset=utf-8;");

        PrintWriter writer = servletResponse.getWriter();
        for (int i = 0; i < 5; i++) {
             writer.write("response " + i + "...\n");
             writer.flush();
             Thread.sleep(1000);
        }

        while(true) {
            if(eventNotify.getChange()) {
                writer.write("response " + eventNotify.getEvents().poll() + "!\n");
                writer.flush();
                eventNotify.setChange();
            }
            Thread.sleep(10);
        }
    }
}
