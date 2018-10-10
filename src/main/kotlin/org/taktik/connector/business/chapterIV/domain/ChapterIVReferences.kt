package org.taktik.connector.business.chapterIV.domain

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import java.io.Serializable
import org.apache.commons.lang.builder.EqualsBuilder

class ChapterIVReferences @Throws(TechnicalConnectorException::class) constructor(init: Boolean) : Serializable {
    var commonReference: String? = null
    var commonNIPReference: String? = null
    var kmehrIdSuffix: String? = null
    var recordCommonInputId: String? = null

    init {
        if (init) {
            val id = IdGeneratorFactory.getIdGenerator().generateId()
            this.commonReference = id
            this.recordCommonInputId = id
            this.kmehrIdSuffix = id
        }
    }

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + if (this.commonNIPReference == null) 0 else this.commonNIPReference!!.hashCode()
        result = 31 * result + if (this.commonReference == null) 0 else this.commonReference!!.hashCode()
        result = 31 * result + if (this.kmehrIdSuffix == null) 0 else this.kmehrIdSuffix!!.hashCode()
        result = 31 * result + if (this.recordCommonInputId == null) 0 else this.recordCommonInputId!!.hashCode()
        return result
    }

    override fun equals(other: Any?) = when {
        this === other -> true
        other == null -> false
        this.javaClass != other.javaClass -> false
        else -> {
            val other = other as ChapterIVReferences?
            EqualsBuilder().append(this.commonNIPReference, other!!.commonNIPReference)
                .append(this.commonReference, other.commonReference).append(this.kmehrIdSuffix, other.kmehrIdSuffix)
                .append(this.recordCommonInputId, other.recordCommonInputId).isEquals
        }
    }
}
