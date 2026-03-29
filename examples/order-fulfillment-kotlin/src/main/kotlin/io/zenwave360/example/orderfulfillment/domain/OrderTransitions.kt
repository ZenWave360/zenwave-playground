package io.zenwave360.example.orderfulfillment.domain
object OrderTransitions {

    fun ensureCanPayOrder(entity: Order) {
        require("payOrder", entity, OrderStatus.PLACED)
    }

    fun ensureCanShipOrder(entity: Order) {
        require("shipOrder", entity, OrderStatus.PAID)
    }

    fun ensureCanCancelOrder(entity: Order) {
        require("cancelOrder", entity, OrderStatus.PLACED, OrderStatus.PAID)
    }

    private fun require(command: String, entity: Order, vararg allowed: OrderStatus) {
        val current = entity.status
        if (current != null && allowed.any { it == current }) {
            return
        }
        throw InvalidStateTransitionException(
            entityType = entity::class.simpleName ?: "Order",
            entityId = entity.id,
            naturalId = "orderNumber=" + entity.orderNumber,
            operation = command,
            currentState = current,
            allowedStates = allowed.toList(),
        )
    }

    class InvalidStateTransitionException(
        val entityType: String,
        val entityId: Any?,
        val naturalId: Any?,
        val operation: String,
        val currentState: Any?,
        val allowedStates: List<*>,
    ) :
        IllegalStateException(
            "Invalid state transition for $entityType (${naturalId ?: "id=$entityId"}): " +
                "operation '$operation' not allowed from state $currentState, allowed states: $allowedStates"
        )
}
