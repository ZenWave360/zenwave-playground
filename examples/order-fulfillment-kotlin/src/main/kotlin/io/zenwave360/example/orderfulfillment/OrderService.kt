package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import java.math.*
import java.time.*

/** Inbound Service Port for managing [Order]. */
interface OrderService {

    /**
     * Customer places an order
     *
     * With Events: [OrderPlaced].
     */
    fun placeOrder(input: PlaceOrderInput): Order

    /**
     * Order is shipped
     *
     * With Events: [OrderPaid].
     */
    fun payOrder(orderNumber: String, input: PayOrderInput): Order

    /**
     * Order is cancelled
     *
     * With Events: [OrderShipped].
     */
    fun shipOrder(orderNumber: String, input: ShipOrderInput): Order

    /**
     * Query order
     *
     * <ul><li><b>shipedOrdersCanNotBeCancelled</b>: Shipped orders can not be cancelled</li></ul> With Events:
     * [OrderCancelled].
     */
    fun cancelOrder(orderNumber: String): Order

    /**  */
    fun getOrder(orderNumber: String): Order?
}
