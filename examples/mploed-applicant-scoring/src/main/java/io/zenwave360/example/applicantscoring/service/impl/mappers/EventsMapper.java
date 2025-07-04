package io.zenwave360.example.applicantscoring.service.impl.mappers;

import io.zenwave360.example.applicantscoring.service.impl.mappers.BaseMapper;
import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(uses = { BaseMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);
    io.zenwave360.example.applicantscoring.events.dtos.ApplicantScoringEvent asApplicantScoringEvent(ApplicantScoring applicantScoring);
}
