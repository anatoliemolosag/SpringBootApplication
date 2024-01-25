package com.anatolieCode;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }



    public void addCustomer(String name, String email, Integer age){
        Customer customer = new Customer();


            customer.setName(name);
            log.info(name + " Attempting to save name");
            customer.setEmail(email);
            log.info(email + " Attempting to save email");
            customer.setAge(age);
            log.info(age + " Attempting to save age");
            customerRepository.save(customer);


    }


//    public void updateCustomer(Integer id, String name, String email, Integer age){
//
//       Optional<Customer> customer = customerRepository.findById(id);
//
//        if(customerRepository.findById(id).isPresent()) {
//            customer.setName(name);
//            customer.setEmail(email);
//            customer.setAge(age);
//
//            customer.
//        }else{
//
//        }
//    }

    public void updateCustomer(Integer id, String name, String email, Integer age) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Resource not found"));

        // Update the existing resource with the new data
        customer.setName(name);
        log.info(name + " attempting to update customer name");
        customer.setEmail(email);
        log.info(email + " attempting to update customer email");
        customer.setAge(age);
        log.info(age + " attempting to update customer age");

        // Save the updated resource
        customerRepository.save(customer);
    }



}
