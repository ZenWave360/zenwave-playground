package io.zenwave360.example.clinicaltool.modules.masterdata.dtos

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** MasterDataKeyValue. */
data class MasterDataKeyValue(val key: String? = null, val value: String? = null) : Serializable {}
