package io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;

/**
* 
*/
@lombok.Getter @lombok.Setter
public  class DoctorCreated  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;


    
    private Doctor doctor;





}
