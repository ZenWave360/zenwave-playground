package io.zenwave360.example.clinicaltool.modules.users.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface UserServiceMapper {

    companion object {
        val INSTANCE: UserServiceMapper = Mappers.getMapper(UserServiceMapper::class.java)
    }

// input mappings
    // SearchCriterianull-User searchUsers
    fun asUser(input: SearchCriteria): User
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: User, input: SearchCriteria): User
    // User-User updateUser
    fun asUser(input: User): User
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: User, input: User): User
// output mappings
}
