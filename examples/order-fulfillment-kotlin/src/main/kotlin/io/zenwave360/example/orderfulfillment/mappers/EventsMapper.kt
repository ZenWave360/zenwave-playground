package io.zenwave360.example.orderfulfillment.mappers

import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface EventsMapper {

    companion object {
        val INSTANCE: EventsMapper = Mappers.getMapper(EventsMapper::class.java)
    }

    fun asOrderPaid(order: Order): io.zenwave360.example.orderfulfillment.events.dtos.OrderPaid

    fun asOrderStatus(orderStatus: OrderStatus): io.zenwave360.example.orderfulfillment.events.dtos.OrderStatus

    fun asOrderPlaced(order: Order): io.zenwave360.example.orderfulfillment.events.dtos.OrderPlaced

    fun asOrderShipped(order: Order): io.zenwave360.example.orderfulfillment.events.dtos.OrderShipped

    fun asOrderCancelled(order: Order): io.zenwave360.example.orderfulfillment.events.dtos.OrderCancelled

    fun asOrderItem(orderItem: OrderItem): io.zenwave360.example.orderfulfillment.events.dtos.OrderItem
}
