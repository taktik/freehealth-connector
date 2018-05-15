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

import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationAnnexType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationContentType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ConsultationDocumentType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.ContentContextType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DestinationContextType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse
import be.fgov.ehealth.ehbox.core.v3.FreeInformationsType
import org.apache.commons.lang.ArrayUtils
import org.slf4j.LoggerFactory
import org.taktik.connector.business.ehbox.api.domain.AcknowledgeMessage
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.business.ehbox.api.domain.Document
import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.ErrorMessage
import org.taktik.connector.business.ehbox.api.domain.Message
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.technical.enumeration.Charset
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.exception.UnsealConnectorException
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.IdentifierType

import javax.activation.DataHandler
import java.io.IOException
import java.io.InputStream
import java.security.KeyStore

class ConsultationFullMessageBuilder : AbstractConsultationBuilder<GetFullMessageResponse>() {

    @Throws(EhboxBusinessConnectorException::class, TechnicalConnectorException::class)
    fun buildFullMessage(
        keystore: KeyStore,
        passPhrase: String,
        response: GetFullMessageResponse
    ): Message<GetFullMessageResponse> {
        val receivedMsg = response.message
        val message =
            this.createMessage(
                receivedMsg.contentContext.contentSpecification,
                response,
                receivedMsg.messageId,
                response.message.publicationId
            )
        val container = AbstractConsultationBuilder.ExceptionContainer(message)
        this.processMessageInfo(response.messageInfo, message)
        this.processSender(response.sender, null, message)
        this.processDestinationContext(receivedMsg.destinationContexts, message)
        this.processContentContext(keystore, passPhrase, receivedMsg.contentContext, message, container)
        return container.getMessage()
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processContentContext(
        keystore: KeyStore,
        passPhrase: String,
        context: ContentContextType,
        message: Message<GetFullMessageResponse>,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        this.processContentSpecification(context.contentSpecification, message)
        this.processCustomMetas(context.customMetas, message)
        if (message is DocumentMessage<*>) {
            this.processDocument(keystore, passPhrase, context.content, message, container)
        } else if (message is AcknowledgeMessage<*>) {
            this.processAcknowledge(context.content, message)
        } else if (message is ErrorMessage<*>) {
            this.processError(context.content, message)
        }
    }

    @Throws(EhboxBusinessConnectorException::class, TechnicalConnectorException::class)
    private fun processAcknowledge(response: ConsultationContentType, message: Message<*>) {
        LOG.debug("processAcknowledge : processing acknowledge for message " + message.id + " and response acknowledgement" + response.acknowledgment + " : no special processing needed")
    }

    @Throws(EhboxBusinessConnectorException::class, TechnicalConnectorException::class)
    private fun processError(response: ConsultationContentType, message: Message<*>) {
        if (message is ErrorMessage<*>) {
            val error = response.error
            if (error != null) {
                message.errorCode = error.code
                message.errorPublicationId = error.publicationId
                if (error.messages != null) {
                    for (failureMessage in error.messages) {
                        message.errorMsg.add("error:" + failureMessage)
                    }
                }

                if (error.failures != null) {
                    for (failureMessage in error.failures) {
                        message.errorMsg.add("failure:" + failureMessage)
                    }
                }
            }
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processDocument(
        keystore: KeyStore,
        passPhrase: String,
        response: ConsultationContentType,
        message: Message<*>,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        val documentMessage = message as DocumentMessage<*>
        this.processINSSPatient(keystore, passPhrase, response.encryptableINSSPatient, documentMessage, container)
        this.processFreeText(keystore, passPhrase, response.freeInformations, documentMessage, container)
        this.processMainDocument(keystore, passPhrase, response.document, documentMessage, container)
        if (!response.annices.isEmpty()) {
            documentMessage.isHasAnnex = true

            for (annexType in response.annices) {
                this.processAnnex(keystore, passPhrase, documentMessage, annexType, container)
            }
        }
    }

    private fun processDestinationContext(destinationList: List<DestinationContextType>, message: Message<*>) {

        for (destinationContext in destinationList) {
            val identityType = IdentifierType.lookup(destinationContext.type, destinationContext.subType, 49)
            val destination = Addressee(identityType)
            destination.id = destinationContext.id
            if (destinationContext.user != null) {
                destination.firstName = destinationContext.user.firstName
                destination.lastName = destinationContext.user.lastName
            }

            destination.quality = destinationContext.quality
            message.getDestinations().add(destination)
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processAnnex(
        keystore: KeyStore,
        passPhrase: String,
        documentMessage: DocumentMessage<*>,
        annexType: ConsultationAnnexType,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        val annex = Document()
        annex.filename = annexType.downloadFileName
        annex.mimeType = annexType.mimeType
        val annexTitle =
            this.handleAndDecryptIfNeeded(
                keystore,
                passPhrase,
                annexType.encryptableTitle,
                documentMessage.isEncrypted,
                container
            )
        if (annexTitle != null) {
            annex.title = ConnectorIOUtils.toString(annexTitle, Charset.UTF_8)
        }

        val annexHandler: DataHandler?
        if (annexType.encryptableBinaryContent != null) {
            annexHandler = annexType.encryptableBinaryContent
            if (annexHandler != null) {
                annex.setContent(
                    this.base64decoding(
                        keystore,
                        passPhrase,
                        annexHandler,
                        documentMessage.isEncrypted,
                        container
                    )
                )
            }
        } else if (annexType.encryptableTextContent != null) {
            annex.setContent(
                this.handleAndDecryptIfNeeded(
                    keystore,
                    passPhrase,
                    annexType.encryptableTextContent,
                    documentMessage.isEncrypted,
                    container
                )
            )
        }

        documentMessage.annexList.add(annex)
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processMainDocument(
        keystore: KeyStore,
        passPhrase: String,
        response: ConsultationDocumentType,
        documentMessage: DocumentMessage<*>,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        val document = Document()
        document.filename = response.downloadFileName
        document.mimeType = response.mimeType
        document.title = response.title
        val responseHandler: DataHandler?
        if (response.encryptableBinaryContent != null) {
            responseHandler = response.encryptableBinaryContent
            if (responseHandler != null) {
                try {
                    document.setContent(
                        this.base64decoding(
                            keystore,
                            passPhrase,
                            responseHandler,
                            documentMessage.isEncrypted,
                            container
                        )
                    )
                } catch (var7: UnsealConnectorException) {
                    document.setException(var7)
                }
            }
        } else if (response.encryptableTextContent != null) {
            document.setContent(
                this.handleAndDecryptIfNeeded(
                    keystore,
                    passPhrase,
                    response.encryptableTextContent,
                    documentMessage.isEncrypted,
                    container
                )
            )
        }

        documentMessage.document = document
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processINSSPatient(
        keystore: KeyStore,
        passPhrase: String,
        encryptableINSSPatient: ByteArray?,
        documentMessage: DocumentMessage<*>,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        if (encryptableINSSPatient != null) {
            val encrypted = documentMessage.isEncrypted
            val patientInss =
                this.handleAndDecryptIfNeeded(keystore, passPhrase, encryptableINSSPatient, encrypted, container)
            if (patientInss != null) {
                documentMessage.patientInss = ConnectorIOUtils.toString(patientInss, Charset.UTF_8)
                documentMessage.isEncrypted = encrypted
            }
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processFreeText(
        keystore: KeyStore,
        passPhrase: String,
        response: FreeInformationsType?,
        documentMessage: DocumentMessage<*>,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ) {
        if (response != null) {
            val freeText =
                this.handleAndDecryptIfNeeded(
                    keystore,
                    passPhrase,
                    response.encryptableFreeText,
                    documentMessage.isEncrypted,
                    container
                )
            if (freeText != null && freeText.size > 0) {
                documentMessage.freeText = ConnectorIOUtils.toString(freeText, Charset.UTF_8)
            }

            val table = response.table
            if (table != null) {
                documentMessage.freeInformationTableTitle = table.title

                for (row in table.rows) {
                    val decryptedKeyBytes =
                        this.handleAndDecryptIfNeeded(
                            keystore,
                            passPhrase,
                            row.encryptableLeftCell,
                            documentMessage.isEncrypted,
                            container
                        )
                    val decryptedValueBytes =
                        this.handleAndDecryptIfNeeded(
                            keystore,
                            passPhrase,
                            row.encryptableRightCell,
                            documentMessage.isEncrypted,
                            container
                        )
                    val decryptedKey = ConnectorIOUtils.toString(decryptedKeyBytes, Charset.UTF_8)
                    val decryptedValue = ConnectorIOUtils.toString(decryptedValueBytes, Charset.UTF_8)
                    documentMessage.freeInformationTableRows.put(decryptedKey, decryptedValue)
                }
            }
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun base64decoding(
        keystore: KeyStore,
        passPhrase: String,
        dataHandler: DataHandler,
        encrypted: Boolean,
        container: AbstractConsultationBuilder.ExceptionContainer<GetFullMessageResponse>
    ): ByteArray? {
        var inputStream: InputStream? = null

        val result: ByteArray?
        try {
            inputStream = dataHandler.inputStream
            val byteVal = ConnectorIOUtils.getBytes(inputStream)
            if (ArrayUtils.isEmpty(byteVal)) {
                return null
            }
            result = this.handleAndDecryptIfNeeded(keystore, passPhrase, byteVal, encrypted, container)
        } catch (ex: IOException) {
            throw TechnicalConnectorException(
                TechnicalConnectorExceptionValues.ERROR_GENERAL,
                ex,
                "Unable to decode datahandler"
            )
        } finally {
            ConnectorIOUtils.closeQuietly(inputStream)
        }
        return result
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConsultationFullMessageBuilder::class.java)
    }
}
