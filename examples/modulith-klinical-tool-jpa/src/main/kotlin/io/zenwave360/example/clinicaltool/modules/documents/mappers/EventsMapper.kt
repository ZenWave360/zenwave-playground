package io.zenwave360.example.clinicaltool.modules.documents.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers


@Mapper(uses = [BaseMapper::class])
interface EventsMapper {

    companion object {
        val INSTANCE: EventsMapper = Mappers.getMapper(EventsMapper::class.java)
    }
}
