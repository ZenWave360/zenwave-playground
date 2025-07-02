package io.zenwave360.example.clinicaltool.modules.clinical.core.domain
    import jakarta.persistence.AttributeConverter
    import jakarta.persistence.Converter

/**
* Enum for GenderType.
*/
enum class GenderType(val value: Int) {

    MALE(1),
    FEMALE(2),
    OTHER(3)
;
    companion object {
    fun fromValue(value: Int): GenderType? {
    return values().find { it.value == value }
    }
    }
        @Converter
        class GenderTypeConverter : AttributeConverter<GenderType, Int> {
        override fun convertToDatabaseColumn(attribute: GenderType?): Int? {
        return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): GenderType? {
        return if (dbData == null) null else GenderType.fromValue(dbData)
        }
        }
}

