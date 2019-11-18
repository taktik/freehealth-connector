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

package org.taktik.freehealth.middleware.service

import org.taktik.freehealth.middleware.dto.ehbox.AltKeystore
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.MessageResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessagesResponse
import org.taktik.freehealth.middleware.dto.ehbox.MessageOperationResponse
import java.util.*

interface EhboxService {
    fun getInfos(keystoreId: UUID, tokenId: UUID, passPhrase: String): BoxInfo
    fun getFullMessage(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        boxId: String,
        messageId: String,
        alternateKeystores: List<AltKeystore>? = null
                      ): MessageResponse
    fun sendMessage(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        message: DocumentMessage,
        publicationReceipt: Boolean,
        receptionReceipt: Boolean,
        readReceipt: Boolean
    ): MessageOperationResponse

    fun loadMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        boxId: String,
        limit: Int?,
        alternateKeystores: List<AltKeystore>? = null
                    ): MessagesResponse
    fun moveMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        messageIds: List<String>,
        source: String,
        destination: String
    ): MessageOperationResponse

    fun deleteMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        messageIds: List<String>,
        source: String
    ): MessageOperationResponse
}
