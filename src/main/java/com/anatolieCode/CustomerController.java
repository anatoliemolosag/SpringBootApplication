package com.anatolieCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();

    }

    record newCustomerRequest(String name,
                              String email,
                              Integer age){

    }

    @PostMapping()
    public ResponseEntity<String> createCustomer(@RequestBody newCustomerRequest request){

        if (request.name == null || request.email == null || request.age == null) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(request + " cannot have a null value");

        }else {
            customerService.addCustomer(request.name, request.email, request.age);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User has been created successfully");
        }


    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }


    @PutMapping("{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody newCustomerRequest request){
        try {
            if (request.name == null || request.email == null || request.age == null) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(request + " cannot have a null value");

            }
                customerService.updateCustomer(id, request.name, request.email, request.age);

            return ResponseEntity.ok("Resource updated successfully");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }




}
