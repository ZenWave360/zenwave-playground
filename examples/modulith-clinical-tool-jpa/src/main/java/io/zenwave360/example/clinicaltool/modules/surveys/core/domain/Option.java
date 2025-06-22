package io.zenwave360.example.clinicaltool.modules.surveys.core.domain;

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
// @Embeddable // json embedded
public  class Option  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull @Size(max = 254)@Column(name = "name" , nullable = false, length = 254)
    private String name ;

  @Embedded
    @AttributeOverride( name = "lang", column = @Column(name = "translations_lang"))

    @AttributeOverride( name = "text", column = @Column(name = "translations_text"))
    private List<OptionTranslation> translations = new ArrayList<>();






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Option)){
      return false;
    }
    Option other = (Option) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
