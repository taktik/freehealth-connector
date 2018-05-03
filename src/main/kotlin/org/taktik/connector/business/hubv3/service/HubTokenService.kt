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

import org.taktik.connector.business.intrahubcommons.exception.IntraHubBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
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
import java.security.KeyStore

interface HubTokenService {
    @Throws(TechnicalConnectorException::class)
    fun declareTransaction(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: DeclareTransactionRequest
    ): DeclareTransactionResponse

    @Throws(IntraHubBusinessConnectorException::class, TechnicalConnectorException::class)
    fun putTransaction(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTransactionRequest
    ): PutTransactionResponse

    @Throws(TechnicalConnectorException::class)
    fun revokeTransaction(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeTransactionRequest
    ): RevokeTransactionResponse

    @Throws(TechnicalConnectorException::class)
    fun getTransactionList(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionListRequest
    ): GetTransactionListResponse

    @Throws(TechnicalConnectorException::class, IntraHubBusinessConnectorException::class)
    fun getTransaction(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionRequest
    ): GetTransactionResponse

    @Throws(TechnicalConnectorException::class)
    fun requestPublication(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RequestPublicationRequest
    ): RequestPublicationResponse

    @Throws(TechnicalConnectorException::class)
    fun putHCParty(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutHCPartyRequest
    ): PutHCPartyResponse

    @Throws(TechnicalConnectorException::class)
    fun getHCParty(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetHCPartyRequest
    ): GetHCPartyResponse

    @Throws(TechnicalConnectorException::class)
    fun putPatient(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutPatientRequest
    ): PutPatientResponse

    @Throws(TechnicalConnectorException::class)
    fun getPatient(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientRequest
    ): GetPatientResponse

    @Throws(TechnicalConnectorException::class)
    fun putHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutHCPartyConsentRequest
    ): PutHCPartyConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun getHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetHCPartyConsentRequest
    ): GetHCPartyConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun revokeHCPartyConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeHCPartyConsentRequest
    ): RevokeHCPartyConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun putPatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutPatientConsentRequest
    ): PutPatientConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun getPatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientConsentRequest
    ): GetPatientConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun revokePatientConsent(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokePatientConsentRequest
    ): RevokePatientConsentResponse

    @Throws(TechnicalConnectorException::class)
    fun putTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTherapeuticLinkRequest
    ): PutTherapeuticLinkResponse

    @Throws(TechnicalConnectorException::class)
    fun getTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTherapeuticLinkRequest
    ): GetTherapeuticLinkResponse

    @Throws(TechnicalConnectorException::class)
    fun revokeTherapeuticLink(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeTherapeuticLinkRequest
    ): RevokeTherapeuticLinkResponse

    @Throws(TechnicalConnectorException::class)
    fun putAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutAccessRightRequest
    ): PutAccessRightResponse

    @Throws(TechnicalConnectorException::class)
    fun getAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetAccessRightRequest
    ): GetAccessRightResponse

    @Throws(TechnicalConnectorException::class)
    fun revokeAccessRight(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: RevokeAccessRightRequest
    ): RevokeAccessRightResponse

    @Throws(TechnicalConnectorException::class)
    fun getPatientAuditTrail(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetPatientAuditTrailRequest
    ): GetPatientAuditTrailResponse

    @Throws(TechnicalConnectorException::class, IntraHubBusinessConnectorException::class)
    fun putTransactionSet(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: PutTransactionSetRequest
    ): PutTransactionSetResponse

    @Throws(TechnicalConnectorException::class)
    fun getTransactionSet(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetTransactionSetRequest
    ): GetTransactionSetResponse

    @Throws(TechnicalConnectorException::class)
    fun getLatestUpdate(
        endpoint: String,
        token: SAMLToken,
        keystore: KeyStore,
        passPhrase: String,
        request: GetLatestUpdateRequest
    ): GetLatestUpdateResponse
}
