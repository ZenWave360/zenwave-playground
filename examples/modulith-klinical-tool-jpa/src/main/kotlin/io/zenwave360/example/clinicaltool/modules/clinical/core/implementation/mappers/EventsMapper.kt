package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers


@Mapper(uses = [BaseMapper::class])
interface EventsMapper {

    companion object {
        val INSTANCE: EventsMapper = Mappers.getMapper(EventsMapper::class.java)
    }
    fun asPatientCreated(input: Patient): io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.PatientCreated
    fun asDoctorCreated(input: Doctor): io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.DoctorCreated
}
