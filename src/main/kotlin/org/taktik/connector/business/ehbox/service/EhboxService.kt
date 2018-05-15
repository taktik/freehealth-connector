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

package org.taktik.connector.business.ehbox.service

import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoORequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoOResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoORequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoOResponse
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageResponse
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface EhboxService {
    @Throws(ConnectorException::class)
    fun getBoxInfo(token: SAMLToken, getBoxInfoRequest: GetBoxInfoRequest): GetBoxInfoResponse

    @Throws(ConnectorException::class)
    fun getFullMessage(token: SAMLToken, request: GetFullMessageRequest): GetFullMessageResponse

    @Throws(ConnectorException::class)
    fun getFullMessage(token: SAMLToken, request: MessageRequestType): GetFullMessageResponse

    @Throws(ConnectorException::class)
    fun getMessageHistory(token: SAMLToken, request: MessageRequestType): GetHistoryResponse

    @Throws(ConnectorException::class)
    fun getMessageHistory(token: SAMLToken, request: GetHistoryRequest): GetHistoryResponse

    @Throws(ConnectorException::class)
    fun getMessageList(token: SAMLToken, request: GetMessagesListRequest): GetMessagesListResponse

    @Throws(ConnectorException::class)
    fun getMessageAcknowledgmentsStatusResponse(
        token: SAMLToken,
        request: GetMessageAcknowledgmentsStatusRequest
    ): GetMessageAcknowledgmentsStatusResponse

    @Throws(ConnectorException::class)
    fun deleteMessage(token: SAMLToken, request: DeleteMessageRequest): DeleteMessageResponse

    @Throws(ConnectorException::class)
    fun moveMessage(token: SAMLToken, request: MoveMessageRequest): MoveMessageResponse

    @Throws(ConnectorException::class)
    fun insertOoO(token: SAMLToken, request: InsertOoORequest): InsertOoOResponse

    @Throws(ConnectorException::class)
    fun deleteOoO(token: SAMLToken, request: DeleteOoORequest): DeleteOoOResponse

    @Throws(ConnectorException::class)
    fun getOoOList(token: SAMLToken, request: GetOoOListRequest): GetOoOListResponse

    @Throws(ConnectorException::class)
    fun getAllEhboxesMessagesList(
        token: SAMLToken,
        request: GetAllEhboxesMessagesListRequest
    ): GetAllEhboxesMessagesListResponse

    @Throws(EhboxBusinessConnectorException::class, ConnectorException::class)
    fun sendMessage(token: SAMLToken, request: SendMessageRequest): SendMessageResponse
}
