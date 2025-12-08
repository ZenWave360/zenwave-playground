package io.zenwave360.example.clinicaltool.modules.users.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**  */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @Column(name = "name") var name: String? = null,
    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    @org.hibernate.annotations.NaturalId
    var username: String? = null,
    @NotNull @Column(name = "email", nullable = false, unique = true) var email: String? = null,
    @Column(name = "password") var password: String? = null,
    @NotNull @Column(name = "roles", nullable = false) var roles: MutableList<String> = mutableListOf(),
    @Column(name = "enabled") var enabled: Boolean? = null,
    @Column(name = "credentials_non_expired") var credentialsNonExpired: Boolean? = null,
    @Column(name = "account_non_expired") var accountNonExpired: Boolean? = null,
    @Column(name = "account_non_locked") var accountNonLocked: Boolean? = null,
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "additional_properties")
    var additionalProperties: MutableMap<String, Object>? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    @CreatedBy @Column(name = "created_by", updatable = false) var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedBy @Column(name = "last_modified_by") var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    var lastModifiedDate: LocalDateTime? = null

    override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
