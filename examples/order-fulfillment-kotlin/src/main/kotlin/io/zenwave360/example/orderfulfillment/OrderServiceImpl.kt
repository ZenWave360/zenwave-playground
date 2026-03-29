package io.zenwave360.example.orderfulfillment

// import io.zenwave360.example.orderfulfillment.events.dtos.*
import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import io.zenwave360.example.orderfulfillment.events.*
import io.zenwave360.example.orderfulfillment.mappers.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/** Service Implementation for managing [Order]. */
@Service
@Transactional
open class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val eventsProducer: OrderEventsProducer,
) : OrderService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val orderServiceMapper: OrderServiceMapper = OrderServiceMapper.INSTANCE

    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE

    override fun placeOrder(input: PlaceOrderInput): Order {
        log.debug("Request placeOrder: {}", input)

        val order = orderServiceMapper.update(Order(), input)
            .apply {
                orderNumber = UUID.randomUUID().toString()
                status = OrderStatus.PLACED
                totalAmount = input.items.sumOf { it.unitPrice!! * BigDecimal.valueOf(it.quantity!!.toLong()) }
            }
            .let { orderRepository.save(it) }

        // emit events
        eventsProducer.onOrderPlaced(eventsMapper.asOrderPlaced(order))
        return order
    }

    override fun payOrder(orderNumber: String, input: PayOrderInput): Order {
        log.debug("Request payOrder: {} {}", orderNumber, input)

        return orderRepository
            .findByOrderNumber(orderNumber)
            ?.let { existingOrder ->
                OrderTransitions.ensureCanPayOrder(existingOrder)
                orderServiceMapper.update(existingOrder, input)
                existingOrder.status = OrderStatus.PAID
                existingOrder
            }
            ?.let { orderRepository.save(it) }
            ?.also {

                // emit events
                eventsProducer.onOrderPaid(eventsMapper.asOrderPaid(it))
            } ?: throw NoSuchElementException("Order not found with id: orderNumber=$orderNumber")
    }

    override fun shipOrder(orderNumber: String, input: ShipOrderInput): Order {
        log.debug("Request shipOrder: {} {}", orderNumber, input)

        return orderRepository
            .findByOrderNumber(orderNumber)
            ?.let { existingOrder ->
                OrderTransitions.ensureCanShipOrder(existingOrder)
                orderServiceMapper.update(existingOrder, input)
                existingOrder.status = OrderStatus.SHIPPED
                existingOrder
            }
            ?.let { orderRepository.save(it) }
            ?.also {

                // emit events
                eventsProducer.onOrderShipped(eventsMapper.asOrderShipped(it))
            } ?: throw NoSuchElementException("Order not found with id: orderNumber=$orderNumber")
    }

    override fun cancelOrder(orderNumber: String): Order {
        log.debug("Request cancelOrder: {}", orderNumber)

        val existingOrder =
            orderRepository.findByOrderNumber(orderNumber)
                ?: throw NoSuchElementException("Order not found with id: orderNumber=$orderNumber")
        OrderTransitions.ensureCanCancelOrder(existingOrder)
        existingOrder.status = OrderStatus.CANCELLED
        val order = orderRepository.save(existingOrder)

        // emit events
        eventsProducer.onOrderCancelled(eventsMapper.asOrderCancelled(order))
        return order
    }

    @Transactional(readOnly = true)
    override fun getOrder(orderNumber: String): Order? {
        log.debug("Request getOrder: {}", orderNumber)
        val order = orderRepository.findByOrderNumber(orderNumber)
        return order
    }
}
