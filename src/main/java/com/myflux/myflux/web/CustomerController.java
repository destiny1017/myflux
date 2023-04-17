package com.myflux.myflux.web;

import com.myflux.myflux.domain.Customer;
import com.myflux.myflux.domain.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    private Sinks.Many<Customer> sink;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> fluxstream() {
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
    }


    // Flux는 리턴 데이터가 여러 건일 때 사용
    @GetMapping("/customer")
    public Flux<Customer> findAll() {
        return customerRepository.findAll().log();
    }

    // Mono는 리턴 데이터가 단건일 때 사용
    @GetMapping("/customer/{id}")
    public Mono<Customer> findById(@PathVariable Long id) {
        return customerRepository.findById(id).log();
    }

    @GetMapping("/customer/sse") // MediaType.TEXT_EVENT_STREAM_VALUE <- ServerSentEvent의 default타입이므로 생략 가능
    public Flux<ServerSentEvent<Customer>> findAllSse() {
        return sink.asFlux().map(customer -> ServerSentEvent.builder(customer).build())
                .doOnCancel(() -> sink.asFlux().blockLast()); // 요청이 끊겼을 경우 sink에 마지막 요청이라는 신호 전달
    }

    @PostMapping("/customer")
    public Mono<Customer> saveCustomer() {
        return customerRepository.save(new Customer("Kim", "Daeho"))
                .doOnNext(customer -> sink.tryEmitNext(customer)); // 데이터 추가될 경우 sink에 next데이터 추가
    }
}
