package io.zenwave360.example.clinicaltool.modules.masterdata.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface MasterDataServiceMapper {

    companion object {
        val INSTANCE: MasterDataServiceMapper = Mappers.getMapper(MasterDataServiceMapper::class.java)
    }

// input mappings
    // MasterDataFilternull-MasterData listMasterDataOfType
    fun asMasterData(type: MasterDataType, lang: String): MasterData
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: MasterData, type: MasterDataType, lang: String): MasterData
    // MasterData-MasterData updateMasterData
    fun asMasterData(input: MasterData): MasterData
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: MasterData, input: MasterData): MasterData
// output mappings
    // MasterData-MasterDataKeyValue listMasterDataOfType
    fun asMasterDataKeyValue(entity: MasterData): MasterDataKeyValue
    fun asMasterDataKeyValueList(entity: List<MasterData>): List<MasterDataKeyValue>
}
