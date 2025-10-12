package io.example.asyncapi.provider.clinicaltool.modules.users.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface UserDTOsMapper {

    companion object {
        val INSTANCE: UserDTOsMapper = Mappers.getMapper(UserDTOsMapper::class.java)
    }

    // request mappings
    fun asSearchCriteria(dto: SearchCriteriaDTO): SearchCriteria
    fun asUser(dto: UserDTO): User

    // response mappings

    fun asUserDTO(entity: User): UserDTO


    fun asUserDTOList(entityList: List<User>): List<UserDTO>
    @Mapping(target = "content", source = "content", conditionExpression = "java(page.getContent() != null)")
    fun asUserPaginatedDTO(page: Page<User>): UserPaginatedDTO
    fun asUserDTOPage(page: Page<User>): Page<UserDTO> {
        return page.map { this.asUserDTO(it) }
    }

}
