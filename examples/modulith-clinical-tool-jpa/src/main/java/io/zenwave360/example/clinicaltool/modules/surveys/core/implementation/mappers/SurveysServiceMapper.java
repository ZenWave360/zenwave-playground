package io.zenwave360.example.clinicaltool.modules.surveys.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.inbound.dtos.*;

import org.mapstruct.AfterMapping;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = { BaseMapper.class })
public interface SurveysServiceMapper {

    SurveysServiceMapper INSTANCE = Mappers.getMapper(SurveysServiceMapper.class);

// input mappings
    // SurveyByNameAndPatientnull-SurveyAnswers getSurveyAndQuestionsForPatient
        SurveyAnswers asSurveyAnswers(String name, Long patientId, String lang);
    @Mapping(target = "id", ignore = true)SurveyAnswers update(@MappingTarget SurveyAnswers entity, String name, Long patientId, String lang);
    // SurveyAnswers-SurveyAnswers answerSurvey
        SurveyAnswers asSurveyAnswers(SurveyAnswers input);
    @Mapping(target = "id", ignore = true)SurveyAnswers update(@MappingTarget SurveyAnswers entity, SurveyAnswers input);
    // java.util.Map-SurveyAnswers updateSurveyAnswers
        SurveyAnswers asSurveyAnswers(java.util.Map input);
    @Mapping(target = "id", ignore = true)SurveyAnswers update(@MappingTarget SurveyAnswers entity, java.util.Map input);
// output mappings
    // SurveyAnswers-SurveyAndQuestions getSurveyAndQuestionsForPatient
    SurveyAndQuestions asSurveyAndQuestions(SurveyAnswers entity);
}
