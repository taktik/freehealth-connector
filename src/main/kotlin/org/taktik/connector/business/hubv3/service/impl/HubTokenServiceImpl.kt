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

package org.taktik.connector.business.hubv3.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import org.taktik.connector.business.intrahubcommons.exception.IntraHubBusinessConnectorException
import org.taktik.connector.business.intrahubcommons.helper.ServiceHelper
import org.taktik.connector.business.intrahubcommons.security.IntrahubEncryptionUtil
import org.taktik.connector.business.hubv3.service.HubTokenService
import org.taktik.connector.business.hubv3.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionResponse
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightRequest
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentRequest
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentResponse
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyRequest
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyResponse
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateRequest
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateResponse
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailRequest
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentResponse
import be.fgov.ehealth.hubservices.core.v3.GetPatientRequest
import be.fgov.ehealth.hubservices.core.v3.GetPatientResponse
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkRequest
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkResponse
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListRequest
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListResponse
import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.GetTransactionResponse
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetRequest
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightRequest
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentRequest
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentResponse
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyRequest
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyResponse
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentRequest
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentResponse
import be.fgov.ehealth.hubservices.core.v3.PutPatientRequest
import be.fgov.ehealth.hubservices.core.v3.PutPatientResponse
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkRequest
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkResponse
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetRequest
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationRequest
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightRequest
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentRequest
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentResponse
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentRequest
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkRequest
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionResponse
import org.apache.commons.logging.LogFactory
import javax.xml.soap.SOAPException
import javax.xml.ws.WebServiceException
import org.slf4j.LoggerFactory
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.IdentifierType
import java.security.KeyStore
import java.util.UUID

class HubTokenServiceImpl(private val keyDepotService: KeyDepotService) : HubTokenService, ConfigurationModuleBootstrap.ModuleBootstrapHook {

    val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)
    val log = LogFactory.getLog(this::class.java)

    @Throws(TechnicalConnectorException::class)
    override fun declareTransaction(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: DeclareTransactionRequest
    ): DeclareTransactionResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:DeclareTransaction",
            request,
            DeclareTransactionResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class, IntraHubBusinessConnectorException::class)
    override fun putTransaction(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTransactionRequest
    ): PutTransactionResponse {
        val helper = MarshallerHelper(PutTransactionRequest::class.java, PutTransactionRequest::class.java)
        LOG.debug("PutTransactionRequest unsigned request :" + helper.toString(request))
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransaction",
            IntrahubEncryptionUtil.encryptFolder(
                request,
                getCrypto(keystoreId, keystore, passPhrase),
                hubId,
                hubApplication,
                keyDepotManager.getEtk(IdentifierType.EHP, hubId, hubApplication ?: "", keystoreId, false)
            ),
            PutTransactionResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun revokeTransaction(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeTransactionRequest
    ): RevokeTransactionResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTransaction",
            request,
            RevokeTransactionResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getTransactionList(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionListRequest
    ): GetTransactionListResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionList",
            request,
            GetTransactionListResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class, IntraHubBusinessConnectorException::class)
    override fun getTransaction(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionRequest
    ): GetTransactionResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransaction",
            request,
            GetTransactionResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun requestPublication(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RequestPublicationRequest
    ): RequestPublicationResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RequestPublication",
            request,
            RequestPublicationResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putHCParty(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutHCPartyRequest
    ): PutHCPartyResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCParty",
            request,
            PutHCPartyResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getHCParty(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetHCPartyRequest
    ): GetHCPartyResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCParty",
            request,
            GetHCPartyResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putPatient(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutPatientRequest
    ): PutPatientResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatient",
            request,
            PutPatientResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getPatient(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientRequest
    ): GetPatientResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatient",
            request,
            GetPatientResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutHCPartyConsentRequest
    ): PutHCPartyConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutHCPartyConsent",
            request,
            PutHCPartyConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetHCPartyConsentRequest
    ): GetHCPartyConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetHCPartyConsent",
            request,
            GetHCPartyConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun revokeHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeHCPartyConsentRequest
    ): RevokeHCPartyConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeHCPartyConsent",
            request,
            RevokeHCPartyConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putPatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutPatientConsentRequest
    ): PutPatientConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutPatientConsent",
            request,
            PutPatientConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getPatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientConsentRequest
    ): GetPatientConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientConsent",
            request,
            GetPatientConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun revokePatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokePatientConsentRequest
    ): RevokePatientConsentResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokePatientConsent",
            request,
            RevokePatientConsentResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTherapeuticLinkRequest
    ): PutTherapeuticLinkResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTherapeuticLink",
            request,
            PutTherapeuticLinkResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTherapeuticLinkRequest
    ): GetTherapeuticLinkResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTherapeuticLink",
            request,
            GetTherapeuticLinkResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun revokeTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeTherapeuticLinkRequest
    ): RevokeTherapeuticLinkResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeTherapeuticLink",
            request,
            RevokeTherapeuticLinkResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun putAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutAccessRightRequest
    ): PutAccessRightResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutAccessRight",
            request,
            PutAccessRightResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetAccessRightRequest
    ): GetAccessRightResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetAccessRight",
            request,
            GetAccessRightResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun revokeAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeAccessRightRequest
    ): RevokeAccessRightResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:RevokeAccessRight",
            request,
            RevokeAccessRightResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getPatientAuditTrail(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientAuditTrailRequest
    ): GetPatientAuditTrailResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetPatientAuditTrail",
            request,
            GetPatientAuditTrailResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class, IntraHubBusinessConnectorException::class)
    override fun putTransactionSet(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTransactionSetRequest
    ): PutTransactionSetResponse {
        log.debug(ConnectorXmlUtils.toString(request))
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:PutTransactionSet",
            IntrahubEncryptionUtil.encryptFolder(
                request,
                getCrypto(keystoreId, keystore, passPhrase),
                hubId,
                hubApplication,
                keyDepotManager.getEtk(IdentifierType.EHP, hubId, hubApplication ?: "", keystoreId, false)
            ),
            PutTransactionSetResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getTransactionSet(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionSetRequest
    ): GetTransactionSetResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetTransactionSet",
            request,
            GetTransactionSetResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun getLatestUpdate(
        endpoint: String,
        token: SAMLToken,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        request: GetLatestUpdateRequest
    ): GetLatestUpdateResponse {
        return this.executeOperation(
            token,
            endpoint,
            keystoreId,
            keystore,
            passPhrase,
            "urn:be:fgov:ehealth:intrahub:protocol:v3:GetLatestUpdate",
            request,
            GetLatestUpdateResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    private fun <T> executeOperation(
        token: SAMLToken,
        endpoint: String,
        keystoreId: UUID,
        keystore: KeyStore,
        passPhrase: String,
        operation: String,
        request: Any,
        clazz: Class<T>
    ): T {
        try {
            val service =
                ServiceFactory.getIntraHubPort(endpoint, token, keystoreId, keystore, passPhrase, operation).setPayload(request)
            val genericResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            return genericResponse.asObject(clazz)
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        } catch (ex: WebServiceException) {
            throw ServiceHelper.handleWebServiceException(ex)
        }
    }

    private fun getCrypto(keystoreId: UUID, keystore: KeyStore, passPhrase: String): Crypto {
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        return CryptoFactory.getCrypto(credential, hokPrivateKeys)
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(DeclareTransactionRequest::class.java)
        JaxbContextFactory.initJaxbContext(DeclareTransactionResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetAccessRightRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetAccessRightResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetHCPartyConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetHCPartyConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetHCPartyRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetHCPartyResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientAuditTrailRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientAuditTrailResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetPatientResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetTherapeuticLinkRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetTherapeuticLinkResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetTransactionListRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetTransactionListResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetTransactionRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetTransactionResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutAccessRightRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutAccessRightResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutHCPartyConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutHCPartyConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutHCPartyRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutHCPartyResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutPatientConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutPatientConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutPatientRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutPatientResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutTherapeuticLinkRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutTherapeuticLinkResponse::class.java)
        JaxbContextFactory.initJaxbContext(PutTransactionRequest::class.java)
        JaxbContextFactory.initJaxbContext(PutTransactionResponse::class.java)
        JaxbContextFactory.initJaxbContext(RequestPublicationRequest::class.java)
        JaxbContextFactory.initJaxbContext(RequestPublicationResponse::class.java)
        JaxbContextFactory.initJaxbContext(RevokeAccessRightRequest::class.java)
        JaxbContextFactory.initJaxbContext(RevokeAccessRightResponse::class.java)
        JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(RevokePatientConsentRequest::class.java)
        JaxbContextFactory.initJaxbContext(RevokePatientConsentResponse::class.java)
        JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkRequest::class.java)
        JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkResponse::class.java)
        JaxbContextFactory.initJaxbContext(RevokeTransactionRequest::class.java)
        JaxbContextFactory.initJaxbContext(RevokeTransactionResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetLatestUpdateRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetLatestUpdateResponse::class.java)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(HubTokenServiceImpl::class.java)
    }
}
