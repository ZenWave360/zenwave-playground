package io.zenwave360.example.clinicaltool.modules.clinical.core.domain
    import jakarta.persistence.AttributeConverter
    import jakarta.persistence.Converter

/**
* Enum for PatientRelationshipType.
*/
enum class PatientRelationshipType(val value: Int) {

    SON_DAUGTHER(1),
    CARE_GIVER(2),
    OTHER(3)
;
    companion object {
    fun fromValue(value: Int): PatientRelationshipType? {
    return values().find { it.value == value }
    }
    }
        @Converter
        class PatientRelationshipTypeConverter : AttributeConverter<PatientRelationshipType, Int> {
        override fun convertToDatabaseColumn(attribute: PatientRelationshipType?): Int? {
        return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): PatientRelationshipType? {
        return if (dbData == null) null else PatientRelationshipType.fromValue(dbData)
        }
        }
}

