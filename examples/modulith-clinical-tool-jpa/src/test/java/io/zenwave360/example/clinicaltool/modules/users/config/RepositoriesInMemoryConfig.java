package io.zenwave360.example.clinicaltool.modules.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.users.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.users.infrastructure.jpa.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final UserRepository userRepository = new UserRepositoryInMemory();
    @Bean @Primary
    public <T extends UserRepository> T userRepository() {
        return (T) userRepository;
    }

}
