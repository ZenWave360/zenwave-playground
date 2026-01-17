package io.zenwave360.example.applicantscoring.web.mappers;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;
import io.zenwave360.example.applicantscoring.web.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface ApplicantScoringDTOsMapper {

    ApplicantScoringDTOsMapper INSTANCE = Mappers.getMapper(ApplicantScoringDTOsMapper.class);

    // request mappings
    ApplicantScoringInput asApplicantScoringInput(ApplicantScoringInputDTO dto);

    ApplicationNumberInput asApplicationNumberInput(ApplicationNumberInputDTO dto);

    BalanceAtBankInput asBalanceAtBankInput(BalanceAtBankInputDTO dto);

    CityInput asCityInput(CityInputDTO dto);

    // response mappings

    ApplicantScoringDTO asApplicantScoringDTO(ApplicantScoring entity);
}
