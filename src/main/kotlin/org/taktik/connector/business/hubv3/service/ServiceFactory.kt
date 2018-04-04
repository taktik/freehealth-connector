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
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.intrahubcommons.security.HubDecryptionHandler
import org.taktik.connector.business.hubv3.service.impl.HubTokenServiceImpl
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.SessionUtil
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.HandlerChain
import org.taktik.connector.technical.ws.domain.HandlerPosition
import org.taktik.connector.technical.ws.domain.TokenType
import java.util.ArrayList
import org.apache.commons.lang.Validate
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import java.security.KeyStore

object ServiceFactory {
    internal val INTRAHUB_PROTOCOL = "/ehealth-hubservices/XSD/hubservices_protocol-3_2.xsd"

    val intraHubService: HubTokenService
        get() = HubTokenServiceImpl()

    @Throws(TechnicalConnectorException::class)
    fun getIntraHubPort(endPoint: String, token: SAMLToken, keystore: KeyStore, passPhrase: String, soapAction: String): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        Validate.notNull(soapAction, "Required parameter SOAPAction is null.")
        return GenericRequest().setEndpoint(endPoint).setSoapAction(soapAction).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(addHubServiceHandlerChain(keystore, passPhrase, HandlerChainUtil.buildChainWithValidator("validation.incoming.intrahubv3.message", "/ehealth-hubservices/XSD/hubservices_protocol-3_2.xsd")))
    }

    @Throws(TechnicalConnectorException::class)
    private fun addHubServiceHandlerChain(keystore: KeyStore, passPhrase: String , chain: HandlerChain): HandlerChain {
        val credential = KeyStoreCredential(keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        chain.register(HandlerPosition.BEFORE, HubDecryptionHandler(crypto))

        return chain
    }

}
