package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

/**
 * Enum for PatientRelationshipType.
 */
public enum PatientRelationshipType {
    SON_DAUGTHER(1),
    CARE_GIVER(2),
    OTHER(3),
    ;
    private final Integer value;

    private PatientRelationshipType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static PatientRelationshipType fromValue(Integer value) {
        return Arrays.stream(PatientRelationshipType.values())
                .filter(e -> e.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    @Converter
    static class PatientRelationshipTypeConverter implements AttributeConverter<PatientRelationshipType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PatientRelationshipType attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.value;
        }

        @Override
        public PatientRelationshipType convertToEntityAttribute(Integer dbData) {
            return PatientRelationshipType.fromValue(dbData);
        }
    }
}
