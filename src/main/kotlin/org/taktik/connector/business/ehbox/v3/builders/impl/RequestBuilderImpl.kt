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

import org.taktik.connector.business.ehbox.v3.builders.RequestBuilder
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoORequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoORequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest
import be.fgov.ehealth.ehbox.core.v3.BoxIdType
import org.joda.time.DateTime

class RequestBuilderImpl : RequestBuilder {

    override fun createDeleteMessageRequest(source: String, vararg messageIds: String): DeleteMessageRequest {
        return this.createDeleteMessageRequest(source, null, *messageIds as Array<String>)
    }

    override fun createGetAllEhboxesMessagesListRequest(
        source: String,
        startIndex: Int?,
        endIndex: Int?
    ): GetAllEhboxesMessagesListRequest {
        val request = GetAllEhboxesMessagesListRequest()
        request.source = source
        request.startIndex = startIndex
        request.endIndex = endIndex
        return request
    }

    override fun createGetMessagesListRequest(
        source: String,
        startIndex: Int,
        endIndex: Int,
        boxId: BoxIdType
    ): GetMessagesListRequest {
        val request = GetMessagesListRequest()
        request.source = source
        request.startIndex = startIndex
        request.endIndex = endIndex
        request.boxId = boxId
        return request
    }

    override fun createGetMessagesListRequest(source: String): GetMessagesListRequest {
        val request = GetMessagesListRequest()
        request.source = source
        return request
    }

    override fun createDeleteMessageRequest(
        source: String,
        boxId: BoxIdType?,
        vararg messageIds: String
    ): DeleteMessageRequest {
        val deleteMessageRequest = DeleteMessageRequest()
        deleteMessageRequest.boxId = boxId
        deleteMessageRequest.source = source
        val `len$` = messageIds.size

        for (`i$` in 0 until `len$`) {
            val messageId = messageIds[`i$`]
            deleteMessageRequest.messageIds.add(messageId)
        }

        return deleteMessageRequest
    }

    override fun createBoxInfoRequestForDefaultBox(): GetBoxInfoRequest {
        return GetBoxInfoRequest()
    }

    override fun createBoxInfoRequest(boxId: BoxIdType): GetBoxInfoRequest {
        val getBoxInfoRequest = GetBoxInfoRequest()
        getBoxInfoRequest.boxId = boxId
        return getBoxInfoRequest
    }

    override fun createAllEhboxesMessagesListRequest(source: String): GetAllEhboxesMessagesListRequest {
        return this.createAllEhboxesMessagesListRequest(source, null, null)
    }

    override fun createAllEhboxesMessagesListRequest(
        source: String,
        startIndex: Int?,
        endIndex: Int?
    ): GetAllEhboxesMessagesListRequest {
        val getAllEhboxesMessagesListRequest = GetAllEhboxesMessagesListRequest()
        getAllEhboxesMessagesListRequest.startIndex = startIndex
        getAllEhboxesMessagesListRequest.endIndex = endIndex
        getAllEhboxesMessagesListRequest.source = source
        return getAllEhboxesMessagesListRequest
    }

    override fun createMessageRequestType(messageId: String): MessageRequestType {
        return this.createMessageRequestType(messageId, "INBOX")
    }

    override fun createMessageRequestType(messageId: String, source: String): MessageRequestType {
        return this.createMessageRequestType(messageId, source, null)
    }

    override fun createMessageRequestType(messageId: String, source: String, boxId: BoxIdType?): MessageRequestType {
        val messageRequestType = MessageRequestType()
        messageRequestType.boxId = boxId
        messageRequestType.messageId = messageId
        messageRequestType.source = source
        return messageRequestType
    }

    override fun createGetFullMessageRequest(messageId: String): GetFullMessageRequest {
        return this.createGetFullMessageRequest(messageId, "INBOX")
    }

    override fun createGetFullMessageRequest(messageId: String, source: String): GetFullMessageRequest {
        return this.createGetFullMessageRequest(messageId, source, null)
    }

    override fun createGetFullMessageRequest(
        messageId: String,
        source: String,
        boxId: BoxIdType?
    ): GetFullMessageRequest {
        val messageRequestType = GetFullMessageRequest()
        messageRequestType.boxId = boxId
        messageRequestType.messageId = messageId
        messageRequestType.source = source
        return messageRequestType
    }

    override fun createGetHistoryRequest(messageId: String): GetHistoryRequest {
        return this.createGetHistoryRequest(messageId, null)
    }

    override fun createGetHistoryRequest(messageId: String, source: String?): GetHistoryRequest {
        return this.createGetHistoryRequest(messageId, source, null)
    }

    override fun createGetHistoryRequest(messageId: String, source: String?, boxId: BoxIdType?): GetHistoryRequest {
        val messageRequestType = GetHistoryRequest()
        messageRequestType.boxId = boxId
        messageRequestType.messageId = messageId
        messageRequestType.source = source
        return messageRequestType
    }

    override fun createMoveMessageRequest(
        source: String,
        destination: String,
        vararg messageIds: String
    ): MoveMessageRequest {
        return this.createMoveMessageRequest(source, destination, null, *messageIds as Array<String>)
    }

    override fun createMoveMessageRequest(
        source: String,
        destination: String,
        boxId: BoxIdType?,
        vararg messageIds: String
    ): MoveMessageRequest {
        val moveMessageRequest = MoveMessageRequest()
        moveMessageRequest.boxId = boxId
        moveMessageRequest.destination = destination
        moveMessageRequest.source = source
        val `len$` = messageIds.size

        for (`i$` in 0 until `len$`) {
            val messageId = messageIds[`i$`]
            moveMessageRequest.messageIds.add(messageId)
        }

        return moveMessageRequest
    }

    override fun createGetMessageAcknowledgmentsStatusRequest(messageId: String): GetMessageAcknowledgmentsStatusRequest {
        return this.createGetMessageAcknowledgmentsStatusRequest(messageId, null, null, null)
    }

    override fun createGetMessageAcknowledgmentsStatusRequest(
        messageId: String,
        startIndex: Int?,
        endIndex: Int?,
        boxId: BoxIdType?
    ): GetMessageAcknowledgmentsStatusRequest {
        val getMessageAcknowledgmentsStatusRequest = GetMessageAcknowledgmentsStatusRequest()
        getMessageAcknowledgmentsStatusRequest.boxId = boxId
        getMessageAcknowledgmentsStatusRequest.endIndex = endIndex
        getMessageAcknowledgmentsStatusRequest.messageId = messageId
        getMessageAcknowledgmentsStatusRequest.startIndex = startIndex
        return getMessageAcknowledgmentsStatusRequest
    }

    override fun createDeleteOoORequest(vararg oOoIds: String): DeleteOoORequest {
        return this.createDeleteOoORequest(null, *oOoIds as Array<String>)
    }

    override fun createDeleteOoORequest(oOoIds: List<String>): DeleteOoORequest {
        return this.createDeleteOoORequest(*oOoIds.toTypedArray())
    }

    override fun createDeleteOoORequest(boxId: BoxIdType?, vararg oOoIds: String): DeleteOoORequest {
        val deleteOoORequest = DeleteOoORequest()
        deleteOoORequest.boxId = boxId
        val `len$` = oOoIds.size

        for (`i$` in 0 until `len$`) {
            val oOoId = oOoIds[`i$`]
            deleteOoORequest.ooOIds.add(oOoId)
        }

        return deleteOoORequest
    }

    override fun createDeleteOoORequest(boxId: BoxIdType, oOoIds: List<String>): DeleteOoORequest {
        return this.createDeleteOoORequest(boxId, *oOoIds.toTypedArray())
    }

    override fun createGetOoOListRequest(): GetOoOListRequest {
        return this.createGetOoOListRequest(null)
    }

    override fun createGetOoOListRequest(boxId: BoxIdType?): GetOoOListRequest {
        val getOoOListRequest = GetOoOListRequest()
        getOoOListRequest.boxId = boxId
        return getOoOListRequest
    }

    override fun createInsertOoORequest(
        startDate: DateTime,
        endDate: DateTime,
        vararg substitutes: BoxIdType
    ): InsertOoORequest {
        return this.createInsertOoORequest(null, startDate, endDate, *substitutes as Array<BoxIdType>)
    }

    override fun createInsertOoORequest(
        boxId: BoxIdType?,
        startDate: DateTime,
        endDate: DateTime,
        vararg substitutes: BoxIdType
    ): InsertOoORequest {
        val insertOoORequest = InsertOoORequest()
        insertOoORequest.boxId = boxId
        insertOoORequest.startDate = startDate
        insertOoORequest.endDate = endDate
        val `len$` = substitutes.size

        for (`i$` in 0 until `len$`) {
            val subtitute = substitutes[`i$`]
            insertOoORequest.substitutes.add(subtitute)
        }

        return insertOoORequest
    }

    override fun createDeleteMessageRequest(source: String, messageIds: List<String>): DeleteMessageRequest {
        return this.createDeleteMessageRequest(source, *messageIds.toTypedArray())
    }

    override fun createDeleteMessageRequest(
        source: String,
        boxId: BoxIdType,
        messageIds: List<String>
    ): DeleteMessageRequest {
        return this.createDeleteMessageRequest(source, boxId, *messageIds.toTypedArray())
    }

    override fun createMoveMessageRequest(
        source: String,
        destination: String,
        messageIds: List<String>
    ): MoveMessageRequest {
        return this.createMoveMessageRequest(source, destination, *messageIds.toTypedArray())
    }

    override fun createMoveMessageRequest(
        source: String,
        destination: String,
        boxId: BoxIdType,
        messageIds: List<String>
    ): MoveMessageRequest {
        return this.createMoveMessageRequest(source, destination, boxId, *messageIds.toTypedArray())
    }

    override fun createInsertOoORequest(
        startDate: DateTime,
        endDate: DateTime,
        substitutes: List<BoxIdType>
    ): InsertOoORequest {
        return this.createInsertOoORequest(startDate, endDate, *substitutes.toTypedArray())
    }

    override fun createInsertOoORequest(
        boxId: BoxIdType,
        startDate: DateTime,
        endDate: DateTime,
        substitutes: List<BoxIdType>
    ): InsertOoORequest {
        return this.createInsertOoORequest(boxId, startDate, endDate, *substitutes.toTypedArray())
    }

    companion object {
        private val stringTemplate = arrayOfNulls<String>(0)
        private val boxIdTemplate = arrayOfNulls<BoxIdType>(0)
    }
}
