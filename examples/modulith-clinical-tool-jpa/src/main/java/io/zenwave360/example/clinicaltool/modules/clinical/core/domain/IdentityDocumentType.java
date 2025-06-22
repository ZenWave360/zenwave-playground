package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

/**
 * Enum for IdentityDocumentType.
 */
public enum IdentityDocumentType {
    
    DNI(1),
    NIE(2),
    PASSPORT(3),
    ;
    private final Integer value;

    private IdentityDocumentType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static IdentityDocumentType fromValue(Integer value) {
        return Arrays.stream(IdentityDocumentType.values())
                .filter(e -> e.value.equals(value)).findFirst().orElse(null);
    }
    @Converter
    static class IdentityDocumentTypeConverter implements AttributeConverter<IdentityDocumentType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(IdentityDocumentType attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.value;
        }

        @Override
        public IdentityDocumentType convertToEntityAttribute(Integer dbData) {
            return IdentityDocumentType.fromValue(dbData);
        }

    }
}

