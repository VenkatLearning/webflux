package com.svc.webflux.dao;

import com.svc.webflux.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {
    public static void sleepSec(int i) {
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.range(1, 10).peek(i -> System.out.println("process " + i))
                .peek(CustomerDao::sleepSec)
                .mapToObj(i -> new Customer(i, "Customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("process " + i))
                .map(i -> new Customer(i, "Customer" + i));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1,50)
                .doOnNext(i-> System.out.println("process..."+i))
                .map(i-> new Customer(i,"Customer "+i));
    }
}
