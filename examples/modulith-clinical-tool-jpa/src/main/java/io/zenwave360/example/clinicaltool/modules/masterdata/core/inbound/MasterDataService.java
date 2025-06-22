package io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound;

import io.zenwave360.example.clinicaltool.modules.masterdata.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [MasterData].
 */
public interface MasterDataService {

     /**
      * 
      *
      */

    public MasterData createMasterData(MasterData input)
;
     /**
      * 
      *
      */

    public Optional<MasterData> getMasterData(Long id)
;
     /**
      * 
      *
      */

    public Optional<MasterData> updateMasterData(Long id, MasterData input)
;
     /**
      * 
      *
      */

    public Page<MasterData> listMasterData(Pageable pageable)
;
     /**
      * 
      *
      */

    public void deleteMasterData(Long id)
;
     /**
      * 
      *
      */

    public List<MasterDataKeyValue> listMasterDataOfType(MasterDataType type, String lang)
;


}
