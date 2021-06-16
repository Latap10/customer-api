package com.acc.training.customerapi.controller;

import javax.validation.Valid;

import com.acc.training.customerapi.api.CustomerApi;
import com.acc.training.customerapi.model.Customer;
import com.acc.training.customerapi.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${my.path}")
public class CustomerController implements CustomerApi {

    @Autowired
    private CustomerService service;
    
    @Override
    public ResponseEntity<Customer> createCustomer(@Valid Customer body) {

        Customer response = service.createCustomer(body);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Customer> getCustomer(String id) {

        Customer response = new Customer();
        
        if (id.length() >= 5 && id.length() <= 8) {
            response = service.getCustomer(id);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    
}
