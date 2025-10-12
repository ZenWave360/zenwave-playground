package io.example.asyncapi.provider.service;

import io.example.asyncapi.provider.domain.*;
import io.example.asyncapi.provider.service.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [Customer].
 */
public interface CustomerService {

    /**
     *
     *
     */
    public Customer createCustomer(Customer input);
    /**
     *
     *
     */
    public Optional<Customer> getCustomer(Long id);
    /**
     *
     *
     */
    public Optional<Customer> updateCustomer(Long id, Customer input);
    /**
     *
     *
     */
    public void deleteCustomer(Long id);
    /**
     *
     *
     */
    public Page<Customer> listCustomers(Pageable pageable);
}
