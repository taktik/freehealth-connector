package org.taktik.connector.business.therlink.domain

import java.io.Serializable
import org.apache.commons.lang.builder.ToStringBuilder

class Author : Serializable {
    var hcParties: MutableSet<HcParty> = HashSet()

    override fun toString(): String {
        val builder = ToStringBuilder(this)
        this.hcParties.forEach { next ->
            builder.append("[")
            builder.append("Family name : " + next.familyName!!)
            builder.append("First Name: " + next.firstName!!)
            builder.append("Name: " + next.name!!)
            builder.append("Type: " + next.type!!)
            builder.append("ids : [")
            next.getIds().forEach { idHcParty ->
                builder.append("idHcParty:scheme=").append(idHcParty.s)
                builder.append(", value=").append(idHcParty.value)
            }
            builder.append("] ")
            builder.append("cds : [")
            next.getCds().forEach { cdHcParty ->
                builder.append("cdHcParty:scheme=").append(cdHcParty.s)
                builder.append(", value=").append(cdHcParty.value)
            }
            builder.append("]")
            builder.append("], ")
        }
        return builder.toString()
    }

    class Builder {
        private val author = Author()

        fun addHcParty(hcp: HcParty): Author.Builder {
            this.author.hcParties.add(hcp)
            return this
        }

        fun build(): Author {
            return this.author
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
