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

package org.taktik.connector.business.ehbox.v3.builders

import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest
import java.io.IOException
import java.security.KeyStore

import java.util.UUID

interface SendMessageBuilder {
    @Throws(IOException::class, EhboxBusinessConnectorException::class, TechnicalConnectorException::class)
    fun buildMessage(keystoreId: UUID,
                     keystore: KeyStore,
                     quality: String,
                     passPhrase: String,
                     document: DocumentMessage<Message>,
                     // User and Mandate elements are allowed in ehBox messages, but not in eh2Ebox specifications;
                     // see pg. 9:
                     // https://www.ehealth.fgov.be/ehealthplatform/file/view/6fe35988ac15c50a85612144deb56533?filename=eH2eBox%20-%20Cookbook%20v1.4%20dd%2020042020.pdf
                     eH2eBox: Boolean = false): SendMessageRequest
}
