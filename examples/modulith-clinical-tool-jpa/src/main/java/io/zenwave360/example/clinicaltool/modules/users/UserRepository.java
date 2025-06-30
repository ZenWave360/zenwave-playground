package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {java.util.Optional<User> findByUsername(String username);
}
