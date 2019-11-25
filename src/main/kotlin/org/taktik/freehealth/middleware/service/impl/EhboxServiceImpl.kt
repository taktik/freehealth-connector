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

package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.commons.core.v1.StatusType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest
import org.springframework.stereotype.Service
import org.taktik.connector.business.ehbox.v3.builders.impl.ConsultationMessageBuilderImpl
import org.taktik.connector.business.ehbox.v3.builders.impl.SendMessageBuilderImpl
import org.taktik.connector.business.ehbox.v3.exception.EhboxCryptoException
import org.taktik.connector.business.ehbox.v3.validator.impl.EhboxReplyValidatorImpl
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.domain.common.Error
import org.taktik.freehealth.middleware.dto.ehbox.AltKeystore
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage
import org.taktik.freehealth.middleware.dto.ehbox.Message
import org.taktik.freehealth.middleware.dto.ehbox.MessageResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessagesResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessageOperationResponse
import org.taktik.freehealth.middleware.mapper.toDocumentMessage
import org.taktik.freehealth.middleware.mapper.toMessageDto
import org.taktik.freehealth.middleware.service.EhboxService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@Service
class EhboxServiceImpl(private val stsService: STSService, keyDepotService: KeyDepotService) : EhboxService {
    private val freehealthEhboxService: org.taktik.connector.business.ehbox.service.EhboxService =
        org.taktik.connector.business.ehbox.service.impl.EhboxServiceImpl(EhboxReplyValidatorImpl())
    private val consultationMessageBuilder = ConsultationMessageBuilderImpl()
    private val sendMessageBuilder = SendMessageBuilderImpl( KeyDepotManagerImpl.getInstance(keyDepotService))


    /**
     * Returns a list containing the results of applying the given [transform] function
     * to each element in the original collection.
     */
    private inline fun <T, R> Iterable<T>.mapFirstNotNull(transform: (T) -> R): R? {
        for (item in this) transform(item)?.let { return it }
        return null
    }

    override fun getInfos(keystoreId: UUID, tokenId: UUID, passPhrase: String): BoxInfo {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val infoRequest = GetBoxInfoRequest()
        val response = freehealthEhboxService.getBoxInfo(samlToken, infoRequest)

        return BoxInfo(
            boxId = response.boxId.id,
            quality = response.boxId.quality,
            nbrMessagesInStandBy = response.nbrMessagesInStandBy,
            currentSize = response.currentSize,
            maxSize = response.maxSize
        )
    }

    override fun getFullMessage(keystoreId: UUID,
                                tokenId: UUID,
                                passPhrase: String,
                                boxId: String,
                                messageId: String,
                                alternateKeystores: List<AltKeystore>?): MessageResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val messageRequest = MessageRequestType().apply {
            this.messageId = messageId
            this.source = boxId
        }
        val msg = freehealthEhboxService.getFullMessage(samlToken, messageRequest)

        return if (msg.status?.code == "100") try { consultationMessageBuilder.buildFullMessage(
            KeyStoreCredential(keystoreId, stsService.getKeyStore(keystoreId, passPhrase)!!, "authentication", passPhrase), msg).toMessageDto()?.let { MessageResponse(it) } ?: MessageResponse(null, Error("Unknown error")) } catch (e:EhboxCryptoException) {
            alternateKeystores?.mapFirstNotNull {
                try { it.uuid?.let { uuid -> it.passPhrase?.let { pass -> consultationMessageBuilder.buildFullMessage(KeyStoreCredential(uuid, stsService.getKeyStore(uuid, pass)!!, "authentication", pass), msg).toMessageDto() }}} catch(_: EhboxCryptoException) { null }
            }?.let { MessageResponse(it) } ?: MessageResponse(null, Error("Impossible to decrypt message using provided Keystores"))
        } else MessageResponse(null, Error(msg.status?.code, msg.status?.messages?.joinToString(",")))
    }

    override fun sendMessage(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        message: DocumentMessage,
        publicationReceipt: Boolean,
        receptionReceipt: Boolean,
        readReceipt: Boolean
    ): MessageOperationResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val request =
            sendMessageBuilder.buildMessage(
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                message.toDocumentMessage()
            ).apply {
                contentContext.contentSpecification.let {
                    it.isPublicationReceipt =
                        publicationReceipt; it.isReceivedReceipt = receptionReceipt; it.isReadReceipt =
                    readReceipt
                }
            }
        request.publicationId = UUID.randomUUID().toString().substring(0,12)
        val sendMessageResponse = freehealthEhboxService.sendMessage(samlToken, request)
        return if (sendMessageResponse.status?.code == "100") MessageOperationResponse(true) else MessageOperationResponse(false, Error(sendMessageResponse.status?.code, sendMessageResponse.status?.messages?.joinToString(",")))
    }

    override fun loadMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        boxId: String,
        limit: Int?,
        alternateKeystores: List<AltKeystore>?
    ): MessagesResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val messagesListRequest = GetMessagesListRequest()

        messagesListRequest.source = boxId
        messagesListRequest.startIndex = 1
        messagesListRequest.endIndex = 100

        val result = mutableListOf<Message>()
        var status: StatusType?

        while (true) {
            val response = freehealthEhboxService.getMessageList(samlToken, messagesListRequest)
            status = response.status

            if (status?.code != "100") { break }

            result.addAll(response.messages.mapNotNull { msg ->
                try { consultationMessageBuilder.buildMessage(
                    KeyStoreCredential(keystoreId, stsService.getKeyStore(keystoreId, passPhrase)!!, "authentication", passPhrase), msg
                ).toMessageDto() } catch (e:EhboxCryptoException) {
                    alternateKeystores?.mapFirstNotNull {
                        try { it.uuid?.let { uuid -> it.passPhrase?.let { pass -> consultationMessageBuilder.buildMessage(KeyStoreCredential(uuid, stsService.getKeyStore(uuid, pass)!!, "authentication", pass), msg).toMessageDto() }}} catch(_: EhboxCryptoException) { null }
                    } ?: ErrorMessage(title = "Impossible to decrypt message using provided Keystores")
                }
            })
            if (response.messages.size < 100 || (limit != null && result.size >= limit)) {
                break
            }
            messagesListRequest.startIndex = messagesListRequest.startIndex + 100
            messagesListRequest.endIndex = messagesListRequest.endIndex + 100
        }
        return MessagesResponse(result, if( status?.code != "100") Error(status?.code, status?.messages?.joinToString(",")) else null)
    }

    override fun moveMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        messageIds: List<String>,
        source: String,
        destination: String
    ): MessageOperationResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val mmr = MoveMessageRequest()
        mmr.source = source
        mmr.destination = destination
        mmr.messageIds.addAll(messageIds)
        val moveMessageResult = freehealthEhboxService.moveMessage(samlToken, mmr)
        return if (moveMessageResult.status?.code == "100") MessageOperationResponse(true) else MessageOperationResponse(false, Error(moveMessageResult.status?.code, moveMessageResult.status?.messages?.joinToString(",")))
    }

    override fun deleteMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        messageIds: List<String>,
        source: String
    ): MessageOperationResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val mmr = be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest()
        mmr.source = source
        mmr.messageIds.addAll(messageIds)
        val deleteMessageResult = freehealthEhboxService.deleteMessage(samlToken, mmr)
        return if (deleteMessageResult.status?.code == "100") MessageOperationResponse(true) else MessageOperationResponse(false, Error(deleteMessageResult.status?.code, deleteMessageResult.status?.messages?.joinToString(",")))
    }
}
