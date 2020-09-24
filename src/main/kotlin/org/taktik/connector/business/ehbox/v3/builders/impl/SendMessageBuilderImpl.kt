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

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.business.ehbox.api.domain.Document
import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.NewsMessage
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.business.ehbox.v3.builders.SendMessageBuilder
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.enumeration.Charset
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotManager
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ByteArrayDatasource
import org.taktik.connector.technical.utils.ConnectorCryptoUtils
import org.taktik.connector.technical.utils.ConnectorIOUtils
import be.fgov.ehealth.ehbox.consultation.protocol.v3.Message
import be.fgov.ehealth.ehbox.core.v3.BoxIdType
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType
import be.fgov.ehealth.ehbox.core.v3.FreeInformationsType
import be.fgov.ehealth.ehbox.core.v3.Row
import be.fgov.ehealth.ehbox.core.v3.Table
import be.fgov.ehealth.ehbox.core.v3.User
import be.fgov.ehealth.ehbox.publication.protocol.v3.ContentContextType
import be.fgov.ehealth.ehbox.publication.protocol.v3.ContentSpecificationType
import be.fgov.ehealth.ehbox.publication.protocol.v3.DestinationContextType
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationAnnexType
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationContentType
import be.fgov.ehealth.ehbox.publication.protocol.v3.PublicationDocumentType
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.KeyStore
import java.util.HashSet
import javax.activation.DataHandler
import org.bouncycastle.cms.CMSException
import org.bouncycastle.util.encoders.Base64
import org.slf4j.LoggerFactory
import java.util.UUID

class SendMessageBuilderImpl(private val keydepotManager: KeyDepotManager) : SendMessageBuilder {

    @Throws(
        IOException::class,
        EhboxBusinessConnectorException::class,
        TechnicalConnectorException::class,
        CMSException::class
    )
    override fun buildMessage(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        document: DocumentMessage<Message>,
        eH2eBox: Boolean
                             ): SendMessageRequest {
        return this.buildCommonMessage(keystoreId, keystore, quality, passPhrase, document, eH2eBox)
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class, IOException::class)
    private fun buildCommonMessage(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        document: org.taktik.connector.business.ehbox.api.domain.Message<Message>,
        eH2eBox: Boolean
                                  ): SendMessageRequest {
        val isDocumentEncrypted = document.isEncrypted
        val destinationEtkSet = HashSet<EncryptionToken>()
        val sendMessageRequest = SendMessageRequest()
        sendMessageRequest.publicationId = document.publicationId
        this.processSender(sendMessageRequest, document.sender)
        this.processDestinations(keystoreId, quality, keystore, passPhrase, document, sendMessageRequest, destinationEtkSet, eH2eBox)
        this.processContent(keystoreId, keystore, quality, passPhrase, document, isDocumentEncrypted, sendMessageRequest, destinationEtkSet)
        return sendMessageRequest
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processContent(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        document: org.taktik.connector.business.ehbox.api.domain.Message<Message>,
        isDocumentEncrypted: Boolean,
        sendMessageRequest: SendMessageRequest,
        destinationEtkSet: Set<EncryptionToken>
                              ) {
        val content = ContentContextType()
        val contentTypeString = this.getContentTypeStringForMessage(document)
        this.processContentSpecification(
            contentTypeString,
            document.isUsePublicationReceipt,
            document.isUseReceivedReceipt,
            document.isUseReadReceipt,
            isDocumentEncrypted,
            content,
            document.isImportant
        )
        this.processCustomMetas(content, document.getCustomMetas())
        val documentMessage = document as DocumentMessage<Message>
        this.processCopyMailTo(documentMessage, sendMessageRequest)
        this.processPublicationContentTypeForDocumentMessage(
            keystoreId,
            keystore,
            quality,
            passPhrase,
            documentMessage,
            isDocumentEncrypted,
            destinationEtkSet,
            content
                                                            )
        sendMessageRequest.contentContext = content
    }

    private fun processCopyMailTo(documentMessage: DocumentMessage<Message>, sendMessageRequest: SendMessageRequest) {
        sendMessageRequest.copyMailTos.addAll(documentMessage.copyMailTo)
    }

    private fun getContentTypeStringForMessage(document: org.taktik.connector.business.ehbox.api.domain.Message<Message>): String {
        return when (document) {
            is NewsMessage -> "NEWS"
            is DocumentMessage -> "DOCUMENT"
            else -> throw UnsupportedOperationException("getContentTypeStringForMessage : the type " + document.javaClass + " is not supported as a message to be send")
        }
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processPublicationDocument(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        message: DocumentMessage<Message>,
        destinationEtkSet: Set<EncryptionToken>,
        contentType: PublicationContentType
                                          ) {

        (message.document ?: Document().apply {
            setContent("Document with ${message.annexList.size} annexes".toByteArray(Charsets.UTF_8))
        }).let { doc ->
            val dataToSend =
                this.encode(keystoreId, keystore, quality, passPhrase, doc.getContent(), message.isEncrypted, destinationEtkSet)

            contentType.document = PublicationDocumentType().apply {
                digest = processDigest(dataToSend)
                encryptableTextContent = dataToSend
                mimeType = doc.mimeType ?: "text/plain"
                title = doc.title ?: "Untitled"
                downloadFileName = doc.filename ?: "body.txt"
            }
        }
    }

    private fun processCustomMetas(content: ContentContextType, customMetas: Map<String, String>?) {
        if (customMetas?.isNotEmpty() == true) {
            content.customMetas.addAll(customMetas.map { e -> CustomMetaType().apply { key = e.key; value = e.value } })
        }
    }

    private fun processContentSpecification(
        contentTypeString: String,
        publicationReceipt: Boolean?,
        receivedReceipt: Boolean?,
        readReceipt: Boolean?,
        isDocumentEncrypted: Boolean,
        content: ContentContextType,
        isImportant: Boolean
    ) {
        val contentSpecification = ContentSpecificationType()
        contentSpecification.applicationName =
            ConfigFactory.getConfigValidator().getProperty("ehbox.application.name", "\${package.name}")
        contentSpecification.isIsEncrypted = isDocumentEncrypted
        contentSpecification.isIsImportant = isImportant
        if (publicationReceipt != null) {
            contentSpecification.isPublicationReceipt = publicationReceipt
        }

        if (readReceipt != null) {
            contentSpecification.isReadReceipt = readReceipt
        }

        if (receivedReceipt != null) {
            contentSpecification.isReceivedReceipt = receivedReceipt
        }

        contentSpecification.contentType = contentTypeString
        content.contentSpecification = contentSpecification
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processPublicationContentTypeForDocumentMessage(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        document: DocumentMessage<Message>,
        isDocumentEncrypted: Boolean,
        destinationEtkSet: Set<EncryptionToken>,
        content: ContentContextType
                                                               ) {
        val contentType = PublicationContentType()
        this.processPublicationDocumentTypeForDocument(
            keystoreId,
            keystore,
            quality,
            passPhrase,
            document.document,
            document.documentTitle,
            isDocumentEncrypted,
            destinationEtkSet,
            contentType
                                                      )
        this.processFreeTextAndFreeInformationTable(
            keystoreId,
            keystore,
            quality,
            passPhrase,
            document.freeText,
            document.freeInformationTableTitle,
            document.freeInformationTableRows,
            isDocumentEncrypted,
            destinationEtkSet,
            contentType
                                                   )
        this.processPatientInss(
            keystoreId,
            keystore,
            quality,
            passPhrase,
            destinationEtkSet,
            contentType,
            document.patientInss,
            isDocumentEncrypted
                               )
        this.processAnnexes(
            keystoreId,
            keystore,
            quality,
            passPhrase,
            document.annexList,
            isDocumentEncrypted,
            destinationEtkSet,
            contentType
                           )
        this.processPublicationDocument(keystoreId, keystore, quality, passPhrase, document, destinationEtkSet, contentType)
        content.content = contentType
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processPublicationDocumentTypeForDocument(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        documentContent: Document?,
        documentTitle: String?,
        isDocumentEncrypted: Boolean,
        destinationEtkSet: Set<EncryptionToken>,
        contentType: PublicationContentType
                                                         ) {
        val documentType = PublicationDocumentType()
        documentType.title = documentTitle
        if (documentContent != null) {
            documentType.downloadFileName = documentContent.filename
            documentType.mimeType = documentContent.mimeType
            val dataToSend =
                this.encode(keystoreId, keystore, quality, passPhrase, documentContent.getContent(), isDocumentEncrypted, destinationEtkSet)
            documentType.digest = this.processDigest(dataToSend)
            documentType.encryptableBinaryContent =
                DataHandler(ByteArrayDatasource(dataToSend, documentContent.mimeType))
        }

        contentType.document = documentType
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processFreeTextAndFreeInformationTable(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        freeText: String?,
        tableTitle: String?,
        tableRows: Map<String, String>,
        isDocumentEncrypted: Boolean,
        destinationEtkSet: Set<EncryptionToken>,
        contentType: PublicationContentType
                                                      ) {
        val hasFreeText = this.freeTextFilledOut(freeText)
        val hasFreeInformationTable = this.freeInformationTableFilledOut(tableTitle, tableRows)
        if (hasFreeInformationTable && hasFreeText) {
            throw IllegalArgumentException("you cannot use both freeInformations.freeText and freeInformations.table together , they are mutually exclusive")
        } else {
            if (hasFreeText || hasFreeInformationTable) {
                val freeInformation = FreeInformationsType()
                if (hasFreeText) {
                    freeInformation.encryptableFreeText =
                        this.encode(
                            keystoreId,
                            keystore,
                            quality,
                            passPhrase,
                            ConnectorIOUtils.toBytes(freeText, Charset.UTF_8),
                            isDocumentEncrypted,
                            destinationEtkSet
                                   )
                }

                if (hasFreeInformationTable) {
                    freeInformation.table =
                        this.fillEncryptableTable(
                            keystoreId,
                            keystore,
                            quality,
                            passPhrase,
                            tableTitle,
                            tableRows,
                            isDocumentEncrypted,
                            destinationEtkSet
                                                 )
                }

                contentType.freeInformations = freeInformation
            }
        }
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun fillEncryptableTable(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        tableTitle: String?,
        tableRows: Map<String, String>,
        isDocumentEncrypted: Boolean,
        destinationEtkSet: Set<EncryptionToken>
                                    ): Table {
        val table = Table()
        table.title = tableTitle

        for (rowKey in tableRows.keys) {
            val rowValue = tableRows[rowKey]
            val row = Row()
            row.encryptableLeftCell = this.encode(keystoreId, keystore, quality, passPhrase, rowKey, isDocumentEncrypted, destinationEtkSet)
            row.encryptableRightCell =
                this.encode(keystoreId, keystore, quality, passPhrase, rowValue, isDocumentEncrypted, destinationEtkSet)
            table.rows.add(row)
        }

        return table
    }

    private fun freeInformationTableFilledOut(tableTitle: String?, tableRows: Map<String, String>?): Boolean {
        return tableTitle != null && tableRows != null && !tableRows.isEmpty()
    }

    @Throws(UnsupportedEncodingException::class)
    private fun freeTextFilledOut(freeText: String?): Boolean {
        return freeText != null && freeText.length > 0
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processAnnexes(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        annexList: List<Document>,
        isDocumentEncrypted: Boolean,
        destinationEtkSet: Set<EncryptionToken>,
        contentType: PublicationContentType
                              ) {

        for (annex in annexList) {
            val annexType = PublicationAnnexType()
            annexType.downloadFileName = annex.filename
            val dataToSend =
                this.encode(keystoreId, keystore, quality, passPhrase, annex.getContent(), isDocumentEncrypted, destinationEtkSet)
            annexType.digest = this.processDigest(dataToSend)
            if (annex.getContent().isEmpty()) {
                annexType.encryptableTextContent = annex.getContent()
            } else {
                annexType.encryptableBinaryContent = DataHandler(ByteArrayDatasource(dataToSend, annex.mimeType))
            }

            annexType.encryptableTitle =
                this.encode(keystoreId, keystore, quality, passPhrase, annex.title ?: "Annex", isDocumentEncrypted, destinationEtkSet)
            annexType.mimeType = annex.mimeType
            contentType.annices.add(annexType)
        }
    }

    @Throws(IOException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processPatientInss(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        destinationEtkSet: Set<EncryptionToken>,
        contentType: PublicationContentType,
        patientInss: String?,
        isEncrypted: Boolean?
                                  ) {
        if (patientInss != null) {
            contentType.encryptableINSSPatient =
                this.encode(
                    keystoreId,
                    keystore,
                    quality,
                    passPhrase,
                    ConnectorIOUtils.toBytes(patientInss, Charset.UTF_8),
                    isEncrypted!!,
                    destinationEtkSet
                           )
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun processDestinations(
        keystoreId: UUID,
        quality: String,
        keystore: KeyStore,
        passPhrase: String,
        document: org.taktik.connector.business.ehbox.api.domain.Message<Message>,
        sendMessageRequest: SendMessageRequest,
        destinationEtkSet: MutableSet<EncryptionToken>,
        eH2eBox: Boolean
                                   ) {

        for (addressee in document.getDestinations()) {
            val destination = this.buildDestination(addressee, eH2eBox)
            sendMessageRequest.destinationContexts.add(destination)
            if (document.isEncrypted) {
                val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, quality)

                destinationEtkSet.addAll(this.getETKForAddressee(addressee, keystoreId))
                destinationEtkSet.add(this.keydepotManager.getETK(credential, keystoreId))
            }
        }
    }

    private fun processSender(sendMessageRequest: SendMessageRequest, sender: Addressee?) {
        if (sender != null) {
            sendMessageRequest.boxId = BoxIdType()
            sendMessageRequest.boxId.id = sender.id
            sendMessageRequest.boxId.quality = sender.quality
            sendMessageRequest.boxId.subType = sender.subType
            sendMessageRequest.boxId.type = sender.type
        }
    }

    @Throws(TechnicalConnectorException::class)
    private fun processDigest(data: ByteArray?): String {
        return String(Base64.encode(ConnectorCryptoUtils.calculateDigest("SHA-256", data)))
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun getETKForAddressee(addressee: Addressee, keystoreId: UUID): Set<EncryptionToken> {
        return if ("ALL" != addressee.id) {
            val etkSet =
                this.keydepotManager.getEtkSet(
                    addressee.identifierTypeHelper,
                    addressee.idAsLong,
                    addressee.applicationId,
                    keystoreId,
                    false
                                              )
            if (etkSet.isEmpty()) {
                throw TechnicalConnectorException(
                    TechnicalConnectorExceptionValues.ERROR_GENERAL,
                    "could not retrieve Etk for known addressee " + addressee
                                                 )
            } else {
                etkSet
            }
        } else {
            HashSet()
        }
    }

    private fun buildDestination(addressee: Addressee, eH2eBox: Boolean): DestinationContextType {
        val destination = DestinationContextType()
        destination.id = addressee.id
        destination.quality = addressee.quality
        destination.type = addressee.type
        destination.subType = addressee.subType
        destination.isOoOProcessed = addressee.isOoOProcessed
        if (!eH2eBox && addressee.firstName != null && addressee.lastName != null) {
            val user = User()
            user.firstName = addressee.firstName
            user.lastName = addressee.lastName
            user.value = addressee.id
            destination.user = user
        }

        return destination
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun encode(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        content: ByteArray?,
        encrypted: Boolean,
        tokens: Set<EncryptionToken>
                      ): ByteArray? {
        var byteVal: ByteArray? = null
        if (encrypted && content != null && content.size != 0) {
            val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, quality)
            val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
            val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

            byteVal = crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, tokens, content)
        } else {
            byteVal = content
        }

        return byteVal
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    private fun encode(
        keystoreId: UUID,
        keystore: KeyStore,
        quality: String,
        passPhrase: String,
        content: String?,
        encrypted: Boolean,
        tokens: Set<EncryptionToken>
                      ): ByteArray? {
        return this.encode(keystoreId, keystore, quality, passPhrase, ConnectorIOUtils.toBytes(content, Charset.UTF_8), encrypted, tokens)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SendMessageBuilderImpl::class.java)
        val DEFAULT_MIME_TYPE = "application/octet-stream"
    }
}
