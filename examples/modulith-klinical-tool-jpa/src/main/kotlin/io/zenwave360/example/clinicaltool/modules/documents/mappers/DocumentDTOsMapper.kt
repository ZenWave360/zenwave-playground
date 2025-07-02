package io.zenwave360.example.clinicaltool.modules.documents.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.documents.domain.DocumentInfo
import io.zenwave360.example.clinicaltool.modules.documents.dtos.DocumentDataDTO
import io.zenwave360.example.clinicaltool.modules.documents.dtos.DocumentInfoDTO
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface DocumentDTOsMapper {

    companion object {
        val INSTANCE: DocumentDTOsMapper = Mappers.getMapper(DocumentDTOsMapper::class.java)
    }

    // request mappings
    fun asDocumentInfo(
        file: org.springframework.web.multipart.MultipartFile?,
        id: Long?,
        version: Int?,
        uuid: String?,
        fileName: String,
        documentType: String,
        contentType: String,
        tags: List<String>?,
        documentData: DocumentDataDTO?
    ): DocumentInfo

    // response mappings

    fun asDocumentInfoDTOList(entityList: List<DocumentInfo>): List<DocumentInfoDTO>

    fun asDocumentInfoDTO(entity: DocumentInfo): DocumentInfoDTO


}
