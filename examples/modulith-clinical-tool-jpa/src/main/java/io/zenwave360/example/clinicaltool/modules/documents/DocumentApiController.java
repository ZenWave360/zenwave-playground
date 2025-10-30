package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * REST controller for DocumentApi.
 */
@RestController
@RequestMapping("/api")
public class DocumentApiController implements DocumentApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private DocumentService documentService;

    @Autowired
    public DocumentApiController setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
        return this;
    }

    private DocumentDTOsMapper mapper = DocumentDTOsMapper.INSTANCE;

    public DocumentApiController(DocumentService documentService) {

        this.documentService = documentService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<DocumentInfoDTO>> listDocumentInfo(List<Long> documentIds) {
        log.debug("REST request to listDocumentInfo: {}", documentIds);
        var documentInfo = documentService.listDocumentInfo(documentIds);
        var responseDTO = mapper.asDocumentInfoDTOList(documentInfo);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<Resource> downloadDocument(Long id, Boolean preview) {
        log.debug("REST request to downloadDocument: {}, {}", id, preview);
        var documentInfo = documentService.downloadDocument(id);
        byte[] bytes = documentInfo.getDocumentData().getData();
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.status(200)
                .header("Content-Disposition", "inline") // or attachment; filename=example.pdf
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // TODO: set content type
                .body(resource);
    }

    @Override
    public ResponseEntity<Void> deleteDocumentInfo(Long id) {
        log.debug("REST request to deleteDocumentInfo: {}", id);
        documentService.deleteDocumentInfo(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<DocumentInfoDTO> uploadDocument(
            String uuid,
            List<String> tags,
            org.springframework.web.multipart.MultipartFile file) {
        log.debug("REST request to uploadDocument: {}, {}, {}", file, uuid, tags);
        var input = mapper.asDocumentInfo(file, uuid, tags);
        var documentInfo = documentService.uploadDocument(input);
        DocumentInfoDTO responseDTO = mapper.asDocumentInfoDTO(documentInfo);
        return ResponseEntity.status(201).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null
                ? Sort.by(sort.stream()
                        .map(sortParam -> {
                            String[] parts = sortParam.split(":");
                            String property = parts[0];
                            Sort.Direction direction =
                                    parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
                            return new Sort.Order(direction, property);
                        })
                        .toList())
                : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
