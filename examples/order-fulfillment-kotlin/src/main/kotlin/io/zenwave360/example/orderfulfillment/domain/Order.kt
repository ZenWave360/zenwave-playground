package io.zenwave360.example.orderfulfillment.domain

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
@Table(name = "order_table")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener::class)
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,

    /** prefix doc comment */
    @NotNull
    @Size(max = 36)
    @Column(name = "order_number", nullable = false, unique = true, length = 36)
    @org.hibernate.annotations.NaturalId
    var orderNumber: String? = null,

    /** suffix inline doc comment */
    @NotNull
    @Column(name = "status", nullable = false)
    @Convert(converter = OrderStatus.OrderStatusConverter::class)
    var status: OrderStatus? = null,
    @NotNull @Column(name = "total_amount", nullable = false) var totalAmount: BigDecimal? = null,
    @NotNull @Size(max = 3) @Column(name = "currency", nullable = false, length = 3) var currency: String? = null,
    @Column(name = "payment_reference") var paymentReference: String? = null,

    /** Order lines stored as JSON for simplicity in the demo */
    @Column(name = "tracking_number") var trackingNumber: String? = null,
    @Size(min = 1)
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "items")
    var items: MutableList<OrderItem> = mutableListOf(),
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
        if (other !is Order) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
