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

    //TODO move logic to the service class
    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();

    }

     record newCustomerRequest(String name,
                              String email,
                              Integer age){

    }



    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody newCustomerRequest request){

            return customerService.addCustomer(request.name, request.email, request.age);

    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }


    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody newCustomerRequest request){

        return  customerService.updateCustomer(id, request.name, request.email, request.age);

    }




}
