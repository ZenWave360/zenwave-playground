package io.zenwave360.example.clinicaltool.modules.surveys.config;


import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;


import io.zenwave360.example.clinicaltool.modules.surveys.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.implementation.*;


    

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.ArrayList;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {



    protected final SurveysBackofficeServiceImpl surveysBackofficeService = new SurveysBackofficeServiceImpl(surveyRepository(), questionRepository()
        );
    protected final SurveysServiceImpl surveysService = new SurveysServiceImpl(surveyAnswersRepository()
        );
    @Bean
    public <T extends SurveysBackofficeService> T surveysBackofficeService() {
        return (T) surveysBackofficeService;
    }
    @Bean
    public <T extends SurveysService> T surveysService() {
        return (T) surveysService;
    }


	static List<Survey> _surveys;
	static List<Question> _questions;
	static List<SurveyAnswers> _surveyAnswers;
	public void reloadTestData() {
        
		var testDataLoader = new TestDataLoader(List.of(Survey.class, SurveySection.class, SectionTranslation.class, Question.class, QuestionTranslation.class, Option.class, OptionTranslation.class, SurveyAnswers.class, Answer.class));
		var surveys = _surveys != null? _surveys : testDataLoader.loadCollectionTestDataAsObjects(Survey.class);
		surveyRepository().deleteAll();
		surveyRepository().saveAll(surveys);
		var questions = _questions != null? _questions : testDataLoader.loadCollectionTestDataAsObjects(Question.class);
		questionRepository().deleteAll();
		questionRepository().saveAll(questions);
		var surveyAnswers = _surveyAnswers != null? _surveyAnswers : testDataLoader.loadCollectionTestDataAsObjects(SurveyAnswers.class);
		surveyAnswersRepository().deleteAll();
		surveyAnswersRepository().saveAll(surveyAnswers);
	}
}
