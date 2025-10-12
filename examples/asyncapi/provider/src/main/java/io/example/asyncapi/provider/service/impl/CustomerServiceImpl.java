package io.example.asyncapi.provider.service.impl;

import io.example.asyncapi.provider.domain.*;
import io.example.asyncapi.provider.repository.jpa.*;
import io.example.asyncapi.provider.service.*;
import io.example.asyncapi.provider.service.dtos.*;
import io.example.asyncapi.provider.service.impl.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [Customer].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CustomerServiceMapper customerServiceMapper = CustomerServiceMapper.INSTANCE;

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer input) {
        log.debug("[CRUD] Request to save Customer: {}", input);
        var customer = customerServiceMapper.update(new Customer(), input);
        customer = customerRepository.save(customer);
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return customer;
    }

    public Optional<Customer> getCustomer(Long id) {
        log.debug("[CRUD] Request to get Customer : {}", id);
        var customer = customerRepository.findById(id);
        return customer;
    }

    @Transactional
    public Optional<Customer> updateCustomer(Long id, Customer input) {
        log.debug("Request updateCustomer: {} {}", id, input);

        var customer = customerRepository
                .findById(id)
                .map(existingCustomer -> {
                    return customerServiceMapper.update(existingCustomer, input);
                })
                .map(customerRepository::save);
        return customer;
    }

    @Transactional
    public void deleteCustomer(Long id) {
        log.debug("[CRUD] Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }

    public Page<Customer> listCustomers(Pageable pageable) {
        log.debug("Request listCustomers: {}", pageable);

        var customers = customerRepository.findAll(pageable);
        return customers;
    }
}
