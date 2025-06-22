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
@Table(name = "hospital")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class Hospital  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull @Size(max = 254)@Column(name = "name" , nullable = false, unique = true, length = 254)
    private String name ;

    /**
    * Primary language of the hospital
    */
  @NotNull @Size(max = 3)@Column(name = "lang" , nullable = false, length = 3)
    private String lang ;

    /**
    * ECT (Europe/Madrid)
    */
  @NotNull @Size(max = 3)@Column(name = "timezone" , nullable = false, length = 3)
    private String timezone ;






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Hospital)){
      return false;
    }
    Hospital other = (Hospital) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
