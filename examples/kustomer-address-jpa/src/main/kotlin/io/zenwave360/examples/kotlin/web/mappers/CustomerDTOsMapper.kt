package io.zenwave360.examples.kotlin.web.mappers

import io.zenwave360.examples.kotlin.web.mappers.*
import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.dtos.*
import io.zenwave360.examples.kotlin.web.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface CustomerDTOsMapper {

    companion object {
        val INSTANCE: CustomerDTOsMapper = Mappers.getMapper(CustomerDTOsMapper::class.java)
    }

    // request mappings
    fun asCustomerSearchCriteria(dto: CustomerSearchCriteriaDTO): CustomerSearchCriteria
    fun asCustomer(dto: CustomerDTO): Customer

    // response mappings

    fun asCustomerDTOList(entityList: List<Customer>): List<CustomerDTO>
    @Mapping(target = "content", source = "content", conditionExpression = "java(page.getContent() != null)")
    fun asCustomerPaginatedDTO(page: Page<Customer>): CustomerPaginatedDTO
    fun asCustomerDTOPage(page: Page<Customer>): Page<CustomerDTO> {
        return page.map { this.asCustomerDTO(it) }
    }

    fun asCustomerDTO(entity: Customer): CustomerDTO


}
