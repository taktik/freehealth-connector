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

package org.taktik.connector.business.hubv3.service

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.taktik.connector.business.intrahubcommons.security.HubDecryptionHandler
import org.taktik.connector.business.hubv3.service.impl.HubTokenServiceImpl
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.HandlerChain
import org.taktik.connector.technical.ws.domain.HandlerPosition
import org.taktik.connector.technical.ws.domain.TokenType
import org.apache.commons.lang.Validate
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import java.security.KeyStore
import java.util.UUID

object ServiceFactory {
    @Throws(TechnicalConnectorException::class)
    fun getIntraHubPort(
        endPoint: String,
        samlToken: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        soapAction: String
    ): GenericRequest {
        Validate.notNull(samlToken, "Required parameter SAMLToken is null.")
        Validate.notNull(soapAction, "Required parameter SOAPAction is null.")

        return GenericRequest().setEndpoint(endPoint).setSoapAction(soapAction).setCredential(samlToken, TokenType.SAML)
            .addDefaulHandlerChain().addHandlerChain(HandlerChain().apply {
            register(
                HandlerPosition.BEFORE,
                HubDecryptionHandler(
                    CryptoFactory.getCrypto(
                        KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality),
                        KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
                    )
                )
            )
        })
    }
}
