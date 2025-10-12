package io.example.asyncapi.provider.clinicaltool.modules.surveys.domain

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

/**
*
*/
// @Embeddable // json embedded
data class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull @Size(max = 254)@Column(name = "name", nullable = false, length = 254)
    var name: String?  = null,

    @Embedded
    @AttributeOverride(name = "lang", column = Column(name = "translations_lang"))

    @AttributeOverride(name = "text", column = Column(name = "translations_text"))
    var translations: MutableList<OptionTranslation> = mutableListOf()



)  : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Option) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
