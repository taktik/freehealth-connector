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

package org.taktik.connector.business.ehbox.v3.exception

import org.taktik.connector.technical.exception.ConnectorException
import be.fgov.ehealth.ehbox.core.v3.BoxIdType
import be.fgov.ehealth.ehbox.publication.protocol.v3.Substitute

class OoOPublicationException(
    message: String,
    errorCode: String,
    val oooForwardInformation: Map<BoxIdType, List<Substitute>>
) : ConnectorException(message, errorCode) {
    companion object {
        private val serialVersionUID = 1L
    }
}
