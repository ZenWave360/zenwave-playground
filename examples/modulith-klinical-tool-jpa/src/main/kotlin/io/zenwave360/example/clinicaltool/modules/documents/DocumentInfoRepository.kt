package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the DocumentInfo entity.
 */
@Suppress("unused")
@Repository
interface DocumentInfoRepository : JpaRepository<DocumentInfo, Long> {
}
