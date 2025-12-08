package io.zenwave360.example.clinicaltool.modules.masterdata.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface MasterDataDTOsMapper {

    companion object {
        val INSTANCE: MasterDataDTOsMapper = Mappers.getMapper(MasterDataDTOsMapper::class.java)
    }

    // request mappings
    fun asMasterData(dto: MasterDataDTO): MasterData

    // response mappings
    
    fun asMasterDataKeyValueDTO(entity: MasterDataKeyValue): MasterDataKeyValueDTO
    
    
    fun asMasterDataDTO(entity: MasterData): MasterDataDTO
    
    
    fun asMasterDataKeyValueDTOList(entityList: List<MasterDataKeyValue>): List<MasterDataKeyValueDTO>
    
    fun asMasterDataDTOList(entityList: List<MasterData>): List<MasterDataDTO>
    @Mapping(target = "content", source = "content", conditionExpression = "java(page.getContent() != null)")
    fun asMasterDataPaginatedDTO(page: Page<MasterData>): MasterDataPaginatedDTO
    fun asMasterDataDTOPage(page: Page<MasterData>): Page<MasterDataDTO> {
        return page.map { this.asMasterDataDTO(it) }
    }

}
