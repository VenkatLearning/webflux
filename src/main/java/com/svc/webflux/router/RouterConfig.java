package com.svc.webflux.router;

import com.svc.webflux.handler.CustomerHandler;
import com.svc.webflux.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    CustomerHandler customerHandler;

    @Autowired
    CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> loadCustomers() {
        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::loadCustomers)
                .GET("/router/customers/stream", customerStreamHandler::loadCustomersStream)
                .GET("/router/customer/{input}",customerHandler::findCustomer)
                .POST("/router/customer/save",customerHandler::saveCustomer)
                .build();
    }
}
