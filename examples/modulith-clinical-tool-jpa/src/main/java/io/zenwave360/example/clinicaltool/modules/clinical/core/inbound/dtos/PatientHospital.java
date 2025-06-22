package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * PatientHospital.
 */
@lombok.Getter @lombok.Setter
public  class PatientHospital  implements Serializable {


    
    private Long patientId ;

    
    private Long hospitalId ;

    
    private String hisNumber ;

    
    private String fullName ;

    
    private GenderType gender ;

    
    private String dni ;

    
    private LocalDate birthDate ;

    
    private String phone ;

    
    private String email ;

    
    private String insuranceCardNumber ;

    
    private String address ;














}
