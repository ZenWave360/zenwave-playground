package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.dtos.*
import io.zenwave360.example.orderfulfillment.web.*
import io.zenwave360.example.orderfulfillment.web.model.*
import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.mappers.*

import java.net.URI
import java.net.URISyntaxException
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest

/**
 * REST controller for OrderApi.
 */
@RestController
@RequestMapping("/api")
open class OrderApiController(
    private val orderService: OrderService
) : OrderApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = OrderDTOsMapper.INSTANCE



    override fun placeOrder(reqBody: PlaceOrderInputDTO): ResponseEntity<OrderDTO> {
        log.debug("REST request to placeOrder: {}", reqBody)
        val input = mapper.asPlaceOrderInput(reqBody)
        val order =  orderService.placeOrder(input)
        val responseDTO = mapper.asOrderDTO(order)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun payOrder(orderNumber: String, reqBody: PayOrderInputDTO): ResponseEntity<OrderDTO> {
        log.debug("REST request to payOrder: {}, {}", orderNumber, reqBody)
        val input = mapper.asPayOrderInput(reqBody)
        val order =  orderService.payOrder(orderNumber, input)
        val responseDTO = mapper.asOrderDTO(order)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun shipOrder(orderNumber: String, reqBody: ShipOrderInputDTO): ResponseEntity<OrderDTO> {
        log.debug("REST request to shipOrder: {}, {}", orderNumber, reqBody)
        val input = mapper.asShipOrderInput(reqBody)
        val order =  orderService.shipOrder(orderNumber, input)
        val responseDTO = mapper.asOrderDTO(order)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun cancelOrder(orderNumber: String): ResponseEntity<OrderDTO> {
        log.debug("REST request to cancelOrder: {}", orderNumber)
        val order =  orderService.cancelOrder(orderNumber)
        val responseDTO = mapper.asOrderDTO(order)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun getOrder(orderNumber: String): ResponseEntity<OrderDTO> {
        log.debug("REST request to getOrder: {}", orderNumber)
        val order =  orderService.getOrder(orderNumber)
        return if (order != null) {
            val responseDTO = mapper.asOrderDTO(order)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    protected fun pageOf(page: Int?, limit: Int?, sort: List<String>?): Pageable {
        val sortOrder = sort?.let {
            Sort.by(it.map { sortParam ->
                val parts = sortParam.split(":")
                val property = parts[0]
                val direction = if (parts.size > 1) Sort.Direction.fromString(parts[1]) else Sort.Direction.ASC
                Sort.Order(direction, property)
            })
        } ?: Sort.unsorted()
        return PageRequest.of(page ?: 0, limit ?: 10, sortOrder)
    }
}
