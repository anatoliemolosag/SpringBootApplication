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
    CustomExceptions customExceptions;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }



    public ResponseEntity addCustomer(String name, String email, Integer age){
        Customer customer = new Customer();
         customExceptions = new CustomExceptions(500,"name, " + ", email" + ", or age must not contain null value",
                "Please fill required fields for registration -- " + "name " + name + " email " + email + " age " + age);



            if (!(name == null) && !(email == null) && !(age == null)) {

                customer.setName(name);
                log.info(name + " Attempting to save name");
                customer.setEmail(email);
                log.info(email + " Attempting to save email");
                customer.setAge(age);
                log.info(age + " Attempting to save age");
                customerRepository.save(customer);
                log.info("empty line");
                return new ResponseEntity<>(customer, HttpStatus.CREATED);

            }else {
                return new ResponseEntity<>(customExceptions, HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }





    public ResponseEntity updateCustomer(Integer id, String name, String email, Integer age) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Resource not found"));

        customExceptions = new CustomExceptions(500,"name, " + ", email" + ", or age must not contain null value",
                "Please fill required fields for registration -- " + "name " + name + " email " + email + " age " + age);


            if (!(name == null) && !(email == null) && !(age == null)) {

                // Update the existing resource with the new data
                customer.setName(name);
                log.info(name + " attempting to update customer name");
                customer.setEmail(email);
                log.info(email + " attempting to update customer email");
                customer.setAge(age);
                log.info(age + " attempting to update customer age");

                // Save the updated resource
                customerRepository.save(customer);

                return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);



            }
            else {
                return new ResponseEntity<>(customExceptions, HttpStatus.INTERNAL_SERVER_ERROR);
            }





    }



}
