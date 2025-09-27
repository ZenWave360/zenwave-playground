package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import io.zenwave360.example.clinicaltool.modules.users.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [User].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserServiceMapper userServiceMapper = UserServiceMapper.INSTANCE;

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        log.debug("Request findByUsername: {}", username);

        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createUser(User input) {
        log.debug("[CRUD] Request to save User: {}", input);
        var user = userServiceMapper.update(new User(), input);
        user = userRepository.save(user);
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return user;
    }

    @Transactional
    public Optional<User> updateUser(String username, User input) {
        log.debug("Request updateUser: {} {}", username, input);

        var user = userRepository
                .findByUsername(username)
                .map(existingUser -> {
                    return userServiceMapper.update(existingUser, input);
                })
                .map(userRepository::save);
        return user;
    }

    public Optional<User> lockAccount(String username) {
        log.debug("Request lockAccount: {}", username);

        return userRepository.findByUsername(username);
    }

    public Optional<User> unLockAccount(String username) {
        log.debug("Request unLockAccount: {}", username);

        return userRepository.findByUsername(username);
    }

    @Transactional
    public void deleteUser(String username) {
        log.debug("Request deleteUser: {}", username);
        var user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }

    public Page<User> searchUsers(SearchCriteria input, Pageable pageable) {
        log.debug("Request searchUsers: {} {}", input, pageable);

        var users = userRepository.findAll(pageable);
        return users;
    }

    public Page<User> listUsers(Pageable pageable) {
        log.debug("Request listUsers: {}", pageable);

        var users = userRepository.findAll(pageable);
        return users;
    }
}
