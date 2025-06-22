package io.zenwave360.example.clinicaltool.modules.documents.core.inbound;

import io.zenwave360.example.clinicaltool.modules.documents.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [DocumentInfo].
 */
public interface DocumentService {

     /**
      * 
      *
      */

    public List<DocumentInfo> listDocumentInfo(Long documentIds)
;
     /**
      * 
      *
      */

    public DocumentInfo downloadDocument(Long id)
;
     /**
      * 
      *
      */

    public DocumentInfo uploadDocument(DocumentInfo input)
;
     /**
      * 
      *
      */

    public void deleteDocumentInfo(Long id)
;


}
