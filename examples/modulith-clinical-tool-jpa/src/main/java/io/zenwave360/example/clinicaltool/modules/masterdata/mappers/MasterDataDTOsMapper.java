package io.zenwave360.example.clinicaltool.modules.masterdata.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = BaseMapper.class)
public interface MasterDataDTOsMapper {

    MasterDataDTOsMapper INSTANCE = Mappers.getMapper(MasterDataDTOsMapper.class);

    // request mappings
    MasterData asMasterData(MasterDataDTO dto);

    // response mappings

    MasterDataDTO asMasterDataDTO(MasterData entity);

    List<MasterDataKeyValueDTO> asMasterDataKeyValueDTOList(List<MasterDataKeyValue> entityList);

    List<MasterDataDTO> asMasterDataDTOList(List<MasterData> entityList);

    MasterDataPaginatedDTO asMasterDataPaginatedDTO(Page<MasterData> page);

    default Page<MasterDataDTO> asMasterDataDTOPage(Page<MasterData> page) {
        return page.map(this::asMasterDataDTO);
    }
}
