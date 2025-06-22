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
@Table(name = "terms_and_conditions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class TermsAndConditions  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull@Column(name = "content" , nullable = false) @Lob 
    private String content ;

    /**
    * language code
    */
  @NotNull @Size(max = 3)@Column(name = "lang" , nullable = false, length = 3)
    private String lang ;

    /**
    * Arbitrary version string
    */
  @NotNull@Column(name = "content_version" , nullable = false, unique = true)
    private String contentVersion ;

    /**
    * Date when the terms and conditions are valid (inclusive)
    */
  @NotNull@Column(name = "start_date" , nullable = false, unique = true)
    private LocalDate startDate ;






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TermsAndConditions)){
      return false;
    }
    TermsAndConditions other = (TermsAndConditions) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
