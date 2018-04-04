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

interface RequestBuilder {

    fun createDeleteMessageRequest(source: String, vararg messageIds: String): DeleteMessageRequest

    fun createGetAllEhboxesMessagesListRequest(source: String, startIndex: Int?, endIndex: Int?): GetAllEhboxesMessagesListRequest

    fun createGetMessagesListRequest(source: String, startIndex: Int, endIndex: Int, boxId: BoxIdType): GetMessagesListRequest

    fun createGetMessagesListRequest(source: String): GetMessagesListRequest

    fun createDeleteMessageRequest(source: String, boxId: BoxIdType?, vararg messageIds: String): DeleteMessageRequest

    fun createBoxInfoRequestForDefaultBox(): GetBoxInfoRequest

    fun createBoxInfoRequest(boxId: BoxIdType): GetBoxInfoRequest

    fun createAllEhboxesMessagesListRequest(source: String): GetAllEhboxesMessagesListRequest

    fun createAllEhboxesMessagesListRequest(source: String, startIndex: Int?, endIndex: Int?): GetAllEhboxesMessagesListRequest

    fun createMessageRequestType(messageId: String): MessageRequestType

    fun createMessageRequestType(messageId: String, source: String): MessageRequestType

    fun createMessageRequestType(messageId: String, source: String, boxId: BoxIdType?): MessageRequestType

    fun createGetFullMessageRequest(messageId: String): GetFullMessageRequest

    fun createGetFullMessageRequest(messageId: String, source: String): GetFullMessageRequest

    fun createGetFullMessageRequest(messageId: String, source: String, boxId: BoxIdType?): GetFullMessageRequest

    fun createGetHistoryRequest(messageId: String): GetHistoryRequest

    fun createGetHistoryRequest(messageId: String, source: String?): GetHistoryRequest

    fun createGetHistoryRequest(messageId: String, source: String?, boxId: BoxIdType?): GetHistoryRequest

    fun createMoveMessageRequest(source: String, destination: String, vararg messageIds: String): MoveMessageRequest

    fun createMoveMessageRequest(source: String, destination: String, boxId: BoxIdType?, vararg messageIds: String): MoveMessageRequest

    fun createGetMessageAcknowledgmentsStatusRequest(messageId: String): GetMessageAcknowledgmentsStatusRequest

    fun createGetMessageAcknowledgmentsStatusRequest(messageId: String, startIndex: Int?, endIndex: Int?, boxId: BoxIdType?): GetMessageAcknowledgmentsStatusRequest

    fun createDeleteOoORequest(vararg oOoIds: String): DeleteOoORequest

    fun createDeleteOoORequest(oOoIds: List<String>): DeleteOoORequest

    fun createDeleteOoORequest(boxId: BoxIdType?, vararg oOoIds: String): DeleteOoORequest

    fun createDeleteOoORequest(boxId: BoxIdType, oOoIds: List<String>): DeleteOoORequest

    fun createGetOoOListRequest(): GetOoOListRequest

    fun createGetOoOListRequest(boxId: BoxIdType?): GetOoOListRequest

    fun createInsertOoORequest(startDate: DateTime, endDate: DateTime, vararg substitutes: BoxIdType): InsertOoORequest

    fun createInsertOoORequest(boxId: BoxIdType?, startDate: DateTime, endDate: DateTime, vararg substitutes: BoxIdType): InsertOoORequest

    fun createDeleteMessageRequest(source: String, messageIds: List<String>): DeleteMessageRequest

    fun createDeleteMessageRequest(source: String, boxId: BoxIdType, messageIds: List<String>): DeleteMessageRequest

    fun createMoveMessageRequest(source: String, destination: String, messageIds: List<String>): MoveMessageRequest

    fun createMoveMessageRequest(source: String, destination: String, boxId: BoxIdType, messageIds: List<String>): MoveMessageRequest

    fun createInsertOoORequest(startDate: DateTime, endDate: DateTime, substitutes: List<BoxIdType>): InsertOoORequest

    fun createInsertOoORequest(boxId: BoxIdType, startDate: DateTime, endDate: DateTime, substitutes: List<BoxIdType>): InsertOoORequest
}
