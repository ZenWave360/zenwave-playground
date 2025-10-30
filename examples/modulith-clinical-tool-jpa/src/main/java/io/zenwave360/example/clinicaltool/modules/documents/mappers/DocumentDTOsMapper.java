package io.zenwave360.example.clinicaltool.modules.documents.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface DocumentDTOsMapper {

    DocumentDTOsMapper INSTANCE = Mappers.getMapper(DocumentDTOsMapper.class);

    // request mappings
    @Mapping(target = "fileName", source = "file.originalFilename")
    @Mapping(target = "contentType", source = "file.contentType")
    @Mapping(target = "documentType", source = "file.contentType", qualifiedByName = "getDocumentType")
    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "documentData.data", source = "file", qualifiedByName = "extractFileBytes")
    DocumentInfo asDocumentInfo(org.springframework.web.multipart.MultipartFile file, String uuid, List<String> tags);

    @Named("getDocumentType")
    default String getDocumentType(String contentType) {
        if (contentType == null) return "unknown";
        if (contentType.startsWith("image/")) return "image";
        if (contentType.equals("application/pdf")) return "pdf";
        if (contentType.startsWith("text/")) return "text";
        return "document";
    }

    @Named("extractFileBytes")
    default byte[] extractFileBytes(org.springframework.web.multipart.MultipartFile file) {
        try {
            return file.getBytes();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to extract file bytes", e);
        }
    }

    // response mappings

    List<DocumentInfoDTO> asDocumentInfoDTOList(List<DocumentInfo> entityList);

    @Mapping(target = "documentData.data", ignore = true)
    DocumentInfoDTO asDocumentInfoDTO(DocumentInfo entity);
}
