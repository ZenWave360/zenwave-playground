package io.zenwave360.example.clinicaltool.modules.documents.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.documents.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.inbound.dtos.*;

import org.mapstruct.AfterMapping;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = { BaseMapper.class })
public interface DocumentServiceMapper {

    DocumentServiceMapper INSTANCE = Mappers.getMapper(DocumentServiceMapper.class);

// input mappings
    // DocumentInfo-DocumentInfo uploadDocument
        DocumentInfo asDocumentInfo(DocumentInfo input);
    @Mapping(target = "id", ignore = true)DocumentInfo update(@MappingTarget DocumentInfo entity, DocumentInfo input);
    // DocumentIdsnull-DocumentInfo listDocumentInfo
        DocumentInfo asDocumentInfo(Long documentIds);
    @Mapping(target = "id", ignore = true)DocumentInfo update(@MappingTarget DocumentInfo entity, Long documentIds);
// output mappings
}
