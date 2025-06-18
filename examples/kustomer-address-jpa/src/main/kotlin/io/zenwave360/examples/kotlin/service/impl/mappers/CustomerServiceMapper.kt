package io.zenwave360.examples.kotlin.service.impl.mappers

import io.zenwave360.examples.kotlin.service.impl.mappers.BaseMapper
import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface CustomerServiceMapper {

    companion object {
        val INSTANCE: CustomerServiceMapper = Mappers.getMapper(CustomerServiceMapper::class.java)
    }

// input mappings
    // CustomerSearchCriterianull-Customer searchCustomers
    fun asCustomer(input: CustomerSearchCriteria): Customer
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Customer, input: CustomerSearchCriteria): Customer
    // Customer-Customer updateCustomer
    fun asCustomer(input: Customer): Customer
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Customer, input: Customer): Customer
// output mappings
    @AfterMapping
    fun manageRelationships(@MappingTarget entity: Customer) {
        entity.paymentMethods?.forEach { paymentMethods ->
            paymentMethods.customer = entity
        }
    }
}
