package io.zenwave360.examples.kotlin.service.impl

import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.*
import io.zenwave360.examples.kotlin.service.dtos.*
import io.zenwave360.examples.kotlin.service.impl.mappers.*
import io.zenwave360.examples.kotlin.repository.jpa.*
// import io.zenwave360.examples.kotlin.events.dtos.*
import io.zenwave360.examples.kotlin.events.*

import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

/**
 * Service Implementation for managing [Customer].
 */
@Service
@Transactional(readOnly = true)
open class CustomerServiceImpl(

    private val customerRepository: CustomerRepository


    , private val eventsProducer: CustomerEventsProducer

) : CustomerService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val customerServiceMapper: CustomerServiceMapper = CustomerServiceMapper.INSTANCE


    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE



    @Transactional

    override  fun createCustomer(input: Customer): Customer
 {
    log.debug("[CRUD] Request to save Customer: {}", input)
    var customer = customerServiceMapper.update(Customer(), input)
    customer = customerRepository.save(customer)
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    
        // emit events
            val customerEvent = eventsMapper.asCustomerEvent(customer)
            eventsProducer.onCustomerEvent(customerEvent)
    return customer


}


    override  fun getCustomer(id: Long): Optional<Customer>
 {
    log.debug("[CRUD] Request to get Customer : {}", id)
    val customer = customerRepository.findById(id)
    return customer


}

    @Transactional

    override  fun updateCustomer(id: Long, input: Customer): Optional<Customer>
 {
    log.debug("Request updateCustomer: {} {}", id, input);

    val customer = customerRepository.findById(id).map { existingCustomer ->
        customerServiceMapper.update(existingCustomer, input)
    }
    .map { customerRepository.save(it) }
     if(customer.isPresent()) {
        // emit events
            val customerEvent = eventsMapper.asCustomerEvent(customer.get())
            eventsProducer.onCustomerEvent(customerEvent) }
    return customer


}

    @Transactional

    override  fun deleteCustomer(id: Long): Unit
 {
    log.debug("[CRUD] Request to delete Customer : {}", id)
    customerRepository.deleteById(id)
    
        // emit events
            val customerEvent = eventsMapper.asCustomerEvent(id)
            eventsProducer.onCustomerEvent(customerEvent)


}


    override  fun searchCustomers(input: CustomerSearchCriteria, pageable: Pageable): Page<Customer>
 {
    log.debug("Request searchCustomers: {} {}", input, pageable);

    val customers = customerRepository.findAll(pageable)
    return customers


}



}
