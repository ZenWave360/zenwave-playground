package io.zenwave360.example.clinicaltool.modules.surveys.web.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface SurveysBackofficeDTOsMapper {

    SurveysBackofficeDTOsMapper INSTANCE = Mappers.getMapper(SurveysBackofficeDTOsMapper.class);

    // request mappings
        Survey asSurvey(SurveyDTO dto);
        Question asQuestion(QuestionDTO dto);

    // response mappings
    
    List<QuestionDTO> asQuestionDTOList(List<Question> entityList);
    
    QuestionDTO asQuestionDTO(Question entity);
    
    
    List<SurveyDTO> asSurveyDTOList(List<Survey> entityList);
    
    SurveyDTO asSurveyDTO(Survey entity);
    


}
