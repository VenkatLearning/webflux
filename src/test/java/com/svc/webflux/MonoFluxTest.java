package com.svc.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFluxTest {

    @Test
    void monoFluxTest(){
       Mono<?> stringMono =  Mono.just("Mono")
               .then(Mono.error(new RuntimeException("Exception")))
               .log();
       stringMono.subscribe(System.out::println);
    }
    @Test
    void  fluxTest(){
       Flux<List<String>> listFlux = Flux.just(List.of("Flux1","Flux2")).log();
       listFlux.concatWith(listFlux).concatWith(Mono.error(new RuntimeException("Exception")));
       listFlux.subscribe(System.out::println,e-> System.out.println(e.getMessage()));
    }

    @Test
    void monoFluxTest1(){
        Mono<String> stringMono =  Mono.just("Mono1");
        stringMono.subscribe(e-> System.out.println(e));
    }
}
