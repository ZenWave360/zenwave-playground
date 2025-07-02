package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.domain.DocumentInfo
import io.zenwave360.example.clinicaltool.modules.documents.mappers.DocumentServiceMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Service Implementation for managing [DocumentInfo].
 */
@Service
@Transactional(readOnly = true)
open class DocumentServiceImpl(

    private val documentInfoRepository: DocumentInfoRepository


) : DocumentService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val documentServiceMapper: DocumentServiceMapper = DocumentServiceMapper.INSTANCE


    override fun listDocumentInfo(documentIds: List<Long>): List<DocumentInfo> {
        log.debug("Request listDocumentInfo: {}", documentIds);

        val documentInfos = documentInfoRepository.findAllById(documentIds)
        return documentInfos


    }


    override fun downloadDocument(id: Long): DocumentInfo {
        log.debug("Request downloadDocument: {}", id);

        return documentInfoRepository.findById(id).orElseThrow()


    }


    override fun uploadDocument(input: DocumentInfo): DocumentInfo {
        log.debug("Request uploadDocument: {}", input);

        val documentInfo = documentServiceMapper.update(DocumentInfo(), input)
        // TODO: implement this method
        return documentInfo


    }

    @Transactional

    override fun deleteDocumentInfo(id: Long): Unit {
        log.debug("[CRUD] Request to delete DocumentInfo : {}", id)
        documentInfoRepository.deleteById(id)


    }


}
