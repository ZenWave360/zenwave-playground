package io.zenwave360.example.clinicaltool.modules.surveys.core.domain;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
* 
*/
@lombok.Getter @lombok.Setter
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener.class)
public  class Question  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

    /**
    * Unique identifier for the question
    */
  @NotNull @Size(max = 254)@Column(name = "name" , nullable = false, unique = true, length = 254)
    private String name ;

  @NotNull@Column(name = "question_type" , nullable = false)
            @Convert(converter = QuestionType.QuestionTypeConverter.class)
    
    private QuestionType questionType ;

  @Column(name = "required" )
    private boolean required = true;

  @Column(name = "range_start" )
    private Integer rangeStart ;

  @Column(name = "range_end" )
    private Integer rangeEnd ;

  
	@org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)@Column(name = "translations")
    private List<QuestionTranslation> translations = new ArrayList<>();

  
	@org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)@Column(name = "options")
    private List<Option> options = new ArrayList<>();

  @Column(name = "include_other" )
    private Boolean includeOther = false;



    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected String createdBy;
    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    protected LocalDateTime createdDate;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    protected LocalDateTime lastModifiedDate;



/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Question)){
      return false;
    }
    Question other = (Question) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
