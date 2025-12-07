package io.zenwave360.examples.kotlin.repository.jpa.inmemory

import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.repository.jpa.CustomerRepository
import org.apache.commons.lang3.ObjectUtils
import java.math.*
import java.time.*
import java.util.*

class CustomerRepositoryInMemory : InMemoryJpaRepository<Customer, Long>(), CustomerRepository {
    private var nextId: Long = 0
    private val primaryKeyGenerator: PrimaryKeyGenerator<Long?> = PrimaryKeyGenerator { nextId++ }

    override fun <S : Customer> save(entity: S): S {
        super.save(entity)
        entity.paymentMethods.forEach({ paymentMethod ->
            paymentMethod.id = ObjectUtils.firstNonNull(
                paymentMethod.id,
                primaryKeyGenerator.next()
            )
        })
        return entity
    }
}
