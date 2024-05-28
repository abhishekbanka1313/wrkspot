package com.company.firstApplication.firstApplicaton.service;

import com.company.firstApplication.firstApplicaton.dao.CustomerRepository;
import com.company.firstApplication.firstApplicaton.exception.CustomerAlreadyExistsException;
import com.company.firstApplication.firstApplicaton.exception.CustomerNotFoundException;
import com.company.firstApplication.firstApplicaton.model.Customer;
import com.company.firstApplication.firstApplicaton.util.CustomerSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomersByCriteria(String firstName, String lastName, String state) throws CustomerNotFoundException {
        log.info("Searching customer by firstName: {}, lastName: {}, state: {}", firstName, lastName, state);
        Specification<Customer> spec = where(CustomerSpecifications.hasFirstName(firstName))
                .and(CustomerSpecifications.hasLastName(lastName))
                .and(CustomerSpecifications.hasState(state));

        List<Customer> customers = customerRepository.findAll(spec);
        if (customers.isEmpty()) {
            log.error("No customers are found given the search criteria firstName: {}, lastName: {}, state: {}", firstName, lastName, state);
            throw new CustomerNotFoundException("No customers found matching the criteria");
        }
        return customers;
    }

    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        log.info("Adding customer with following details: {}", customer);
        if (customerRepository.existsByCustomerId(customer.getCustomerId())) {
            log.error("Customer with Id: {} already exists", customer.getCustomerId());
            throw new CustomerAlreadyExistsException("Customer with ID " + customer.getCustomerId() + " already exists");
        }
        return customerRepository.save(customer);
    }
}
