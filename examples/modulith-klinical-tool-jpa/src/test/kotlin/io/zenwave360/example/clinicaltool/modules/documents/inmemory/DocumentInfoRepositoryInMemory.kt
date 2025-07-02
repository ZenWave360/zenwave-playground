package io.zenwave360.example.clinicaltool.modules.documents.inmemory

import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.DocumentInfoRepository
import java.math.*
import java.time.*
import java.util.*

class DocumentInfoRepositoryInMemory : InMemoryJpaRepository<DocumentInfo, Long>(), DocumentInfoRepository {
}
