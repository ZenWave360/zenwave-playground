package io.zenwave360.example.applicantscoring.domain;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
* 
*/
@lombok.Getter @lombok.Setter
@Embeddable
public  class BalanceAtBank  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;


  @Column(name = "balance_at_bank" )
    private BigDecimal balanceAtBank ;







}
