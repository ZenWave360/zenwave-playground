package io.zenwave360.example.clinicaltool.modules.clinical.config


import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*


import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.*



import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*


import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.events.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {



    private val eventPublisher = InMemoryEventPublisher()
    protected val hospitalService = HospitalServiceImpl(hospitalRepository(), doctorRepository(), eventPublisher
        )
    protected val patientsService = PatientsServiceImpl(patientRepository(), provisionalPatientRepository(), eventPublisher
        )
    @Bean
    open fun <T : HospitalService> hospitalService(): T {
        return hospitalService as T
    }
    @Bean
    open fun <T : PatientsService> patientsService(): T {
        return patientsService as T
    }


	companion object {
		var _hospitals: List<Hospital>? = null

		var _doctors: List<Doctor>? = null

		var _patients: List<Patient>? = null

		var _provisionalPatients: List<ProvisionalPatient>? = null
	}

	fun reloadTestData() {

		val testDataLoader = TestDataLoader(listOf(Hospital::class.java, Doctor::class.java, Patient::class.java, GeneralInfo::class.java, HealthInsuranceInfo::class.java, ProvisionalPatient::class.java, PatientAddress::class.java, HospitalAddress::class.java, PatientWearable::class.java, MedicalContact::class.java, PersonalContact::class.java))
		val hospitals = _hospitals ?: testDataLoader.loadCollectionTestDataAsObjects(Hospital::class.java)
		hospitalRepository().deleteAll()
		hospitalRepository().saveAll(hospitals)
		val doctors = _doctors ?: testDataLoader.loadCollectionTestDataAsObjects(Doctor::class.java)
		doctorRepository().deleteAll()
		doctorRepository().saveAll(doctors)
		val patients = _patients ?: testDataLoader.loadCollectionTestDataAsObjects(Patient::class.java)
		patientRepository().deleteAll()
		patientRepository().saveAll(patients)
		val provisionalPatients = _provisionalPatients ?: testDataLoader.loadCollectionTestDataAsObjects(ProvisionalPatient::class.java)
		provisionalPatientRepository().deleteAll()
		provisionalPatientRepository().saveAll(provisionalPatients)
        eventPublisher.getEvents().clear()
	}
        @Bean
        fun eventPublisher(): InMemoryEventPublisher {
        return eventPublisher
        }
}
