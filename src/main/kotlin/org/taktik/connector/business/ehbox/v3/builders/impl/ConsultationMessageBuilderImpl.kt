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

package org.taktik.connector.business.ehbox.v3.builders.impl

import org.taktik.connector.business.ehbox.api.domain.Message
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.business.ehbox.v3.builders.ConsultationMessageBuilder
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.etee.Crypto
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse

import java.security.KeyStore

class ConsultationMessageBuilderImpl : ConsultationMessageBuilder {
    private val fullBuilder: ConsultationFullMessageBuilder
    private val reducedBuilder: ConsultationReducedMessageBuilder

    init {
        this.fullBuilder = ConsultationFullMessageBuilder()
        this.reducedBuilder = ConsultationReducedMessageBuilder()
    }

    @Throws(EhboxBusinessConnectorException::class, TechnicalConnectorException::class)
    override fun buildFullMessage(keystore: KeyStore, passPhrase: String, msg: GetFullMessageResponse): Message<GetFullMessageResponse> {
        return this.fullBuilder.buildFullMessage(keystore, passPhrase, msg)
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    override fun buildMessage(keystore: KeyStore, passPhrase: String, msg: be.fgov.ehealth.ehbox.consultation.protocol.v3.Message): Message<be.fgov.ehealth.ehbox.consultation.protocol.v3.Message> {
        return this.reducedBuilder.buildMessage(keystore, passPhrase, msg)
    }
}
