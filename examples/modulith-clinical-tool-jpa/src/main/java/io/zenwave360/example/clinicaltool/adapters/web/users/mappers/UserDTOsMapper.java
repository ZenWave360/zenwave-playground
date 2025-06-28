package io.zenwave360.example.clinicaltool.adapters.web.users.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.users.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.users.dtos.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface UserDTOsMapper {

    UserDTOsMapper INSTANCE = Mappers.getMapper(UserDTOsMapper.class);

    // request mappings
        SearchCriteria asSearchCriteria(SearchCriteriaDTO dto);
        User asUser(UserDTO dto);

    // response mappings
    
    UserDTO asUserDTO(User entity);
    
    
    List<UserDTO> asUserDTOList(List<User> entityList);
    UserPaginatedDTO asUserPaginatedDTO(Page<User> page);
    default Page<UserDTO> asUserDTOPage(Page<User> page) {
      return page.map(this::asUserDTO);
    }


}
