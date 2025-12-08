package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

/** Enum for IdentityDocumentType. */
enum class IdentityDocumentType(val value: Int) {

    DNI(1),
    NIE(2),
    PASSPORT(3);

    companion object {
        fun fromValue(value: Int): IdentityDocumentType? {
            return values().find { it.value == value }
        }
    }

    @Converter
    class IdentityDocumentTypeConverter : AttributeConverter<IdentityDocumentType, Int> {
        override fun convertToDatabaseColumn(attribute: IdentityDocumentType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): IdentityDocumentType? {
            return if (dbData == null) null else IdentityDocumentType.fromValue(dbData)
        }
    }
}
