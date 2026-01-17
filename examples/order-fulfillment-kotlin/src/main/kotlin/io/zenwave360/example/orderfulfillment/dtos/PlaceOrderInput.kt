package io.zenwave360.example.orderfulfillment.dtos

import io.zenwave360.example.orderfulfillment.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** PlaceOrderInput. */
data class PlaceOrderInput(
    @Size(min = 1) val items: MutableList<OrderItem> = mutableListOf(),
    @NotNull @Size(max = 3) val currency: String,
) : Serializable {

    fun addItems(items: OrderItem): PlaceOrderInput {
        this.items += items
        return this
    }
}
