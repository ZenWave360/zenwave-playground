package io.zenwave360.example.clinicaltool.modules.surveys.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.outbound.jpa.QuestionRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class QuestionRepositoryInMemory extends InMemoryJpaRepository<Question> implements QuestionRepository {
}
