package io.zenwave360.example.clinicaltool.modules.documents.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface DocumentServiceMapper {

    DocumentServiceMapper INSTANCE = Mappers.getMapper(DocumentServiceMapper.class);

    // input mappings
    // DocumentInfo-DocumentInfo uploadDocument
    DocumentInfo asDocumentInfo(DocumentInfo input);

    @Mapping(target = "id", ignore = true)
    DocumentInfo update(@MappingTarget DocumentInfo entity, DocumentInfo input);

    // output mappings
}
