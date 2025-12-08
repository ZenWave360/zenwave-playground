package io.zenwave360.example.clinicaltool.modules.users.dtos

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** SearchCriteria. */
data class SearchCriteria(
    val username: String? = null,
    val email: String? = null,
    val role: String? = null,
    val enabled: Boolean? = null,
    val searchTerms: MutableMap<String, Object>? = null,
) : Serializable {}
