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

import org.joda.time.DateTime
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.utils.DateUtils

import java.io.Serializable
import java.util.ArrayList
import java.util.Date
import java.util.HashMap

abstract class Message<T> : Serializable {
    var id: String? = null
    var publicationId: String? = null
    var sender: Addressee? = null


    @Deprecated("")
    @get:Deprecated("")
    @set:Deprecated("")
    var mandatee: Addressee? = null
    private var destinations: MutableList<Addressee>? = null
    var isImportant: Boolean = false
    var isEncrypted: Boolean = false
    var isUsePublicationReceipt: Boolean = false
    var isUseReceivedReceipt: Boolean = false
    var isUseReadReceipt: Boolean = false
    var original: T? = null
    var isHasAnnex: Boolean = false
    var isHasFreeInformations: Boolean = false
    var publicationDateTime: DateTime? = null
    var expirationDateTime: DateTime? = null
    var size: String? = null
    private var customMetas: MutableMap<String, String>? = null


    var expirationDate: Date
        @Deprecated("")
        get() = this.expirationDateTime!!.toDate()
        @Deprecated("")
        set(expirationDate) {
            this.expirationDateTime = DateUtils.convert(expirationDate)
        }


    var publicationDate: Date
        @Deprecated("")
        get() = this.publicationDateTime!!.toDate()
        @Deprecated("")
        set(publicationDate) {
            this.publicationDateTime = DateUtils.convert(publicationDate)
        }

    init {
        this.generatePublicationId()
    }

    fun generatePublicationId() {
        try {
            this.publicationId = IdGeneratorFactory.getIdGenerator(ConfigFactory.getConfigValidator().getProperty("org.taktik.connector.business.ehbox.api.domain.message.idgenerator", "nano")).generateId()
        } catch (var5: TechnicalConnectorException) {
            val time = System.nanoTime()
            val identifier = java.lang.Long.toString(time, 36).toUpperCase()
            this.publicationId = identifier
        }

    }

    fun getDestinations(): MutableList<Addressee> {
        if (this.destinations == null) {
            this.destinations = ArrayList()
        }

        return this.destinations!!
    }

    fun setDestinations(destinations: MutableList<Addressee>) {
        this.destinations = destinations
    }

    override fun toString(): String {
        return "Message [id=" + this.publicationId + ", sender=" + this.sender + ", destinations=" + this.destinations + ", important=" + this.isImportant + ", encrypted=" + this.isEncrypted + "]"
    }

    fun getCustomMetas(): MutableMap<String, String> {
        if (this.customMetas == null) {
            this.customMetas = HashMap()
        }

        return this.customMetas!!
    }

    companion object {
        private val PROP_IDGENERATOR = "org.taktik.connector.business.ehbox.api.domain.message.idgenerator"
    }
}
