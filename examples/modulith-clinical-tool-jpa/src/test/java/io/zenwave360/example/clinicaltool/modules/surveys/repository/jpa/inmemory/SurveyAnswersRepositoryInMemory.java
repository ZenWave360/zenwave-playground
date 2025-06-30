package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyAnswersRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class SurveyAnswersRepositoryInMemory extends InMemoryJpaRepository<SurveyAnswers> implements SurveyAnswersRepository {
    @Override
    public java.util.Optional<SurveyAnswers> findBySurveyIdAndPatientIdAndDate(Long surveyId, Long patientId, LocalDate date) {
        return getEntities().values().stream().filter(e ->
             isSameValue(surveyId, readField(e, "surveyId")) && isSameValue(patientId, readField(e, "patientId")) && isSameValue(date, readField(e, "date")) 
        ).findFirst();
    }
}
