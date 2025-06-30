package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [User].
 */
@org.springframework.modulith.NamedInterface("UserService")
public interface UserService {

     /**
      * 
      *
      */

    public Optional<User> findByUsername(String username)
;
     /**
      * 
      *
      */

    public User createUser(User input)
;
     /**
      * 
      *
      */

    public Optional<User> updateUser(String username, User input)
;
     /**
      * 
      *
      */

    public Optional<User> lockAccount(String username)
;
     /**
      * 
      *
      */

    public Optional<User> unLockAccount(String username)
;
     /**
      * 
      *
      */

    public void deleteUser(String username)
;
     /**
      * 
      *
      */

    public Page<User> searchUsers(SearchCriteria input, Pageable pageable)
;
     /**
      * 
      *
      */

    public Page<User> listUsers(Pageable pageable)
;


}
