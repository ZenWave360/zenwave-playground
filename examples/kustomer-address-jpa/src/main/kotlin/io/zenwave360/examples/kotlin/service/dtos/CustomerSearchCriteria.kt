package io.zenwave360.examples.kotlin.service.dtos

import io.zenwave360.examples.kotlin.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * CustomerSearchCriteria.
 */
data class CustomerSearchCriteria(

    
    val name: String?  = null,

    
    val email: String?  = null,

    
    val city: String?  = null,

    
    val state: String?  = null

)  : Serializable {






}
