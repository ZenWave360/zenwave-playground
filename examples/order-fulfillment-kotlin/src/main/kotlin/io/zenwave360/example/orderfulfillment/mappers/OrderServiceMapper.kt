package io.zenwave360.example.orderfulfillment.mappers

import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface OrderServiceMapper {

    companion object {
        val INSTANCE: OrderServiceMapper = Mappers.getMapper(OrderServiceMapper::class.java)
    }

    // input mappings
    // PayOrderInputnull-Order payOrder

    fun asOrder(input: PayOrderInput): Order

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Order, input: PayOrderInput): Order

    // ShipOrderInputnull-Order shipOrder

    fun asOrder(input: ShipOrderInput): Order

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Order, input: ShipOrderInput): Order

    // PlaceOrderInputnull-Order placeOrder

    fun asOrder(input: PlaceOrderInput): Order

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Order, input: PlaceOrderInput): Order
    // output mappings
}
