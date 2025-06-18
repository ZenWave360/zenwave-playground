package io.zenwave360.examples.kotlin.repository.jpa

import io.zenwave360.examples.kotlin.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the Customer entity.
 */
@Suppress("unused")
@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
}
