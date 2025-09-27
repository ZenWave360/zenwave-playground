package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class SurveyRepositoryInMemory extends InMemoryJpaRepository<Survey> implements SurveyRepository {
    @Override
    public java.util.Optional<Survey> findByNameAndHospitalId(String name, Long hospitalId) {
        return getEntities().values().stream()
                .filter(e ->
                        isSameValue(name, readField(e, "name")) && isSameValue(hospitalId, readField(e, "hospitalId")))
                .findFirst();
    }
}
