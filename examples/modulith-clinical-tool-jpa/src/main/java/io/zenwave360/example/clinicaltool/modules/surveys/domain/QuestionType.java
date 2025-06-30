package io.zenwave360.example.clinicaltool.modules.surveys.domain;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

/**
 * Enum for QuestionType.
 */
public enum QuestionType {
    
    YES_NO(1),
    MULTIPLE_SELECTION(2),
    SINGLE_SELECTION(3),
    TEXT(4),
    NUMBER(5),
    DECIMAL(6),
    RANGE(7),
    ;
    private final Integer value;

    private QuestionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static QuestionType fromValue(Integer value) {
        return Arrays.stream(QuestionType.values())
                .filter(e -> e.value.equals(value)).findFirst().orElse(null);
    }
    @Converter
    static class QuestionTypeConverter implements AttributeConverter<QuestionType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(QuestionType attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.value;
        }

        @Override
        public QuestionType convertToEntityAttribute(Integer dbData) {
            return QuestionType.fromValue(dbData);
        }

    }
}

