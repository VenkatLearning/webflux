package com.svc.webflux.handler;


import com.svc.webflux.dao.CustomerDao;
import com.svc.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){
        Flux<Customer> customersList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customersList,Customer.class);

    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest) {
        Integer id = Integer.valueOf(serverRequest.pathVariable("input"));
     //   customerDao.getCustomersList().take(1).single();
       Mono<Customer> customerMono =  customerDao.getCustomersList().filter(item->item.getId()==id).next();
       return ServerResponse.ok().body(customerMono,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
       Mono<String> saveResponse = customerMono.map(dto->dto.getId()+"<->"+dto.getName());
       return ServerResponse.ok().body(saveResponse,String.class);
    }
}
