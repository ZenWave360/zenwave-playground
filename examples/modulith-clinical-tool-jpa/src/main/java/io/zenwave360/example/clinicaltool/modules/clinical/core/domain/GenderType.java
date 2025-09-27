package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

/**
 * Enum for GenderType.
 */
public enum GenderType {
    MALE(1),
    FEMALE(2),
    OTHER(3),
    ;
    private final Integer value;

    private GenderType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static GenderType fromValue(Integer value) {
        return Arrays.stream(GenderType.values())
                .filter(e -> e.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    @Converter
    static class GenderTypeConverter implements AttributeConverter<GenderType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(GenderType attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.value;
        }

        @Override
        public GenderType convertToEntityAttribute(Integer dbData) {
            return GenderType.fromValue(dbData);
        }
    }
}
