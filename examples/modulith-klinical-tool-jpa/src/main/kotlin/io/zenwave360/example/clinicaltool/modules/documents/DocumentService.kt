package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [DocumentInfo].
 */
@org.springframework.modulith.NamedInterface("DocumentService")
interface DocumentService {


         /**
      *
      *
      */

     fun listDocumentInfo(documentIds: List<Long>): List<DocumentInfo>


         /**
      *
      *
      */

     fun downloadDocument(id: Long): DocumentInfo


         /**
      *
      *
      */

     fun uploadDocument(input: DocumentInfo): DocumentInfo


         /**
      *
      *
      */

     fun deleteDocumentInfo(id: Long): Unit



}
