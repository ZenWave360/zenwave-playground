package io.example.asyncapi.provider.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.QuestionRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class QuestionRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Test
    fun findAllTest() {
        val results = questionRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val question = questionRepository.findById(id).orElseThrow()
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
//        question.rangeStart = java.lang.Integer.valueOf(42)
//        question.rangeEnd = 0.toInt()
        question.translations = List.of(QuestionTranslation())
        question.options = List.of(Option())
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
        val question = questionRepository.findById(id).orElseThrow()
        question.name = ""
        question.questionType = QuestionType.values()[0]
        question.required = true
//        question.rangeStart = 0.toInt()
//        question.rangeEnd = 0.toInt()
        question.translations = List.of(QuestionTranslation())
        question.options = List.of(Option())
        question.includeOther = false

        val updated = questionRepository.save(question)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals(QuestionType.values()[0], updated.questionType)
        Assertions.assertEquals(true, updated.required)
        Assertions.assertEquals(0, updated.rangeStart)
        Assertions.assertEquals(0, updated.rangeEnd)
        Assertions.assertEquals(List.of(QuestionTranslation()), updated.translations)
        Assertions.assertEquals(List.of(Option()), updated.options)
        Assertions.assertEquals(false, updated.includeOther)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        questionRepository.deleteById(id)
        val notFound = questionRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}
