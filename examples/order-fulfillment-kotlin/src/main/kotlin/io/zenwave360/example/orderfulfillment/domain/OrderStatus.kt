package io.zenwave360.example.orderfulfillment.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

/** Enum for OrderStatus. */
enum class OrderStatus(val value: Int) {

    DRAFT(1),
    PLACED(2),
    PAID(3),
    SHIPPED(4),
    CANCELLED(5);

    companion object {
        fun fromValue(value: Int): OrderStatus? {
            return values().find { it.value == value }
        }
    }

    @Converter
    class OrderStatusConverter : AttributeConverter<OrderStatus, Int> {
        override fun convertToDatabaseColumn(attribute: OrderStatus?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): OrderStatus? {
            return if (dbData == null) null else OrderStatus.fromValue(dbData)
        }
    }
}
