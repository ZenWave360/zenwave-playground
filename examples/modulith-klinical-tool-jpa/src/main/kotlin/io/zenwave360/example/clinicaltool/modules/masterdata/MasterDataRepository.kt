package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/** Spring Data JPA repository for the MasterData entity. */
@Suppress("unused")
@Repository
interface MasterDataRepository : JpaRepository<MasterData, Long> {
    fun findByTypeAndKey(type: MasterDataType, key: String): MasterData?
}
