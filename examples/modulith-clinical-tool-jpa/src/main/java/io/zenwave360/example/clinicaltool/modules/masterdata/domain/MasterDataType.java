package io.zenwave360.example.clinicaltool.modules.masterdata.domain;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

/**
 * Enum for MasterDataType.
 */
public enum MasterDataType {
    
    GENDER(1),
    ID_DOCUMENT_TYPE(2),
    COUNTRY(3),
    INSURANCE_COMPANY(4),
    MEDICAL_AREA(5),
    ;
    private final Integer value;

    private MasterDataType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static MasterDataType fromValue(Integer value) {
        return Arrays.stream(MasterDataType.values())
                .filter(e -> e.value.equals(value)).findFirst().orElse(null);
    }
    @Converter
    static class MasterDataTypeConverter implements AttributeConverter<MasterDataType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(MasterDataType attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.value;
        }

        @Override
        public MasterDataType convertToEntityAttribute(Integer dbData) {
            return MasterDataType.fromValue(dbData);
        }

    }
}

