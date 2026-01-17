package io.zenwave360.example.orderfulfillment.inmemory

import io.zenwave360.example.orderfulfillment.OrderRepository
import io.zenwave360.example.orderfulfillment.domain.*
import java.math.*
import java.time.*
import java.util.*

class OrderRepositoryInMemory : InMemoryJpaRepository<Order, Long>(), OrderRepository {
    override fun findByOrderNumber(orderNumber: String): Order? {
        return getEntities().values.firstOrNull { e -> isSameValue(orderNumber, readField(e, "orderNumber")) }
    }
}
