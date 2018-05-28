package org.taktik.connector.business.therlink.domain

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.ToStringBuilder
import org.joda.time.DateTime
import java.io.Serializable
import java.util.*

abstract class TherapeuticLinkRequestType(
    val dateTime: DateTime,
    val id: String,
    val author: Author,
    val link: TherapeuticLink? = null,
    val proofs: List<Proof?> = ArrayList()
) : Serializable {
    @Deprecated("") constructor(
        id: String,
        date: Date,
        author: Author,
        link: TherapeuticLink,
        vararg proofs: Proof
    ) : this(dateTime = DateTime(date), id = id, author = author, link = link, proofs = proofs.toList())

    constructor(id: String, dateTime: DateTime, author: Author, link: TherapeuticLink, vararg proofs: Proof) : this(
        dateTime = dateTime,
        id = id,
        author = author,
        link = link,
        proofs = proofs.toList()
    )

    override fun hashCode(): Int {
        val builder = HashCodeBuilder()
        builder.append(this.link)
        builder.append(this.proofs)
        builder.append(this.author)
        builder.append(this.dateTime)
        builder.append(this.id)
        return builder.toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        } else if (other !is TherapeuticLinkRequestType) {
            return false
        } else if (other === this) {
            return true
        } else {
            val builder = EqualsBuilder()
            builder.append(this.link, other.link)
            builder.append(this.proofs, other.proofs)
            builder.append(this.author, other.author)
            builder.append(this.dateTime, other.dateTime)
            builder.append(this.id, other.id)
            return builder.isEquals
        }
    }

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        builder.append(this.link)
        builder.append(this.proofs)
        builder.append(this.author)
        builder.append(this.dateTime)
        builder.append(this.id)
        return builder.toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
