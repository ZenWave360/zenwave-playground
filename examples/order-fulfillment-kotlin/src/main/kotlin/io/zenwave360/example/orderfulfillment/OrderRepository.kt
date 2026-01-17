package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.domain.*
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/** Spring Data JPA repository for the Order entity. */
@Suppress("unused")
@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findByOrderNumber(orderNumber: String): Order?
}
