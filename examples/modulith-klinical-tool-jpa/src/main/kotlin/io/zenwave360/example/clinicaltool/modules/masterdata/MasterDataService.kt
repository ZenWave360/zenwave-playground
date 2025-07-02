package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [MasterData].
 */
@org.springframework.modulith.NamedInterface("MasterDataService")
interface MasterDataService {


         /**
      * 
      *
      */

     fun createMasterData(input: MasterData): MasterData


         /**
      * 
      *
      */

     fun getMasterData(id: Long): Optional<MasterData>


         /**
      * 
      *
      */

     fun updateMasterData(id: Long, input: MasterData): Optional<MasterData>


         /**
      * 
      *
      */

     fun listMasterData(pageable: Pageable): Page<MasterData>


         /**
      * 
      *
      */

     fun deleteMasterData(id: Long): Unit


         /**
      * 
      *
      */

     fun listMasterDataOfType(type: MasterDataType, lang: String): List<MasterDataKeyValue>



}
