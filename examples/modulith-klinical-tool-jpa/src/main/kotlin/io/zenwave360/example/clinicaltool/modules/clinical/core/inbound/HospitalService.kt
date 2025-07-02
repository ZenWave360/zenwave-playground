package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [Hospital, Doctor].
 */
@org.springframework.modulith.NamedInterface("HospitalService")
interface HospitalService {


         /**
      * 
      *
      */

     fun getHospital(id: Long): Optional<Hospital>


         /**
      * 
      *
      */

     fun createHospital(input: Hospital): Hospital


         /**
      * 
      *
      */

     fun updateHospital(id: Long, input: Hospital): Optional<Hospital>


         /**
      * 
      *
      */

     fun listHospitals(): List<Hospital>


         /**
      * 
      *
      * With Events: [DoctorCreated].

      */

     fun createDoctor(input: Doctor): Doctor


         /**
      * 
      *
      */

     fun updateDoctor(id: Long, input: Doctor): Optional<Doctor>


         /**
      * 
      *
      */

     fun getDoctor(id: Long): Optional<Doctor>


         /**
      * 
      *
      */

     fun listDoctors(): List<Doctor>


         /**
      * 
      *
      */

     fun listHospitalDoctors(hospitalId: Long): List<Doctor>


         /**
      * 
      *
      */

     fun listHospitalPatients(hospitalId: Long): List<PatientHospital>



}
