package com.anatolieCode.service;


import com.anatolieCode.exception.BadRequestException;
import com.anatolieCode.exception.ConflictException;
import com.anatolieCode.model.Customer;
import com.anatolieCode.repository.jpa.CustomerRepository;
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

    public ResponseEntity addCustomer(String name, String email, Integer age){
        Customer customer = new Customer();

         Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);

            if(customerOptional.isPresent()){
                throw new ConflictException("Oops cannot create a user, " + email + " already exist ");
            }

            if (!(name == null) && !(email == null) && !(age == null)) {
                log.info(String.valueOf(customerOptional.isPresent()));
                customer.setName(name);
                log.info(name + " Attempting to save name");
                customer.setEmail(email);
                log.info(email + " Attempting to save email");
                customer.setAge(age);
                log.info(age + " Attempting to save age");
                customerRepository.save(customer);
                return new ResponseEntity<>(customer, HttpStatus.CREATED);
            }
                throw new BadRequestException("Invalid request body, null values not accepted, please provide a valid name, email, age");
    }

    public ResponseEntity updateCustomer(Integer id, String name, String email, Integer age) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Resource not found"));

            if (!(name == null) && !(email == null) && !(age == null)) {
                if (!(customer.getEmail().equals(email))) {
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
                throw new ConflictException("Invalid request body " + email + " already exists");
            }
                throw new BadRequestException("Invalid request body, null values not accepted, please provide a valid name, email, age");
    }


}
