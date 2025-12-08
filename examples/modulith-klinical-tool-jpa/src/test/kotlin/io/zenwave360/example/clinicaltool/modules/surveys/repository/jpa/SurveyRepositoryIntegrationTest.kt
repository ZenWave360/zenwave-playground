package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import jakarta.persistence.EntityManager
import java.time.*
import java.util.List
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class SurveyRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var surveyRepository: SurveyRepository

    @Test
    fun findAllTest() {
        val results = surveyRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val survey = surveyRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(survey.id)
        Assertions.assertNotNull(survey.version)
        Assertions.assertNotNull(survey.createdBy)
        Assertions.assertNotNull(survey.createdDate)
    }

    @Test
    fun saveTest() {
        val survey = Survey()
        survey.name = ""
        survey.hospitalId = 1L
        survey.title = ""
        survey.lang = ""
        survey.sections = mutableListOf(SurveySection())

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
        val survey = surveyRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        survey.name = ""
        survey.hospitalId = 1L
        survey.title = ""
        survey.lang = ""
        survey.sections = mutableListOf(SurveySection())

        val updated = surveyRepository.save(survey)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals(0L, updated.hospitalId)
        Assertions.assertEquals("", updated.title)
        Assertions.assertEquals("", updated.lang)
//        Assertions.assertEquals(mutableListOf(SurveySection()), updated.sections)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        surveyRepository.deleteById(id)
        val notFound = surveyRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
