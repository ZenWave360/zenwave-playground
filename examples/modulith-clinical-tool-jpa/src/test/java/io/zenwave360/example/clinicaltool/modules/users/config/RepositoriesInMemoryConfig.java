package io.zenwave360.example.clinicaltool.modules.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final UserRepository userRepository = new UserRepositoryInMemory();
    @Bean @Primary
    public <T extends UserRepository> T userRepository() {
        return (T) userRepository;
    }

}
