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
            ?.let { existingOrder -> orderServiceMapper.update(existingOrder, input) }
            ?.apply { status = OrderStatus.PAID }
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
            ?.let { existingOrder -> orderServiceMapper.update(existingOrder, input) }
            ?.apply { status = OrderStatus.SHIPPED }
            ?.let { orderRepository.save(it) }
            ?.also {

                // emit events
                eventsProducer.onOrderShipped(eventsMapper.asOrderShipped(it))
            } ?: throw NoSuchElementException("Order not found with id: orderNumber=$orderNumber")
    }

    override fun cancelOrder(orderNumber: String): Order {
        log.debug("Request cancelOrder: {}", orderNumber)

        return orderRepository.findByOrderNumber(orderNumber)
            ?.also {
                if (it.status == OrderStatus.SHIPPED) {
                    throw IllegalStateException("Shipped orders can not be cancelled")
                }
            }
            ?.apply { status = OrderStatus.CANCELLED }
            ?.let { orderRepository.save(it) }
            ?.also {
                // emit events
                eventsProducer.onOrderCancelled(eventsMapper.asOrderCancelled(it))
            }
            ?: throw NoSuchElementException("Order not found with id: orderNumber=$orderNumber")
    }

    @Transactional(readOnly = true)
    override fun getOrder(orderNumber: String): Order? {
        log.debug("Request getOrder: {}", orderNumber)
        val order = orderRepository.findByOrderNumber(orderNumber)
        return order
    }
}
