package io.zenwave360.example.orderfulfillment.dtos

import io.zenwave360.example.orderfulfillment.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** PayOrderInput. */
data class PayOrderInput(@NotNull val paymentReference: String) : Serializable {}
