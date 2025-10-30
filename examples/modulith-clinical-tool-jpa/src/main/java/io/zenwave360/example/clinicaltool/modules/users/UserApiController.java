package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import io.zenwave360.example.clinicaltool.modules.users.mappers.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * REST controller for UserApi.
 */
@RestController
@RequestMapping("/api")
public class UserApiController implements UserApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private UserService userService;

    @Autowired
    public UserApiController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    private UserDTOsMapper mapper = UserDTOsMapper.INSTANCE;

    public UserApiController(UserService userService) {

        this.userService = userService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<UserDTO> findByUsername(String username) {
        log.debug("REST request to findByUsername: {}", username);
        var user = userService.findByUsername(username);
        if (user.isPresent()) {
            UserDTO responseDTO = mapper.asUserDTO(user.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(String username, UserDTO reqBody) {
        log.debug("REST request to updateUser: {}, {}", username, reqBody);
        var input = mapper.asUser(reqBody);
        var user = userService.updateUser(username, input);
        if (user.isPresent()) {
            UserDTO responseDTO = mapper.asUserDTO(user.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        log.debug("REST request to deleteUser: {}", username);
        userService.deleteUser(username);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO reqBody) {
        log.debug("REST request to createUser: {}", reqBody);
        var input = mapper.asUser(reqBody);
        var user = userService.createUser(input);
        UserDTO responseDTO = mapper.asUserDTO(user);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<UserDTO> enableAccount(String username) {
        log.debug("REST request to enableAccount: {}", username);
        var user = userService.enableAccount(username);
        if (user.isPresent()) {
            UserDTO responseDTO = mapper.asUserDTO(user.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserDTO> disableAccount(String username) {
        log.debug("REST request to disableAccount: {}", username);
        var user = userService.diableAccount(username);
        if (user.isPresent()) {
            UserDTO responseDTO = mapper.asUserDTO(user.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserPaginatedDTO> searchUsers(
            Integer page, Integer limit, List<String> sort, SearchCriteriaDTO reqBody) {
        log.debug("REST request to searchUsers: {}, {}, {}, {}", page, limit, sort, reqBody);
        var input = mapper.asSearchCriteria(reqBody);
        var userPage = userService.searchUsers(input, pageOf(page, limit, sort));
        var responseDTO = mapper.asUserPaginatedDTO(userPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<UserPaginatedDTO> listUsers(Integer page, Integer limit, List<String> sort) {
        log.debug("REST request to listUsers: {}, {}, {}", page, limit, sort);
        var userPage = userService.listUsers(pageOf(page, limit, sort));
        var responseDTO = mapper.asUserPaginatedDTO(userPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null
                ? Sort.by(sort.stream()
                        .map(sortParam -> {
                            String[] parts = sortParam.split(":");
                            String property = parts[0];
                            Sort.Direction direction =
                                    parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
                            return new Sort.Order(direction, property);
                        })
                        .toList())
                : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
