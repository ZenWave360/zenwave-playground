package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class QuestionRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void findAllTest() {
        var results = questionRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var question = questionRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(question.getId());
        Assertions.assertNotNull(question.getVersion());
        Assertions.assertNotNull(question.getCreatedBy());
        Assertions.assertNotNull(question.getCreatedDate());
    }

    @Test
    void saveTest() {
        Question question = new Question();
        question.setName("");
        question.setQuestionType(QuestionType.values()[0]);
        question.setRequired(true);
        question.setRangeStart(0);
        question.setRangeEnd(0);
        question.setTranslations(List.of(new QuestionTranslation()));
        question.setOptions(List.of(new Option()));
        question.setIncludeOther(false);

        // Persist aggregate root
        var created = questionRepository.save(question);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
        Assertions.assertNotNull(created.getCreatedBy());
        Assertions.assertNotNull(created.getCreatedDate());
    }

    @Test
    void updateTest() {
        var id = 1L;
        var question = questionRepository.findById(id).orElseThrow();
        question.setName("");
        question.setQuestionType(QuestionType.values()[0]);
        question.setRequired(true);
        question.setRangeStart(0);
        question.setRangeEnd(0);
        question.setTranslations(List.of(new QuestionTranslation()));
        question.setOptions(List.of(new Option()));
        question.setIncludeOther(false);

        question = questionRepository.save(question);
        Assertions.assertEquals("", question.getName());
        Assertions.assertEquals(QuestionType.values()[0], question.getQuestionType());
        Assertions.assertEquals(true, question.isRequired());
        Assertions.assertEquals(0, question.getRangeStart());
        Assertions.assertEquals(0, question.getRangeEnd());
        Assertions.assertEquals(List.of(new QuestionTranslation()), question.getTranslations());
        Assertions.assertEquals(List.of(new Option()), question.getOptions());
        Assertions.assertEquals(false, question.getIncludeOther());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        questionRepository.deleteById(id);
        var notFound = questionRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
