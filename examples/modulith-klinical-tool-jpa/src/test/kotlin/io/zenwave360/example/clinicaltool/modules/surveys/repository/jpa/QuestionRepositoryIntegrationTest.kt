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

class QuestionRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var questionRepository: QuestionRepository

    @Test
    fun findAllTest() {
        val results = questionRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val question = questionRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(question.id)
        Assertions.assertNotNull(question.version)
        Assertions.assertNotNull(question.createdBy)
        Assertions.assertNotNull(question.createdDate)
    }

    @Test
    fun saveTest() {
        val question = Question()
        question.name = ""
        question.questionType = QuestionType.values()[0]
        question.required = true
        question.rangeStart = 0 as Integer
        question.rangeEnd = 0 as Integer
        question.translations = mutableListOf(QuestionTranslation())
        question.options = mutableListOf(Option())
        question.includeOther = false

        // Persist aggregate root
        val created = questionRepository.save(question)

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
        val question = questionRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        question.name = ""
        question.questionType = QuestionType.values()[0]
        question.required = true
        question.rangeStart = 0 as Integer
        question.rangeEnd = 0 as Integer
        question.translations = mutableListOf(QuestionTranslation())
        question.options = mutableListOf(Option())
        question.includeOther = false

        val updated = questionRepository.save(question)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals(QuestionType.values()[0], updated.questionType)
        Assertions.assertEquals(true, updated.required)
        Assertions.assertEquals(0 as Integer, updated.rangeStart)
        Assertions.assertEquals(0 as Integer, updated.rangeEnd)
        Assertions.assertEquals(false, updated.includeOther)
//        Assertions.assertEquals(mutableListOf(QuestionTranslation()), updated.translations)
//        Assertions.assertEquals(mutableListOf(Option()), updated.options)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        questionRepository.deleteById(id)
        val notFound = questionRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
