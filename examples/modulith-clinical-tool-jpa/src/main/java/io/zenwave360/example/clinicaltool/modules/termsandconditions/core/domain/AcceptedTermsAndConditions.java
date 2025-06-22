package io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain;

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
@Entity
@Table(name = "accepted_terms_and_conditions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class AcceptedTermsAndConditions  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull@Column(name = "user_id" , nullable = false) @org.hibernate.annotations.NaturalId 
    private Long userId ;

  @NotNull@Column(name = "terms_and_conditions_id" , nullable = false)
    private Long termsAndConditionsId ;

  @NotNull@Column(name = "accepted_date" , nullable = false)
    private Instant acceptedDate ;






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AcceptedTermsAndConditions)){
      return false;
    }
    AcceptedTermsAndConditions other = (AcceptedTermsAndConditions) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
