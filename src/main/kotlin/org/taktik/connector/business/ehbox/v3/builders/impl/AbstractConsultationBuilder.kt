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
import org.taktik.connector.business.ehbox.api.domain.AcknowledgeMessage
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.ErrorMessage
import org.taktik.connector.business.ehbox.api.domain.Message
import org.taktik.connector.business.ehbox.api.domain.NewsMessage
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues
import org.taktik.connector.business.ehbox.v3.exception.EhboxCryptoException
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.UnsealConnectorException
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorExceptionUtils
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.IdentifierType
import be.fgov.ehealth.ehbox.core.v3.ContentInfoType
import be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType
import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType
import be.fgov.ehealth.ehbox.core.v3.MessageInfoType
import be.fgov.ehealth.ehbox.core.v3.SenderType

import java.security.KeyStore
import java.util.ArrayList

import org.apache.commons.lang.ArrayUtils
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory

abstract class AbstractConsultationBuilder<T>() {

    protected fun processCustomMetas(metas: List<CustomMetaType>?, message: Message<T>) {
        metas?.forEach { message.getCustomMetas().put(it.key, it.value) }
    }

    protected fun processSender(inSender: SenderType?, contentspec: ContentSpecificationType?, message: Message<T>) {
        if (inSender != null) {
            val addressee = this.buildAddressee(inSender)
            if (contentspec != null) {
                addressee.applicationId = contentspec.applicationName
            }
            message.sender = addressee
        }
    }

    protected fun processContentInfo(contentInfo: ContentInfoType?, message: Message<T>) {
        if (contentInfo != null) {
            message.isHasAnnex = contentInfo.isHasAnnex
            message.isHasFreeInformations = contentInfo.isHasFreeInformations
        }
    }

    protected fun buildAddressee(sender: SenderType): Addressee {
        val identifierType = IdentifierType.lookup(sender.type, sender.subType, 49)
        val destination = Addressee(identifierType)
        destination.id = sender.id
        if (StringUtils.isEmpty(sender.personInOrganisation)) {
            destination.firstName = sender.firstName
            destination.lastName = sender.name
        } else {
            destination.organizationName = sender.name
            destination.personInOrganisation = sender.personInOrganisation
        }

        destination.quality = sender.quality
        return destination
    }

    protected fun buildAddressee(identifier: EhboxIdentifierType): Addressee {
        val identifierType = IdentifierType.lookup(identifier.type, identifier.subType, 49)
        val destination = Addressee(identifierType)
        destination.id = identifier.id
        if (identifier.user != null) {
            destination.firstName = identifier.user.firstName
            destination.lastName = identifier.user.lastName
        }

        destination.quality = identifier.quality
        return destination
    }

    protected fun processMessageInfo(response: MessageInfoType?, message: Message<T>) {
        if (response != null) {
            message.expirationDateTime = response.expirationDate
            message.publicationDateTime = response.publicationDate
            message.size = response.size
        }
    }

    protected fun processContentSpecification(contentspec: ContentSpecificationType?, message: Message<T>) {
        if (contentspec != null) {
            message.isImportant = contentspec.isIsImportant
            message.isEncrypted = contentspec.isIsEncrypted
            if (message.sender != null) {
                message.sender!!.applicationId = contentspec.applicationName
            }
        }
    }

    @Throws(TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    protected fun handleAndDecryptIfNeeded(
        credential: KeyStoreCredential,
        data: ByteArray,
        encrypted: Boolean,
        container: AbstractConsultationBuilder.ExceptionContainer<T>
    ): ByteArray? {
        if (ArrayUtils.isEmpty(data)) {
            return data
        } else {
            var byteVal = ArrayUtils.clone(data)
            if (ConfigFactory.getConfigValidator().getBooleanProperty("ehboxv3.try.to.base64decode.content", true)!!) {
                byteVal = ConnectorIOUtils.base64Decode(byteVal, false)
            }

            if (encrypted) {
                try {
                    val hokPrivateKeys = KeyManager.getDecryptionKeys(credential.keyStore, credential.password)
                    val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)
                    byteVal = crypto.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, byteVal).contentAsByte
                } catch (unsealConnectorException: UnsealConnectorException) {
                    container.exceptions.add(unsealConnectorException)

                    try {
                        byteVal = ConnectorExceptionUtils.processUnsealConnectorException(unsealConnectorException)
                    } catch (connectorException: UnsealConnectorException) {
                        LOG.error("unrecoverable unsealException occurred while decrypting ehbox content ," +
                                      " returning null as message , error : ${connectorException.message}")
                        throw EhboxCryptoException(connectorException, null)
                    }
                }
            }

            return byteVal
        }
    }

    @Throws(EhboxBusinessConnectorException::class)
    protected fun createMessage(
        content: ContentSpecificationType,
        responseMsg: T,
        id: String,
        publicationId: String?
    ): Message<T> {
        val message: Message<T> = when (content.contentType) {
            "DOCUMENT" -> DocumentMessage()
            "NEWS" -> NewsMessage()
            "ERROR" -> ErrorMessage()
            "ACKNOWLEDGMENT" -> AcknowledgeMessage()
            else -> throw EhboxBusinessConnectorException(
                EhboxBusinessConnectorExceptionValues.ERROR_BUSINESS_CODE_REASON,
                "Unsupported contentType",
                content.contentType
            )
        }

        message.original = responseMsg
        message.id = id
        message.publicationId = publicationId

        return message
    }

    class ExceptionContainer<T>(private val message: Message<T>) {
        val exceptions = ArrayList<UnsealConnectorException>()

        @Throws(EhboxCryptoException::class)
        fun getMessage(): Message<T> {
            return if (this.exceptions.isEmpty()) {
                this.message
            } else {
                throw EhboxCryptoException(this.exceptions, this.message)
            }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AbstractConsultationBuilder::class.java)
    }
}
