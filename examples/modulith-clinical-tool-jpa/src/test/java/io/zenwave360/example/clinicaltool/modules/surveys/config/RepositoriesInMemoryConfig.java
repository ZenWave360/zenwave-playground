package io.zenwave360.example.clinicaltool.modules.surveys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final SurveyRepository surveyRepository = new SurveyRepositoryInMemory();
    @Bean @Primary
    public <T extends SurveyRepository> T surveyRepository() {
        return (T) surveyRepository;
    }
    protected final QuestionRepository questionRepository = new QuestionRepositoryInMemory();
    @Bean @Primary
    public <T extends QuestionRepository> T questionRepository() {
        return (T) questionRepository;
    }
    protected final SurveyAnswersRepository surveyAnswersRepository = new SurveyAnswersRepositoryInMemory();
    @Bean @Primary
    public <T extends SurveyAnswersRepository> T surveyAnswersRepository() {
        return (T) surveyAnswersRepository;
    }

}
