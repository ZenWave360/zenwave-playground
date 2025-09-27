package io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface SurveysBackofficeServiceMapper {

    SurveysBackofficeServiceMapper INSTANCE = Mappers.getMapper(SurveysBackofficeServiceMapper.class);

    // input mappings
    // Survey-Survey updateSurvey
    Survey asSurvey(Survey input);

    @Mapping(target = "id", ignore = true)
    Survey update(@MappingTarget Survey entity, Survey input);
    // Question-Question updateQuestion
    Question asQuestion(Question input);

    @Mapping(target = "id", ignore = true)
    Question update(@MappingTarget Question entity, Question input);
    // output mappings
}
