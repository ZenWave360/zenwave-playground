package io.zenwave360.example.clinicaltool.common.mappers

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.mapstruct.Mapper

@Mapper
interface BaseMapper {

    fun asInstant(date: OffsetDateTime?): Instant? {
        return date?.toInstant()
    }

    fun asOffsetDateTime(date: Instant?): OffsetDateTime? {
        return date?.let { OffsetDateTime.ofInstant(it, ZoneOffset.UTC) }
    }

    fun map(value: OffsetDateTime?): LocalDateTime? {
        return value?.toLocalDateTime()
    }

    fun map(value: LocalDateTime?): OffsetDateTime? {
        return value?.let { OffsetDateTime.of(it, ZoneOffset.UTC) }
    }
}
