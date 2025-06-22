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
@Table(name = "survey_answers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener.class)
public  class SurveyAnswers  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @NotNull@Column(name = "survey_id" , nullable = false) @org.hibernate.annotations.NaturalId 
    private Long surveyId ;

  @NotNull@Column(name = "patient_id" , nullable = false) @org.hibernate.annotations.NaturalId 
    private Long patientId ;

  @NotNull@Column(name = "date" , nullable = false) @org.hibernate.annotations.NaturalId 
    private LocalDate date ;

  @NotNull@Column(name = "lang" , nullable = false)
    private String lang ;

  
	@org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)@Column(name = "answers")
    private List<Answer> answers = new ArrayList<>();



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
    if (!(o instanceof SurveyAnswers)){
      return false;
    }
    SurveyAnswers other = (SurveyAnswers) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
