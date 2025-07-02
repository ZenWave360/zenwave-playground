package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyAnswersRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class SurveyAnswersRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var surveyAnswersRepository: SurveyAnswersRepository

    @Test
    fun findAllTest() {
        val results = surveyAnswersRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val surveyAnswers = surveyAnswersRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(surveyAnswers.id)
        Assertions.assertNotNull(surveyAnswers.version)
        Assertions.assertNotNull(surveyAnswers.createdBy)
        Assertions.assertNotNull(surveyAnswers.createdDate)
    }

    @Test
    fun saveTest() {
        val surveyAnswers = SurveyAnswers()
        surveyAnswers.surveyId = 0L
        surveyAnswers.patientId = 0L
        surveyAnswers.date = LocalDate.now()
        surveyAnswers.lang = ""
        surveyAnswers.answers = List.of(Answer())



        // Persist aggregate root
        val created = surveyAnswersRepository.save(surveyAnswers)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)


    }

    @Test
    fun updateTest() {
        val id = 1L
        val surveyAnswers = surveyAnswersRepository.findById(id).orElseThrow()
        surveyAnswers.surveyId = 0L
        surveyAnswers.patientId = 0L
        surveyAnswers.date = LocalDate.now()
        surveyAnswers.lang = ""
        surveyAnswers.answers = List.of(Answer())

        val updated = surveyAnswersRepository.save(surveyAnswers)
        Assertions.assertEquals(0L, updated.surveyId)
        Assertions.assertEquals(0L, updated.patientId)
        Assertions.assertEquals(LocalDate.now(), updated.date)
        Assertions.assertEquals("", updated.lang)
        Assertions.assertEquals(List.of(Answer()), updated.answers)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        surveyAnswersRepository.deleteById(id)
        val notFound = surveyAnswersRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}