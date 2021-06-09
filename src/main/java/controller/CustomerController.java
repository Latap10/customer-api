package controller;

import javax.validation.Valid;

import com.acc.training.customerapi.api.CustomerApi;
import com.acc.training.customerapi.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import service.CustomerService;


@RestController
public class CustomerController implements CustomerApi{

    @Autowired
    CustomerService service;

    @Override
    public ResponseEntity<Customer> createCustomer(@Valid Customer body) {

        Customer response = service.createCustomer(body);
       
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Customer> getCustomer(String id) {

        // ToDo validate customerid min length 5 and max length 8
        Customer response = service.getCustomer(id);

        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
