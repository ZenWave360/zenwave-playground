package io.zenwave360.example.adapters.web;

import io.zenwave360.example.core.domain.*;
import io.zenwave360.example.core.inbound.*;
import io.zenwave360.example.core.inbound.dtos.*;
import io.zenwave360.example.adapters.web.*;
import io.zenwave360.example.adapters.web.model.*;
import io.zenwave360.example.adapters.web.mappers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.math.*;
import java.time.*;
import java.util.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.NativeWebRequest;


/**
 * REST controller for CustomerApi.
 */
@RestController
@RequestMapping("/api")
public class CustomerApiController implements CustomerApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;


    private CustomerService customerService;

    @Autowired
    public CustomerApiController setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
        return this;
    }


    private CustomerDTOsMapper mapper = CustomerDTOsMapper.INSTANCE;

    public CustomerApiController(CustomerService customerService) {

        this.customerService = customerService;

    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO reqBody) {
        log.debug("REST request to createCustomer: {}", reqBody);
        var input = mapper.asCustomer(reqBody);
        var customer = customerService.createCustomer(input);
        CustomerDTO responseDTO = mapper.asCustomerDTO(customer);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomer(Long id) {
        log.debug("REST request to getCustomer: {}", id);
        var customer = customerService.getCustomer(id);
        if (customer.isPresent()) {
            CustomerDTO responseDTO = mapper.asCustomerDTO(customer.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(Long id, CustomerDTO reqBody) {
        log.debug("REST request to updateCustomer: {}, {}", id, reqBody);
        var input = mapper.asCustomer(reqBody);
        var customer = customerService.updateCustomer(id, input);
        if (customer.isPresent()) {
            CustomerDTO responseDTO = mapper.asCustomerDTO(customer.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
        log.debug("REST request to deleteCustomer: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<CustomerPaginatedDTO> searchCustomers(Integer page, Integer limit, List<String> sort, CustomerSearchCriteriaDTO reqBody) {
        log.debug("REST request to searchCustomers: {}, {}, {}, {}", page, limit, sort, reqBody);
        var input = mapper.asCustomerSearchCriteria(reqBody);
        var customerPage = customerService.searchCustomers(input, pageOf(page, limit, sort));
        var responseDTO = mapper.asCustomerPaginatedDTO(customerPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null ? Sort.by(sort.stream().map(sortParam -> {
            String[] parts = sortParam.split(":");
            String property = parts[0];
            Sort.Direction direction = parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
            return new Sort.Order(direction, property);
        }).toList()) : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
