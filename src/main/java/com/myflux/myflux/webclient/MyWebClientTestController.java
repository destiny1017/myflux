package com.myflux.myflux.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyWebClientTestController {

    @GetMapping("/api-call1")
    public String callApi1() {
        process(5000);
        return "api1 response..";
    }

    @GetMapping("/api-call2")
    public String callApi2() {
        process(3000);
        return "api2 response..";
    }

    @GetMapping("/api-call3")
    public String callApi3() {
        process(2000);
        return "api3 response..";
    }

    public void process(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
