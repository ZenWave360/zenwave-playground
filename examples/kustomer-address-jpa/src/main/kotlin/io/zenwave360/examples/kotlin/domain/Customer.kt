package io.zenwave360.examples.kotlin.domain

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
* 
*/
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener::class)
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    /**
    * Customer name
    */
    @NotNull @Size(max = 254)@Column(name = "name", nullable = false, length = 254)
    var name: String?  = null,

    @NotNull @Size(max = 254) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")@Column(name = "email", nullable = false, length = 254)
    var email: String?  = null,

    /**
    * Customer Addresses can be stored in a JSON column in the database.
    */
    @Size(min = 1, max = 5)
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "addresses")
    var addresses: MutableList<Address> = mutableListOf()



    ,
        
    @NotNull @Size(max = 3)
    
@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var paymentMethods: Set<PaymentMethod> = mutableSetOf()
    
)  : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    var lastModifiedDate: LocalDateTime? = null
    // manage relationships
    fun addPaymentMethods(paymentMethods: PaymentMethod): Customer {
        this.paymentMethods += paymentMethods
        paymentMethods.customer = this
        return this
    }

    fun removePaymentMethods(paymentMethods: PaymentMethod): Customer {
        this.paymentMethods -= paymentMethods
        paymentMethods.customer = null
        return this
    }


override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
