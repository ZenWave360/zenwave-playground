package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.common.*
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*

import java.net.URI
import java.net.URISyntaxException
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest

/**
 * REST controller for DocumentApi.
 */
@RestController
@RequestMapping("/api")
open class DocumentApiController(
    private val documentService: DocumentService
) : DocumentApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = DocumentDTOsMapper.INSTANCE



    override fun listDocumentInfo(documentIds: List<Long>?): ResponseEntity<List<DocumentInfoDTO>> {
        log.debug("REST request to listDocumentInfo: {}", documentIds)
        val documentInfo =  documentService.listDocumentInfo(documentIds)
        val responseDTO = mapper.asDocumentInfoDTOList(documentInfo)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun downloadDocument(id: Long, preview: Boolean?): ResponseEntity<Resource> {
        log.debug("REST request to downloadDocument: {}, {}", id, preview)
        val documentInfo =  documentService.downloadDocument(id)
        val bytes: ByteArray? = null // TODO get bytes from documentData.data
        val resource = ByteArrayResource(bytes ?: ByteArray(0))
        return ResponseEntity
            .status(200)
            .header("Content-Disposition", "inline") // or attachment; filename=example.pdf
            .contentType(MediaType.APPLICATION_OCTET_STREAM) // TODO: set content type
            .body(resource)
    }

    override fun deleteDocumentInfo(id: Long): ResponseEntity<Unit> {
        log.debug("REST request to deleteDocumentInfo: {}", id)
        documentService.deleteDocumentInfo(id)
        return ResponseEntity.status(204).build()
    }

    override fun uploadDocument(uuid: kotlin.String?, tags: kotlin.collections.List<kotlin.String>?, file: org.springframework.web.multipart.MultipartFile): ResponseEntity<DocumentInfoDTO> {
        log.debug("REST request to uploadDocument: {}, {}, {}", file, uuid, tags)
        val input = mapper.asDocumentInfo(file)
        val documentInfo =  documentService.uploadDocument(input)
        val responseDTO = mapper.asDocumentInfoDTO(documentInfo)
        return ResponseEntity.status(201).body(responseDTO)
    }


    protected fun pageOf(page: Int?, limit: Int?, sort: List<String>?): Pageable {
        val sortOrder = sort?.let {
            Sort.by(it.map { sortParam ->
                val parts = sortParam.split(":")
                val property = parts[0]
                val direction = if (parts.size > 1) Sort.Direction.fromString(parts[1]) else Sort.Direction.ASC
                Sort.Order(direction, property)
            })
        } ?: Sort.unsorted()
        return PageRequest.of(page ?: 0, limit ?: 10, sortOrder)
    }
}
