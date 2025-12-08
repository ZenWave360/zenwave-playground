package io.zenwave360.example.clinicaltool.modules.documents.inmemory

import io.zenwave360.example.clinicaltool.modules.documents.DocumentInfoRepository
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import java.math.*
import java.time.*
import java.util.*

class DocumentInfoRepositoryInMemory : InMemoryJpaRepository<DocumentInfo, Long>(), DocumentInfoRepository {
    override fun <S : DocumentInfo> save(entity: S): S {
        super.save(entity)
        entity.documentData?.id = entity.documentData?.id ?: entity.id
        return entity
    }
}
