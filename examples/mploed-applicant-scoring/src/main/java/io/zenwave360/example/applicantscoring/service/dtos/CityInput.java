package io.zenwave360.example.applicantscoring.service.dtos;

import io.zenwave360.example.applicantscoring.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * CityInput.
 */
@lombok.Getter @lombok.Setter
public  class CityInput  implements Serializable {


    @NotNull @Size(min = 3, max = 254)
    private String city ;




}
