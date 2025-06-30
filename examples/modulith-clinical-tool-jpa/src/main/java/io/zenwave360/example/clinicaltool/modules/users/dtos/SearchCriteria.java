package io.zenwave360.example.clinicaltool.modules.users.dtos;

import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * SearchCriteria.
 */
@lombok.Getter @lombok.Setter
public  class SearchCriteria  implements Serializable {


    
    private String username ;

    
    private String email ;

    
    private String role ;

    
    private Boolean enabled ;

    
    private Map<String, Object> searchTerms ;








}
