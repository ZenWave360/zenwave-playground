package io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface TermsAndConditionsDTOsMapper {

    TermsAndConditionsDTOsMapper INSTANCE = Mappers.getMapper(TermsAndConditionsDTOsMapper.class);

    // request mappings
        TermsAndConditions asTermsAndConditions(TermsAndConditionsDTO dto);
        AcceptedTermsAndConditionsInput asAcceptedTermsAndConditionsInput(AcceptedTermsAndConditionsInputDTO dto);

    // response mappings
    
    TermsAndConditionsDTO asTermsAndConditionsDTO(TermsAndConditions entity);
    
    
    List<TermsAndConditionsDTO> asTermsAndConditionsDTOList(List<TermsAndConditions> entityList);


}
