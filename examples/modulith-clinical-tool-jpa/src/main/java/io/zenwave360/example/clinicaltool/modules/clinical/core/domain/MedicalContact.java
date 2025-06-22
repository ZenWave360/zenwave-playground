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
@Table(name = "medical_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class MedicalContact  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull @Size(max = 100)@Column(name = "name" , nullable = false, length = 100)
    private String name ;

  @NotNull @Size(max = 100)@Column(name = "surname" , nullable = false, length = 100)
    private String surname ;

  @Size(max = 100)@Column(name = "surname2" , length = 100)
    private String surname2 ;

  @Size(max = 254)@Column(name = "hospital" , length = 254)
    private String hospital ;

  @Size(max = 100)@Column(name = "area" , length = 100)
    private String area ;

  @Size(max = 100)@Column(name = "job_position" , length = 100)
    private String jobPosition ;

  @NotNull @Size(max = 20)@Column(name = "phone_number" , nullable = false, length = 20)
    private String phoneNumber ;

  @NotNull @Size(max = 100) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")@Column(name = "email" , nullable = false, length = 100)
    private String email ;




  
    @NotNull
    
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "patient_id")
@com.fasterxml.jackson.annotation.JsonBackReference


    private Patient patient ;




/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MedicalContact)){
      return false;
    }
    MedicalContact other = (MedicalContact) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
