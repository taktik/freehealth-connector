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

package org.taktik.connector.business.ehbox.v3.validator.impl

import org.taktik.connector.business.ehbox.v3.exception.OoOPublicationException
import org.taktik.connector.business.ehbox.v3.validator.EhboxReplyValidator
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import be.fgov.ehealth.commons.core.v1.LocalisedString
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.ehbox.core.v3.BoxIdType
import be.fgov.ehealth.ehbox.publication.protocol.v3.Recipient
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse
import be.fgov.ehealth.ehbox.publication.protocol.v3.Substitute
import java.util.HashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EhboxReplyValidatorImpl : EhboxReplyValidator {

    @Throws(ConnectorException::class)
    override fun validateReplyStatus(response: ResponseType): Boolean {
        if ("100" != response.status.code && "200" != response.status.code) {
            if ("826" == response.status.code) {
                if (response is SendMessageResponse) {
                    val oooInformation = HashMap<BoxIdType, List<Substitute>>()
                    response.recipients.forEach { recipient ->
                        if (recipient.absentFrom != null) {
                            val receiver = BoxIdType()
                            receiver.id = recipient.id
                            receiver.quality = recipient.quality
                            receiver.type = recipient.type
                            receiver.subType = recipient.subType
                            oooInformation.put(receiver, recipient.substitutes)
                        }
                    }
                    throw OoOPublicationException(TechnicalConnectorExceptionValues.ERROR_WS.message, "826", oooInformation)
                } else {
                    throw this.generateError(response)
                }
            } else {
                throw this.generateError(response)
            }
        } else {
            return true
        }
    }

    private fun generateError(response: ResponseType): TechnicalConnectorException {
        val reasonBuilder = StringBuilder("Received Code[")
        reasonBuilder.append(response.status.code)
        reasonBuilder.append("] Reason: ")
        response.status.messages.forEach { localisedString -> reasonBuilder.append(" ").append(localisedString.value) }

        LOG.error(reasonBuilder.toString())
        return TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, reasonBuilder.toString())
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(EhboxReplyValidatorImpl::class.java)
    }
}
