package io.example.asyncapi.provider.clinicaltool.modules.users.dtos

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * SearchCriteria.
 */
data class SearchCriteria(


    val username: String?  = null,


    val email: String?  = null,


    val role: String?  = null,


    val enabled: Boolean?  = null,


    val searchTerms: MutableMap<String,Object>?  = null

)  : Serializable {







}
