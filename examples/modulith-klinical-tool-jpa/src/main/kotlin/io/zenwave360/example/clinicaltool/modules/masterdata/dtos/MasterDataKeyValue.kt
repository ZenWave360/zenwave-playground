package io.zenwave360.example.clinicaltool.modules.masterdata.dtos

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * MasterDataKeyValue.
 */
data class MasterDataKeyValue(

    
    val key: String?  = null,

    
    val value: String?  = null

)  : Serializable {




}
