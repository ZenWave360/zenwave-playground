package io.zenwave360.example.clinicaltool.modules.documents.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface DocumentDTOsMapper {

    companion object {
        val INSTANCE: DocumentDTOsMapper = Mappers.getMapper(DocumentDTOsMapper::class.java)
    }

    // request mappings
    fun asDocumentInfo(file: org.springframework.web.multipart.MultipartFile): DocumentInfo

    // response mappings
    
    fun asDocumentInfoDTOList(entityList: List<DocumentInfo>): List<DocumentInfoDTO>
    
    fun asDocumentInfoDTO(entity: DocumentInfo): DocumentInfoDTO
    

}
