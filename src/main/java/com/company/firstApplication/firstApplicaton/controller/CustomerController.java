package com.company.firstApplication.firstApplicaton.controller;

import com.company.firstApplication.firstApplicaton.exception.CustomerNotFoundException;
import com.company.firstApplication.firstApplicaton.model.Customer;
import com.company.firstApplication.firstApplicaton.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String state) {
        List<Customer> customers = customerService.getCustomersByCriteria(firstName, lastName, state);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    private void filterCustomers(List<Customer> listA, List<Customer> listB) {
        List<Customer> customersOnlyInListA = listA.stream()
                .filter(customer -> !listB.contains(customer))
                .collect(Collectors.toList());
        List<Customer> customersOnlyInListB = listB.stream()
                .filter(customer -> !listA.contains(customer))
                .collect(Collectors.toList());

        List<Customer> customersInBothLists = listA.stream()
                .filter(listB::contains)
                .collect(Collectors.toList());
    }
}
