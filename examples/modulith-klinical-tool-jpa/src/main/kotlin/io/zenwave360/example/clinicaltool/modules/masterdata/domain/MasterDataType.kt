package io.zenwave360.example.clinicaltool.modules.masterdata.domain

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

/** Enum for MasterDataType. */
enum class MasterDataType(val value: Int) {

    GENDER(1),
    ID_DOCUMENT_TYPE(2),
    COUNTRY(3),
    INSURANCE_COMPANY(4),
    MEDICAL_AREA(5);

    companion object {
        fun fromValue(value: Int): MasterDataType? {
            return values().find { it.value == value }
        }
    }

    @Converter
    class MasterDataTypeConverter : AttributeConverter<MasterDataType, Int> {
        override fun convertToDatabaseColumn(attribute: MasterDataType?): Int? {
            return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): MasterDataType? {
            return if (dbData == null) null else MasterDataType.fromValue(dbData)
        }
    }
}
