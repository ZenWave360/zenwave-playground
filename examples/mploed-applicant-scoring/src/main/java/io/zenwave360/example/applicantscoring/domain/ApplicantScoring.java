package io.zenwave360.example.applicantscoring.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "applicant_scoring")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
public class ApplicantScoring implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Integer version;

    @Embedded
    @AttributeOverride(name = "applicationNumber", column = @Column(name = "application_number_applicationNumber"))
    private ApplicationNumber applicationNumber;

    @Embedded
    @AttributeOverride(name = "city", column = @Column(name = "city_city"))
    private City city;

    @Embedded
    @AttributeOverride(name = "balanceAtBank", column = @Column(name = "balance_at_bank_balanceAtBank"))
    private BalanceAtBank balanceAtBank;

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
        if (!(o instanceof ApplicantScoring)) {
            return false;
        }
        ApplicantScoring other = (ApplicantScoring) o;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
