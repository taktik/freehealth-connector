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

import org.taktik.connector.business.ehbox.api.domain.Document
import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.ErrorMessage
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.technical.enumeration.Charset
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.utils.ConnectorIOUtils
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message
import be.fgov.ehealth.ehbox.core.v3.ContentInfoType

import java.security.KeyStore

class ConsultationReducedMessageBuilder : AbstractConsultationBuilder<Message>() {

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    fun buildMessage(
        keystore: KeyStore,
        passPhrase: String,
        response: Message
    ): org.taktik.connector.business.ehbox.api.domain.Message<Message> {
        val message = this.createMessage(response.contentSpecification, response, response.messageId, null)
        val container = AbstractConsultationBuilder.ExceptionContainer(message)
        this.processMessageInfo(response.messageInfo, message)
        this.processContentSpecification(response.contentSpecification, message)
        this.processContentInfo(response.contentInfo, message)
        this.processContent(keystore, passPhrase, response.contentInfo, message, container)
        this.processCustomMetas(response.customMetas, message)
        this.processDestination(response, message)
        this.processSender(response.sender, response.contentSpecification, message)
        return container.getMessage()
    }

    private fun processDestination(
        response: Message,
        message: org.taktik.connector.business.ehbox.api.domain.Message<Message>
    ) {
        if (response.destination != null) {
            val destination = this.buildAddressee(response.destination)
            message.getDestinations().add(destination)
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processContent(
        keystore: KeyStore,
        passPhrase: String,
        response: ContentInfoType,
        message: org.taktik.connector.business.ehbox.api.domain.Message<Message>,
        container: AbstractConsultationBuilder.ExceptionContainer<Message>
    ) {
        if (message is DocumentMessage<*>) {
            val documentMessage = message as DocumentMessage<*>
            val document = Document()
            document.title = response.title
            documentMessage.document = document
            val decodedInss =
                this.handleAndDecryptIfNeeded(
                    keystore,
                    passPhrase,
                    response.encryptableINSSPatient,
                    documentMessage.isEncrypted,
                    container
                )
            if (decodedInss != null) {
                documentMessage.patientInss = ConnectorIOUtils.toString(decodedInss, Charset.UTF_8)
            }
        } else if (message is ErrorMessage<*>) {
            val errorMessage = message as ErrorMessage<*>
            errorMessage.title = response.title
        }
    }
}
