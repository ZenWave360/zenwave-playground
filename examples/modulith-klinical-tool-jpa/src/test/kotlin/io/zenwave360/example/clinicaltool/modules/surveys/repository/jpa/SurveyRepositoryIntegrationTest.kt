package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class SurveyRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    @Test
    fun findAllTest() {
        val results = surveyRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val survey = surveyRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(survey.id)
        Assertions.assertNotNull(survey.version)
        Assertions.assertNotNull(survey.createdBy)
        Assertions.assertNotNull(survey.createdDate)
    }

    @Test
    fun saveTest() {
        val survey = Survey()
        survey.name = ""
        survey.hospitalId = 0L
        survey.title = ""
        survey.lang = ""
        survey.sections = List.of(SurveySection())



        // Persist aggregate root
        val created = surveyRepository.save(survey)

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
        val survey = surveyRepository.findById(id).orElseThrow()
        survey.name = ""
        survey.hospitalId = 0L
        survey.title = ""
        survey.lang = ""
        survey.sections = List.of(SurveySection())

        val updated = surveyRepository.save(survey)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals(0L, updated.hospitalId)
        Assertions.assertEquals("", updated.title)
        Assertions.assertEquals("", updated.lang)
        Assertions.assertEquals(List.of(SurveySection()), updated.sections)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        surveyRepository.deleteById(id)
        val notFound = surveyRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}