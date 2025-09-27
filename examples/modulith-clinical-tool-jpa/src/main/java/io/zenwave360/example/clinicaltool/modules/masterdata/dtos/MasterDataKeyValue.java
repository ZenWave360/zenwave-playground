package io.zenwave360.example.clinicaltool.modules.masterdata.dtos;

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * MasterDataKeyValue.
 */
@lombok.Getter
@lombok.Setter
public class MasterDataKeyValue implements Serializable {

    private String key;

    private String value;
}
