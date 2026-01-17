package io.zenwave360.example.orderfulfillment.mappers

import io.zenwave360.example.orderfulfillment.mappers.*
import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import io.zenwave360.example.orderfulfillment.web.model.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface OrderDTOsMapper {

    companion object {
        val INSTANCE: OrderDTOsMapper = Mappers.getMapper(OrderDTOsMapper::class.java)
    }

    // request mappings
    fun asPlaceOrderInput(dto: PlaceOrderInputDTO): PlaceOrderInput
    fun asShipOrderInput(dto: ShipOrderInputDTO): ShipOrderInput
    fun asPayOrderInput(dto: PayOrderInputDTO): PayOrderInput

    // response mappings
    
    fun asOrderDTO(entity: Order): OrderDTO
    

}
