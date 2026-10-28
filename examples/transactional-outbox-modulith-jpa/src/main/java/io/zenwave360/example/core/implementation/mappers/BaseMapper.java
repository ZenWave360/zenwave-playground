package io.zenwave360.example.core.implementation.mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;

@Mapper
public interface BaseMapper {

    default Instant asInstant(OffsetDateTime date) {
        return date != null ? date.toInstant() : null;
    }

    default OffsetDateTime asOffsetDateTime(Instant date) {
        return date != null ? OffsetDateTime.ofInstant(date, ZoneOffset.UTC) : null;
    }

    default LocalDateTime map(OffsetDateTime value) {
        return value != null ? value.toLocalDateTime() : null;
    }

    default OffsetDateTime map(LocalDateTime value) {
        return value != null ? OffsetDateTime.of(value, ZoneOffset.UTC) : null;
    }
}
