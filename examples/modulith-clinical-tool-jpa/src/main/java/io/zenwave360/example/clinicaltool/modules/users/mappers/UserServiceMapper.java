package io.zenwave360.example.clinicaltool.modules.users.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface UserServiceMapper {

    UserServiceMapper INSTANCE = Mappers.getMapper(UserServiceMapper.class);

    // input mappings
    // SearchCriterianull-User searchUsers
    User asUser(SearchCriteria input);

    @Mapping(target = "id", ignore = true)
    User update(@MappingTarget User entity, SearchCriteria input);
    // User-User updateUser
    User asUser(User input);

    @Mapping(target = "id", ignore = true)
    User update(@MappingTarget User entity, User input);
    // output mappings
}
