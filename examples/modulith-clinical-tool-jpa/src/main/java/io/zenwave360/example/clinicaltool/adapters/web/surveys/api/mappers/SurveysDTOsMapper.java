package io.zenwave360.example.clinicaltool.adapters.web.surveys.api.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.surveys.api.dtos.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface SurveysDTOsMapper {

    SurveysDTOsMapper INSTANCE = Mappers.getMapper(SurveysDTOsMapper.class);

    // request mappings
        SurveyAnswers asSurveyAnswers(SurveyAnswersDTO dto);

    // response mappings
    
    SurveyAnswersDTO asSurveyAnswersDTO(SurveyAnswers entity);
    
    
    SurveyAndQuestionsDTO asSurveyAndQuestionsDTO(SurveyAndQuestions entity);
    


}
