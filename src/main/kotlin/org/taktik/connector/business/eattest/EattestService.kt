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

package org.taktik.connector.business.eattest

import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface EattestService {
    //v1
    @Throws(exceptionClasses = [TechnicalConnectorException::class])
    fun sendAttestion(token: SAMLToken, request: be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest): be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse

    //v2
    @Throws(exceptionClasses = [TechnicalConnectorException::class])
    fun sendAttestion(token: SAMLToken, request: be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest): be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse
    @Throws(exceptionClasses = [TechnicalConnectorException::class])
    fun cancelAttestion(token: SAMLToken, request: CancelAttestationRequest): CancelAttestationResponse
}
