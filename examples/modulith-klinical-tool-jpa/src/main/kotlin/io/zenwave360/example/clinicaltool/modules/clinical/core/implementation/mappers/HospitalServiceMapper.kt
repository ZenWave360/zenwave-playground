package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface HospitalServiceMapper {

    companion object {
        val INSTANCE: HospitalServiceMapper = Mappers.getMapper(HospitalServiceMapper::class.java)
    }

    // input mappings
    // Doctor-Doctor updateDoctor

    fun asDoctor(input: Doctor): Doctor

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Doctor, input: Doctor): Doctor

    // HospitalIdnull-Doctor listHospitalDoctors
    fun asDoctor(hospitalId: Long): Doctor {
        return Doctor().apply {
            // TODO: implement this method
            // this.hospitalId = hospitalId
        }
    }

    fun update(entity: Doctor, hospitalId: Long): Doctor {
        return entity.apply {
            // TODO: implement this method
            // this.hospitalId = hospitalId
        }
    }

    // Hospital-Hospital updateHospital

    fun asHospital(input: Hospital): Hospital

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Hospital, input: Hospital): Hospital

    // HospitalIdnull-Hospital listHospitalPatients
    fun asHospital(hospitalId: Long): Hospital {
        return Hospital().apply {
            // TODO: implement this method
            // this.hospitalId = hospitalId
        }
    }

    fun update(entity: Hospital, hospitalId: Long): Hospital {
        return entity.apply {
            // TODO: implement this method
            // this.hospitalId = hospitalId
        }
    }

    // output mappings
    // Hospital-PatientHospital listHospitalPatients
    fun asPatientHospital(entity: Hospital): PatientHospital

    fun asPatientHospitalList(entity: List<Hospital>): List<PatientHospital>
}
