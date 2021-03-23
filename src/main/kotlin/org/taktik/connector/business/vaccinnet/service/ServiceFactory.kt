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

package org.taktik.connector.business.vaccinnet.service

import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import java.net.MalformedURLException

object ServiceFactory {
    private val config = ConfigFactory.getConfigValidator()

    @Throws(MalformedURLException::class, TechnicalConnectorException::class)
    fun getVaccinationService(token: SAMLToken) = GenericRequest().apply {
        setEndpoint(
            config.getProperty(
                "endpoint.vaccinnet.vaccination.v2",
                "https://tfe.kindengezin.be/VaccinnetUPL/wupl/VaccinationService-2_1"
            )
        )
        setCredential(token, TokenType.SAML)
        addDefaulHandlerChain()
    }

}
