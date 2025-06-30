package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*;
import io.zenwave360.example.clinicaltool.modules.documents.*;

import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [DocumentInfo].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DocumentServiceMapper documentServiceMapper = DocumentServiceMapper.INSTANCE;


    private final DocumentInfoRepository documentInfoRepository;






    public List<DocumentInfo> listDocumentInfo(Long documentIds)
 {
    log.debug("Request listDocumentInfo: {}", documentIds);

    var documentInfos = documentInfoRepository.findAll();
    return documentInfos;


}


    public DocumentInfo downloadDocument(Long id)
 {
    log.debug("Request downloadDocument: {}", id);

    return documentInfoRepository.findById(id).orElseThrow();


}


    public DocumentInfo uploadDocument(DocumentInfo input)
 {
    log.debug("Request uploadDocument: {}", input);

    var documentInfo = documentServiceMapper.update(new DocumentInfo(), input);
    // TODO: implement this method
    return documentInfo;


}

    @Transactional

    public void deleteDocumentInfo(Long id)
 {
    log.debug("[CRUD] Request to delete DocumentInfo : {}", id);
    documentInfoRepository.deleteById(id);


}



}
