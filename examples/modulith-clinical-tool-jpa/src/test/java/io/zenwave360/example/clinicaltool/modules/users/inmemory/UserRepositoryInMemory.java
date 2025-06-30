package io.zenwave360.example.clinicaltool.modules.users.inmemory;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.UserRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class UserRepositoryInMemory extends InMemoryJpaRepository<User> implements UserRepository {
    @Override
    public java.util.Optional<User> findByUsername(String username) {
        return getEntities().values().stream().filter(e ->
             isSameValue(username, readField(e, "username")) 
        ).findFirst();
    }
}
