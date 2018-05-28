/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.ehbox.api.domain

import org.slf4j.LoggerFactory
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues
import org.taktik.connector.business.ehbox.api.utils.QualityType
import org.taktik.connector.technical.utils.IdentifierType

class Addressee {
    var id: String? = null
    private var identifier: IdentifierType? = null
    var quality: String? = null
    var applicationId: String? = null
    var lastName: String? = null
    var firstName: String? = null
    var organizationName: String? = null
    var personInOrganisation: String? = null
    var isOoOProcessed: Boolean = false

    val idAsLong: Long
        get() = java.lang.Long.parseLong(this.id!!)

    val type: String?
        get() = if (this.identifier == null) null else this.identifier!!.getType(IdentifierType.EHBOX)

    val subType: String?
        get() = if (this.identifier == null) null else this.identifier!!.getSubType(IdentifierType.EHBOX)

    val identifierTypeHelper: IdentifierType
        @Throws(EhboxBusinessConnectorException::class) get() {
            if (this.identifier == null) {
                LOG.debug("\t## Identifier is empty : throwing Ehbox business connector exception")
                throw EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.NO_QUALITY_SET)
            } else {
                return this.identifier!!
            }
        }

    constructor(id: String, qualityType: QualityType) {
        this.id = id
        this.quality = qualityType.quality
        this.identifier = qualityType.identifierType
    }

    constructor(id: String, helperType: IdentifierType) {
        this.id = id
        this.identifier = helperType
    }

    constructor(type: IdentifierType?) {
        if (type == null) {
            throw IllegalArgumentException("this constructor cannot be called with a null value")
        } else {
            this.identifier = type
        }
    }

    fun setQuality(quality: QualityType?) {
        if (quality != null) {
            this.quality = quality.quality
            this.identifier = quality.identifierType
        }
    }

    fun setIdenfitierTypeHelper(helper: IdentifierType) {
        this.identifier = helper
    }

    override fun toString(): String {
        val sb = StringBuffer()
        if (this.personInOrganisation == null) {
            sb.append(this.firstName)
            sb.append(" ")
            sb.append(this.lastName)
        } else {
            sb.append(this.organizationName)
        }

        return "Addressee [id=${this.id}, identifier=${this.identifier}, quality=${this.quality}, name=$sb]"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Addressee::class.java)
    }
}
