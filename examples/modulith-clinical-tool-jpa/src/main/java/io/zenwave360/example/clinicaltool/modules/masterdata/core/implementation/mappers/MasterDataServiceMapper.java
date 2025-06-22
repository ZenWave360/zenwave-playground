package io.zenwave360.example.clinicaltool.modules.masterdata.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.dtos.*;

import org.mapstruct.AfterMapping;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = { BaseMapper.class })
public interface MasterDataServiceMapper {

    MasterDataServiceMapper INSTANCE = Mappers.getMapper(MasterDataServiceMapper.class);

// input mappings
    // MasterDataFilternull-MasterData listMasterDataOfType
        MasterData asMasterData(MasterDataType type, String lang);
    @Mapping(target = "id", ignore = true)MasterData update(@MappingTarget MasterData entity, MasterDataType type, String lang);
    // MasterData-MasterData updateMasterData
        MasterData asMasterData(MasterData input);
    @Mapping(target = "id", ignore = true)MasterData update(@MappingTarget MasterData entity, MasterData input);
// output mappings
    // MasterData-MasterDataKeyValue listMasterDataOfType
    MasterDataKeyValue asMasterDataKeyValue(MasterData entity);
        List<MasterDataKeyValue> asMasterDataKeyValueList(List<MasterData> entity);
}
