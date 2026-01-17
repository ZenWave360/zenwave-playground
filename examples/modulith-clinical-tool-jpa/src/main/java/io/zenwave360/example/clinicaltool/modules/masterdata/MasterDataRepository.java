package io.zenwave360.example.clinicaltool.modules.masterdata;

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MasterData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterDataRepository extends JpaRepository<MasterData, Long> {
    java.util.Optional<MasterData> findByTypeAndKey(MasterDataType type, String key);

    List<MasterData> findByType(MasterDataType type);
}
