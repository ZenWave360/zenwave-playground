package io.zenwave360.example.clinicaltool.modules.documents.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface DocumentServiceMapper {

    companion object {
        val INSTANCE: DocumentServiceMapper = Mappers.getMapper(DocumentServiceMapper::class.java)
    }

// input mappings
    // DocumentInfo-DocumentInfo uploadDocument
    fun asDocumentInfo(input: DocumentInfo): DocumentInfo
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: DocumentInfo, input: DocumentInfo): DocumentInfo
    // DocumentIdsnull-DocumentInfo listDocumentInfo
    fun asDocumentInfo(documentIds: Long): DocumentInfo
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: DocumentInfo, documentIds: Long): DocumentInfo
// output mappings
}
