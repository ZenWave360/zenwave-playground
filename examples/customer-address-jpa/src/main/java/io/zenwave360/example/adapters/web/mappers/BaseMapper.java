package io.zenwave360.example.adapters.web.mappers;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@Mapper
public interface BaseMapper {

    default Instant asInstant(OffsetDateTime date) {
        return date != null? date.toInstant() : null;
    }

    default OffsetDateTime asOffsetDateTime(Instant date) {
        return date != null? OffsetDateTime.ofInstant(date, ZoneOffset.UTC) : null;
    }

    default LocalDateTime map(OffsetDateTime value) {
        return value != null ? value.toLocalDateTime() : null;
    }

    default OffsetDateTime map(LocalDateTime value) {
        return value != null ? OffsetDateTime.of(value, ZoneOffset.UTC) : null;
    }

    default Resource map(byte[] value) {
        return new ByteArrayResource(value);
    }
}
