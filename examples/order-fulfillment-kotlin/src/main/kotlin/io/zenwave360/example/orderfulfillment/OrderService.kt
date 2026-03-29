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
     * Set Order as paid
     *
     * With Events: [OrderPaid].
     */
    fun payOrder(orderNumber: String, input: PayOrderInput): Order

    /**
     * Set Order as shipped
     *
     * With Events: [OrderShipped].
     */
    fun shipOrder(orderNumber: String, input: ShipOrderInput): Order

    /**
     * Set Order as cancelled
     *
     * With Events: [OrderCancelled].
     */
    fun cancelOrder(orderNumber: String): Order

    /** Query order */
    fun getOrder(orderNumber: String): Order?
}
