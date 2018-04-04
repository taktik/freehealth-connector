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

import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest
import org.springframework.stereotype.Service
import org.taktik.connector.business.ehbox.v3.builders.impl.ConsultationMessageBuilderImpl
import org.taktik.connector.business.ehbox.v3.builders.impl.SendMessageBuilderImpl
import org.taktik.connector.business.ehbox.v3.validator.impl.EhboxReplyValidatorImpl
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage
import org.taktik.freehealth.middleware.dto.ehbox.Message
import org.taktik.freehealth.middleware.mapper.toDocumentMessage
import org.taktik.freehealth.middleware.mapper.toMessageDto
import org.taktik.freehealth.middleware.service.EhboxService
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

@Service
class EhboxServiceImpl(val stsService: STSService) : EhboxService {
    private val freehealthEhboxService: org.taktik.connector.business.ehbox.service.EhboxService = org.taktik.connector.business.ehbox.service.impl.EhboxServiceImpl(EhboxReplyValidatorImpl())
    private val consultationMessageBuilder = ConsultationMessageBuilderImpl()
    private val sendMessageBuilder = SendMessageBuilderImpl(KeyDepotManagerFactory.getKeyDepotManager())


    private fun getSamlToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken {
        return stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
    }

    override fun getInfos(keystoreId: UUID, tokenId: UUID, passPhrase: String): BoxInfo {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
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

    override fun getFullMessage(keystoreId: UUID, tokenId: UUID, passPhrase: String, boxId: String, messageId: String): Message {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
        val messageRequest = MessageRequestType().apply {
            this.messageId = messageId
            this.source = boxId
        }
        val fullMessage = freehealthEhboxService.getFullMessage(samlToken, messageRequest)
        return consultationMessageBuilder.buildFullMessage(stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase, fullMessage).toMessageDto() ?: ErrorMessage(title = "Unknown error")
    }

    override fun sendMessage(keystoreId: UUID, tokenId: UUID, passPhrase: String, message: DocumentMessage, publicationReceipt: Boolean, receptionReceipt: Boolean, readReceipt: Boolean): Boolean {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
        val request = sendMessageBuilder.buildMessage(stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase, message.toDocumentMessage()).apply {
            contentContext.contentSpecification.let { it.isPublicationReceipt = publicationReceipt; it.isReceivedReceipt = receptionReceipt; it.isPublicationReceipt = readReceipt }
        }
        return freehealthEhboxService.sendMessage(samlToken, request).status?.code == "100"
    }

    override fun loadMessages(keystoreId: UUID, tokenId: UUID, passPhrase: String, boxId: String, limit: Int?): List<Message> {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
        val messagesListRequest = GetMessagesListRequest()

        messagesListRequest.source = boxId
        messagesListRequest.startIndex = 1
        messagesListRequest.endIndex = 100

        val result = mutableListOf<Message>()
        while (true) {
            val response = freehealthEhboxService.getMessageList(samlToken, messagesListRequest)
            result.addAll(response.messages.mapNotNull {consultationMessageBuilder.buildMessage(stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase, it).toMessageDto()})
            if (response.messages.size < 100 || (limit != null && result.size >= limit)) {
                break
            }
            messagesListRequest.startIndex = messagesListRequest.startIndex + 100
            messagesListRequest.endIndex = messagesListRequest.endIndex + 100
        }
        return result
    }

    override fun moveMessages(keystoreId: UUID, tokenId: UUID, passPhrase: String, messageIds: List<String>, source: String, destination: String): Boolean {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
        val mmr = MoveMessageRequest()
        mmr.source = source
        mmr.destination = destination
        mmr.messageIds.addAll(messageIds)
        return freehealthEhboxService.moveMessage(samlToken, mmr).status?.code == "100"
    }

    override fun deleteMessages(keystoreId: UUID, tokenId: UUID, passPhrase: String, messageIds: List<String>, source: String): Boolean {
        val samlToken = getSamlToken(tokenId, keystoreId, passPhrase)
        val mmr = be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest()
        mmr.source = source
        mmr.messageIds.addAll(messageIds)
        return freehealthEhboxService.deleteMessage(samlToken, mmr).status?.code == "100"
    }

}