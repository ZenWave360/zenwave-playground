package io.zenwave360.example.clinicaltool.modules.masterdata.inmemory;

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.MasterDataRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class MasterDataRepositoryInMemory extends InMemoryJpaRepository<MasterData> implements MasterDataRepository {
    @Override
    public java.util.Optional<MasterData> findByTypeAndKey(MasterDataType type, String key) {
        return getEntities().values().stream().filter(e ->
             isSameValue(type, readField(e, "type")) && isSameValue(key, readField(e, "key")) 
        ).findFirst();
    }
}
