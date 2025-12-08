package io.zenwave360.example.clinicaltool.modules.users.config;

import io.zenwave360.example.clinicaltool.common.TestDataLoader;
import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    protected final UserServiceImpl userService = new UserServiceImpl(userRepository());

    @Bean
    public <T extends UserService> T userService() {
        return (T) userService;
    }

    static List<User> _users;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(List.of(User.class));
        var users = _users != null ? _users : testDataLoader.loadCollectionTestDataAsObjects(User.class);
        userRepository().deleteAll();
        userRepository().saveAll(users);
    }
}
