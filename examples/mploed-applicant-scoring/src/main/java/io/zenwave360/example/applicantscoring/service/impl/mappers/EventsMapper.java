package io.zenwave360.example.applicantscoring.service.impl.mappers;

import io.zenwave360.example.applicantscoring.domain.ApplicantScoring;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "version", source = "version")
    @Mapping(target = "applicationNumber", source = "applicationNumber.applicationNumber")
    @Mapping(target = "city", source = "city.city")
    @Mapping(target = "balanceAtBank", source = "balanceAtBank.balanceAtBank")
    io.zenwave360.example.applicantscoring.events.dtos.ApplicantScoringEvent asApplicantScoringEvent(
            ApplicantScoring applicantScoring);
}
