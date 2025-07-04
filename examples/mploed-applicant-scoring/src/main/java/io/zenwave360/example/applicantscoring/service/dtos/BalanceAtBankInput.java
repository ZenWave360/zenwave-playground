package io.zenwave360.example.applicantscoring.service.dtos;

import io.zenwave360.example.applicantscoring.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * BalanceAtBankInput.
 */
@lombok.Getter @lombok.Setter
public  class BalanceAtBankInput  implements Serializable {


    @NotNull
    private BigDecimal balanceAtBank ;




}
