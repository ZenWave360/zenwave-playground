package io.zenwave360.examples.kotlin.service.impl.mappers

import io.zenwave360.examples.kotlin.service.impl.mappers.BaseMapper
import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers


@Mapper(uses = [BaseMapper::class])
interface EventsMapper {

    companion object {
        val INSTANCE: EventsMapper = Mappers.getMapper(EventsMapper::class.java)
    }
    fun asAddress(address: Address): io.zenwave360.examples.kotlin.events.dtos.Address
    fun asPaymentMethod(paymentMethod: PaymentMethod): io.zenwave360.examples.kotlin.events.dtos.PaymentMethod
    fun asCustomerEvent(customer: Customer): io.zenwave360.examples.kotlin.events.dtos.CustomerEvent
    fun asCustomerEvent(id: Long): io.zenwave360.examples.kotlin.events.dtos.CustomerEvent {
        return io.zenwave360.examples.kotlin.events.dtos.CustomerEvent().withId(id)
    }
}
