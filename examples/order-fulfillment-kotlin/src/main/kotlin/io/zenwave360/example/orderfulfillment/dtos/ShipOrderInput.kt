package io.zenwave360.example.orderfulfillment.dtos

import io.zenwave360.example.orderfulfillment.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** ShipOrderInput. */
data class ShipOrderInput(@NotNull val trackingNumber: String) : Serializable {}
