package io.zenwave360.example.clinicaltool.modules.masterdata.inmemory

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.MasterDataRepository
import java.math.*
import java.time.*
import java.util.*

class MasterDataRepositoryInMemory : InMemoryJpaRepository<MasterData, Long>(), MasterDataRepository {
    override fun findByTypeAndKey(type: MasterDataType, key: String): java.util.Optional<MasterData> {
        return getEntities().values.stream().filter { e ->
             isSameValue(type, readField(e, "type")) && isSameValue(key, readField(e, "key")) 
        }.findFirst()
    }
}
