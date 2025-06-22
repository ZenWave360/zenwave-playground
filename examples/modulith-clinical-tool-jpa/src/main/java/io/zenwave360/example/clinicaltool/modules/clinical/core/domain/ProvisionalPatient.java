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
@Entity
@Table(name = "provisional_patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class ProvisionalPatient  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull @Size(max = 20)@Column(name = "phone_number" , nullable = false, length = 20) @org.hibernate.annotations.NaturalId 
    private String phoneNumber ;

  @NotNull @Size(max = 100)@Column(name = "his_number" , nullable = false, length = 100) @org.hibernate.annotations.NaturalId 
    private String hisNumber ;

  
	@org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)@Column(name = "patient")
    private Patient patient ;






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProvisionalPatient)){
      return false;
    }
    ProvisionalPatient other = (ProvisionalPatient) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
