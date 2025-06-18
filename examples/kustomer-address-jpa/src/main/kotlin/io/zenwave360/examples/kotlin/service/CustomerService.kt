package io.zenwave360.examples.kotlin.service

import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [Customer].
 */
interface CustomerService {


         /**
      * 
      *
      * With Events: [CustomerEvent].

      */

     fun createCustomer(input: Customer): Customer


         /**
      * 
      *
      */

     fun getCustomer(id: Long): Optional<Customer>


         /**
      * 
      *
      * With Events: [CustomerEvent].

      */

     fun updateCustomer(id: Long, input: Customer): Optional<Customer>


         /**
      * 
      *
      * With Events: [CustomerEvent].

      */

     fun deleteCustomer(id: Long): Unit


         /**
      * 
      *
      */

     fun searchCustomers(input: CustomerSearchCriteria, pageable: Pageable): Page<Customer>



}
