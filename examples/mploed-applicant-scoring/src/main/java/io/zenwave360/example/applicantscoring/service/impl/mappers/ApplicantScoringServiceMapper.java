package io.zenwave360.example.applicantscoring.service.impl.mappers;

import io.zenwave360.example.applicantscoring.domain.ApplicantScoring;
import io.zenwave360.example.applicantscoring.service.dtos.ApplicantScoringInput;
import io.zenwave360.example.applicantscoring.service.dtos.ApplicationNumberInput;
import io.zenwave360.example.applicantscoring.service.dtos.BalanceAtBankInput;
import io.zenwave360.example.applicantscoring.service.dtos.CityInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { BaseMapper.class })
public interface ApplicantScoringServiceMapper {

    ApplicantScoringServiceMapper INSTANCE = Mappers.getMapper(ApplicantScoringServiceMapper.class);

    // input mappings
    // ApplicationNumberInputnull-ApplicantScoring createApplicantScoring
    @Mapping(target = "applicationNumber.applicationNumber", source = "applicationNumber")
    ApplicantScoring asApplicantScoring(ApplicationNumberInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationNumber.applicationNumber", source = "applicationNumber")
    ApplicantScoring update(@MappingTarget ApplicantScoring entity, ApplicationNumberInput input);

    // CityInputnull-ApplicantScoring updateCity
    @Mapping(target = "city.city", source = "city")
    ApplicantScoring asApplicantScoring(CityInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city.city", source = "city")
    ApplicantScoring update(@MappingTarget ApplicantScoring entity, CityInput input);

    // BalanceAtBankInputnull-ApplicantScoring updateBalanceAtBank
    @Mapping(target = "balanceAtBank.balanceAtBank", source = "balanceAtBank")
    ApplicantScoring asApplicantScoring(BalanceAtBankInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balanceAtBank.balanceAtBank", source = "balanceAtBank")
    ApplicantScoring update(@MappingTarget ApplicantScoring entity, BalanceAtBankInput input);

    // ApplicantScoringInputnull-ApplicantScoring updateApplicantScoring
    @Mapping(target = "applicationNumber.applicationNumber", source = "applicationNumber")
    @Mapping(target = "city.city", source = "city")
    @Mapping(target = "balanceAtBank.balanceAtBank", source = "balanceAtBank")
    ApplicantScoring asApplicantScoring(ApplicantScoringInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationNumber.applicationNumber", source = "applicationNumber")
    @Mapping(target = "city.city", source = "city")
    @Mapping(target = "balanceAtBank.balanceAtBank", source = "balanceAtBank")
    ApplicantScoring update(@MappingTarget ApplicantScoring entity, ApplicantScoringInput input);
    // output mappings
}
