package com.svc.webflux.service;

import com.svc.webflux.dao.CustomerDao;
import com.svc.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getCustomers(){
        long start = System.currentTimeMillis();
        List<Customer>  customerList = customerDao.getCustomers();
        long endTime = System.currentTimeMillis();
        System.out.println("Total ... "+(endTime-start));
        return customerList;
    }

    public Flux<Customer> getCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer>  customerList = customerDao.getCustomersStream();
        long endTime = System.currentTimeMillis();
        System.out.println("Total ... "+(endTime-start));
        return customerList;
    }
}
