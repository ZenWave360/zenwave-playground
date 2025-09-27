package io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface TermsAndConditionsServiceMapper {

    TermsAndConditionsServiceMapper INSTANCE = Mappers.getMapper(TermsAndConditionsServiceMapper.class);

    // input mappings
    // TermsAndConditions-TermsAndConditions updateTermsAndConditions
    TermsAndConditions asTermsAndConditions(TermsAndConditions input);

    @Mapping(target = "id", ignore = true)
    TermsAndConditions update(@MappingTarget TermsAndConditions entity, TermsAndConditions input);
    // Langnull-TermsAndConditions getCurrentTermsAndConditions
    TermsAndConditions asTermsAndConditions(String lang);

    @Mapping(target = "id", ignore = true)
    TermsAndConditions update(@MappingTarget TermsAndConditions entity, String lang);
    // output mappings
}
