package com.company.firstApplication.firstApplicaton.service;

import com.company.firstApplication.firstApplicaton.exception.CustomerAlreadyExistsException;
import com.company.firstApplication.firstApplicaton.exception.CustomerNotFoundException;
import com.company.firstApplication.firstApplicaton.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomersByCriteria(String firstName, String lastName, String state) throws CustomerNotFoundException;

    Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
}
