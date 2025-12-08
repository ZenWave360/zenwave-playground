package io.zenwave360.example.clinicaltool.modules.masterdata.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

/**  */
@Entity
@Table(name = "master_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class MasterData(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @NotNull
    @Column(name = "type", nullable = false)
    @Convert(converter = MasterDataType.MasterDataTypeConverter::class)
    @org.hibernate.annotations.NaturalId
    var type: MasterDataType? = null,
    @NotNull
    @Size(max = 254)
    @Column(name = "key", nullable = false, length = 254)
    @org.hibernate.annotations.NaturalId
    var key: String? = null,
    @NotNull @Size(max = 254) @Column(name = "value", nullable = false, length = 254) var value: String? = null,
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "translations")
    var translations: MutableList<MasterDataTranslation> = mutableListOf(),
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MasterData) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
