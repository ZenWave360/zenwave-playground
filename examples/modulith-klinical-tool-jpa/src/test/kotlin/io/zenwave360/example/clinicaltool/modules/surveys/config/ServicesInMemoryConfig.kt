package io.zenwave360.example.clinicaltool.modules.surveys.config

import io.zenwave360.example.clinicaltool.config.TestDataLoader
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {

    protected val surveysBackofficeService = SurveysBackofficeServiceImpl(surveyRepository(), questionRepository())
    protected val surveysService = SurveysServiceImpl(surveyAnswersRepository())

    @Bean
    open fun <T : SurveysBackofficeService> surveysBackofficeService(): T {
        return surveysBackofficeService as T
    }

    @Bean
    open fun <T : SurveysService> surveysService(): T {
        return surveysService as T
    }

    companion object {
        var _surveys: List<Survey>? = null
        var _questions: List<Question>? = null
        var _surveyAnswers: List<SurveyAnswers>? = null
    }

    fun reloadTestData() {

        val testDataLoader =
            TestDataLoader(
                listOf(
                    Survey::class.java,
                    SurveySection::class.java,
                    SectionTranslation::class.java,
                    Question::class.java,
                    QuestionTranslation::class.java,
                    Option::class.java,
                    OptionTranslation::class.java,
                    SurveyAnswers::class.java,
                    Answer::class.java,
                )
            )
        val surveys = _surveys ?: testDataLoader.loadCollectionTestDataAsObjects(Survey::class.java)
        surveyRepository().deleteAll()
        surveyRepository().saveAll(surveys)
        val questions = _questions ?: testDataLoader.loadCollectionTestDataAsObjects(Question::class.java)
        questionRepository().deleteAll()
        questionRepository().saveAll(questions)
        val surveyAnswers = _surveyAnswers ?: testDataLoader.loadCollectionTestDataAsObjects(SurveyAnswers::class.java)
        surveyAnswersRepository().deleteAll()
        surveyAnswersRepository().saveAll(surveyAnswers)
    }
}
