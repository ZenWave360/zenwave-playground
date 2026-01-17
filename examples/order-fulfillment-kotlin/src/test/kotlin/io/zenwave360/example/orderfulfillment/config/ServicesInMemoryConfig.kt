package io.zenwave360.example.orderfulfillment.config

import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.events.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {

    protected val eventsProducerInMemoryContext = EventsProducerInMemoryContext()

    protected val orderService =
        OrderServiceImpl(orderRepository(), eventsProducerInMemoryContext.orderEventsProducer())

    @Bean
    open fun <T : OrderService> orderService(): T {
        return orderService as T
    }

    companion object {
        var _orders: List<Order>? = null
    }

    fun reloadTestData() {

        val testDataLoader = TestDataLoader(listOf(Order::class.java))
        val orders = _orders ?: testDataLoader.loadCollectionTestDataAsObjects(Order::class.java)
        orderRepository().deleteAll()
        orderRepository().saveAll(orders)
    }
    // fun getEventsProducerInMemoryContext(): EventsProducerInMemoryContext {
    //     return eventsProducerInMemoryContext
    // }
}
