package io.example.asyncapi.provider.clinicaltool.modules.surveys.domain
    import jakarta.persistence.AttributeConverter
    import jakarta.persistence.Converter

/**
* Enum for QuestionType.
*/
enum class QuestionType(val value: Int) {

    YES_NO(1),
    MULTIPLE_SELECTION(2),
    SINGLE_SELECTION(3),
    TEXT(4),
    NUMBER(5),
    DECIMAL(6),
    RANGE(7)
;
    companion object {
    fun fromValue(value: Int): QuestionType? {
    return values().find { it.value == value }
    }
    }
        @Converter
        class QuestionTypeConverter : AttributeConverter<QuestionType, Int> {
        override fun convertToDatabaseColumn(attribute: QuestionType?): Int? {
        return attribute?.value
        }

        override fun convertToEntityAttribute(dbData: Int?): QuestionType? {
        return if (dbData == null) null else QuestionType.fromValue(dbData)
        }
        }
}

