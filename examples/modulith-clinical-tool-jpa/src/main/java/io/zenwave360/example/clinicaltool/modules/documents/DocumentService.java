package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Inbound Service Port for managing [DocumentInfo].
 */
@org.springframework.modulith.NamedInterface("DocumentService")
public interface DocumentService {

    /**
     *
     *
     */
    public List<DocumentInfo> listDocumentInfo(List<Long> documentIds);
    /**
     *
     *
     */
    public DocumentInfo downloadDocument(Long id);
    /**
     *
     *
     */
    public DocumentInfo uploadDocument(DocumentInfo input);
    /**
     *
     *
     */
    public void deleteDocumentInfo(Long id);
}
