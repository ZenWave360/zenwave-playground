package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.QuestionRepository
import java.math.*
import java.time.*
import java.util.*

class QuestionRepositoryInMemory : InMemoryJpaRepository<Question, Long>(), QuestionRepository {}
