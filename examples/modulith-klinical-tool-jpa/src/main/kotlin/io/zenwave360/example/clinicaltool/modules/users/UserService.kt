package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [User].
 */
@org.springframework.modulith.NamedInterface("UserService")
interface UserService {


         /**
      * 
      *
      */

     fun findByUsername(username: String): Optional<User>


         /**
      * 
      *
      */

     fun createUser(input: User): User


         /**
      * 
      *
      */

     fun updateUser(username: String, input: User): Optional<User>


         /**
      * 
      *
      */

     fun lockAccount(username: String): Optional<User>


         /**
      * 
      *
      */

     fun unLockAccount(username: String): Optional<User>


         /**
      * 
      *
      */

     fun deleteUser(username: String): Unit


         /**
      * 
      *
      */

     fun searchUsers(input: SearchCriteria, pageable: Pageable): Page<User>


         /**
      * 
      *
      */

     fun listUsers(pageable: Pageable): Page<User>



}
