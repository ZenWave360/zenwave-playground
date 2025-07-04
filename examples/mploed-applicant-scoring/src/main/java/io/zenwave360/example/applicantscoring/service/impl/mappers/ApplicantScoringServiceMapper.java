package io.zenwave360.example.applicantscoring.service.impl.mappers;

import io.zenwave360.example.applicantscoring.service.impl.mappers.BaseMapper;
import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;

import org.mapstruct.AfterMapping;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = { BaseMapper.class })
public interface ApplicantScoringServiceMapper {

    ApplicantScoringServiceMapper INSTANCE = Mappers.getMapper(ApplicantScoringServiceMapper.class);

// input mappings
    // ApplicationNumberInputnull-ApplicantScoring createApplicantScoring
        ApplicantScoring asApplicantScoring(ApplicationNumberInput input);
    @Mapping(target = "id", ignore = true)ApplicantScoring update(@MappingTarget ApplicantScoring entity, ApplicationNumberInput input);
    // CityInputnull-ApplicantScoring updateCity
        ApplicantScoring asApplicantScoring(CityInput input);
    @Mapping(target = "id", ignore = true)ApplicantScoring update(@MappingTarget ApplicantScoring entity, CityInput input);
    // BalanceAtBankInputnull-ApplicantScoring updateBalanceAtBank
        ApplicantScoring asApplicantScoring(BalanceAtBankInput input);
    @Mapping(target = "id", ignore = true)ApplicantScoring update(@MappingTarget ApplicantScoring entity, BalanceAtBankInput input);
    // ApplicantScoringInputnull-ApplicantScoring updateApplicantScoring
        ApplicantScoring asApplicantScoring(ApplicantScoringInput input);
    @Mapping(target = "id", ignore = true)ApplicantScoring update(@MappingTarget ApplicantScoring entity, ApplicantScoringInput input);
// output mappings
}
