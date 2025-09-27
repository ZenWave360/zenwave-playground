package io.zenwave360.example.clinicaltool.modules.users.config;

import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final UserRepository userRepository = new UserRepositoryInMemory();

    @Bean
    @Primary
    public <T extends UserRepository> T userRepository() {
        return (T) userRepository;
    }
}
