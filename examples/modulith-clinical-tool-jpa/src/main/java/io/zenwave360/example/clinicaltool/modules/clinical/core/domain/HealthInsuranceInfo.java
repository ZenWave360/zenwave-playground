package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

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
public  class HealthInsuranceInfo  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull @Size(max = 100)@Column(name = "insurance_company_id" , nullable = false, length = 100)
    private String insuranceCompanyId ;

  @NotNull @Size(max = 20)@Column(name = "insurance_card_number" , nullable = false, length = 20)
    private String insuranceCardNumber ;






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HealthInsuranceInfo)){
      return false;
    }
    HealthInsuranceInfo other = (HealthInsuranceInfo) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
