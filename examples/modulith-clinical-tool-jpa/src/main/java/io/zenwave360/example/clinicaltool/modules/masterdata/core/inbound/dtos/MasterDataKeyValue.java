package io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.dtos;

import io.zenwave360.example.clinicaltool.modules.masterdata.core.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * MasterDataKeyValue.
 */
@lombok.Getter @lombok.Setter
public  class MasterDataKeyValue  implements Serializable {


    
    private String key ;

    
    private String value ;





}
