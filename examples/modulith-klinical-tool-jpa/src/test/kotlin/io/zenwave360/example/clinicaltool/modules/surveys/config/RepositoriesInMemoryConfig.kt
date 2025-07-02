package io.zenwave360.example.clinicaltool.modules.surveys.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val surveyRepository = SurveyRepositoryInMemory()

    @Bean
    @Primary
    fun surveyRepository(): SurveyRepositoryInMemory {
        return surveyRepository
    }
    protected val questionRepository = QuestionRepositoryInMemory()

    @Bean
    @Primary
    fun questionRepository(): QuestionRepositoryInMemory {
        return questionRepository
    }
    protected val surveyAnswersRepository = SurveyAnswersRepositoryInMemory()

    @Bean
    @Primary
    fun surveyAnswersRepository(): SurveyAnswersRepositoryInMemory {
        return surveyAnswersRepository
    }

}
