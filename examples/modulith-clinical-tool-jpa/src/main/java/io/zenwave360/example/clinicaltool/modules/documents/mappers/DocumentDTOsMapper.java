package io.zenwave360.example.clinicaltool.modules.documents.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface DocumentDTOsMapper {

    DocumentDTOsMapper INSTANCE = Mappers.getMapper(DocumentDTOsMapper.class);

    // request mappings
    DocumentInfo asDocumentInfo(
            org.springframework.web.multipart.MultipartFile file,
            Long id,
            Integer version,
            String uuid,
            String fileName,
            String documentType,
            String contentType,
            List<String> tags,
            DocumentDataDTO documentData);

    // response mappings

    List<DocumentInfoDTO> asDocumentInfoDTOList(List<DocumentInfo> entityList);

    DocumentInfoDTO asDocumentInfoDTO(DocumentInfo entity);
}
