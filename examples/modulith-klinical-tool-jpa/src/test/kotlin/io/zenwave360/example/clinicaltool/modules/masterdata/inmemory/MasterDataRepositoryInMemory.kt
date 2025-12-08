package io.zenwave360.example.clinicaltool.modules.masterdata.inmemory

import io.zenwave360.example.clinicaltool.modules.masterdata.MasterDataRepository
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import java.math.*
import java.time.*
import java.util.*

class MasterDataRepositoryInMemory : InMemoryJpaRepository<MasterData, Long>(), MasterDataRepository {
    override fun findByTypeAndKey(type: MasterDataType, key: String): MasterData? {
        return getEntities().values.firstOrNull { e ->
            isSameValue(type, readField(e, "type")) && isSameValue(key, readField(e, "key"))
        }
    }
}
