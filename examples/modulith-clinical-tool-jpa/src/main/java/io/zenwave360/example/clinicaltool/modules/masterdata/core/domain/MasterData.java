package io.zenwave360.example.clinicaltool.modules.masterdata.core.domain;

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
@Table(name = "master_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class MasterData  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull@Column(name = "type" , nullable = false)
            @Convert(converter = MasterDataType.MasterDataTypeConverter.class)
     @org.hibernate.annotations.NaturalId 
    private MasterDataType type ;

  @NotNull @Size(max = 254)@Column(name = "key" , nullable = false, length = 254) @org.hibernate.annotations.NaturalId 
    private String key ;

  @NotNull @Size(max = 254)@Column(name = "value" , nullable = false, length = 254)
    private String value ;

  
	@org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)@Column(name = "translations")
    private List<MasterDataTranslation> translations = new ArrayList<>();






/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MasterData)){
      return false;
    }
    MasterData other = (MasterData) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
