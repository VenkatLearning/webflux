package com.svc.webflux.handler;

import com.svc.webflux.dao.CustomerDao;
import com.svc.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomersStream(ServerRequest serverRequest){
       Flux<Customer>  customerFlux = customerDao.getCustomersStream();
       return ServerResponse.ok()
               .contentType(MediaType.TEXT_EVENT_STREAM)
               .body(customerFlux,Customer.class);
    }
}
