package io.zenwave360.example.clinicaltool.modules.documents.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface DocumentServiceMapper {

    companion object {
        val INSTANCE: DocumentServiceMapper = Mappers.getMapper(DocumentServiceMapper::class.java)
    }

    // input mappings
    // DocumentInfo-DocumentInfo uploadDocument

    fun asDocumentInfo(input: DocumentInfo): DocumentInfo

    @Mapping(target = "id", ignore = true)
    fun update(@MappingTarget entity: DocumentInfo, input: DocumentInfo): DocumentInfo

    // DocumentIdsnull-DocumentInfo listDocumentInfo
    fun asDocumentInfo(documentIds: List<Long>): DocumentInfo {
        return DocumentInfo().apply {
            // TODO: implement this method
            // this.documentIds = documentIds
        }
    }

    fun update(entity: DocumentInfo, documentIds: List<Long>): DocumentInfo {
        return entity.apply {
            // TODO: implement this method
            // this.documentIds = documentIds
        }
    }
    // output mappings
}
