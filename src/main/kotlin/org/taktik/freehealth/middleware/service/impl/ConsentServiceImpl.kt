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

package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.ConsentType
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentResponse
import be.fgov.ehealth.hubservices.core.v2.PatientIdType
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENTvalues
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.domain.consent.ConsentMessage
import org.taktik.connector.business.kmehrcommons.CDConsentBuilderUtil
import org.taktik.connector.business.kmehrcommons.builders.HcPartyBuilder
import org.taktik.connector.business.wsconsent.builders.RequestObjectBuilderFactory
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.ServiceFactory
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.ConsentService
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

@Service
class ConsentServiceImpl(val stsService: STSService) : ConsentService {

    override fun registerPatientConsent(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientFirstName: String,
        patientLastName: String,
        eidCardNumber: String?,
        isiCardNumber: String?
    ): ConsentMessage {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Consent operations")

        val consentList = ArrayList<CDCONSENT>().apply {
            this.add(CDConsentBuilderUtil.createCDConsent("1.0", CDCONSENTvalues.RETROSPECTIVE))
        }

        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        val consentType =
            RequestObjectBuilderFactory.consentBuilder.createNewConsent(
                makePatient(
                    patientSsin,
                    patientFirstName,
                    patientLastName,
                    eidCardNumber,
                    isiCardNumber
                ), consentList, DateTime(), author
            )
        val consentRequest = RequestObjectBuilderFactory.requestObjectBuilder.createPutRequest(author, consentType)
        val response =
            ServiceFactory.getGenericWsSender().send(getPort(samlToken).apply {
                setPayload(consentRequest as Any)
                setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:PutPatientConsent")
            }).asObject(PutPatientConsentResponse::class.java) as PutPatientConsentResponse

        return ConsentMessage().apply {
            if (!response.acknowledge.isIscomplete) {
                isComplete = false
                errors.addAll(response.acknowledge.errors.map { e ->
                    org.taktik.connector.business.domain.Error(
                        StringUtils.join(e.cds.map { it.value }, ","),
                        e.url,
                        e.description.value,
                        HashMap()
                    )
                })
            } else {
                isComplete = true
                consent = consentType
            }
        }
    }

    override fun getPatientConsent(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        patientFirstName: String,
        patientLastName: String
    ): ConsentMessage {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Consent operations")

        val consentList = ArrayList<CDCONSENT>().apply {
            this.add(CDConsentBuilderUtil.createCDConsent("1.0", CDCONSENTvalues.RETROSPECTIVE))
        }

        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        val consentType =
            RequestObjectBuilderFactory.consentBuilder.createSelectGetPatientConsent(
                makePatient(
                    patientSsin,
                    patientFirstName,
                    patientLastName
                ), consentList
            )
        val consentRequest = RequestObjectBuilderFactory.requestObjectBuilder.createGetRequest(author, consentType)

        val response = ServiceFactory.getGenericWsSender().send(getPort(samlToken).apply {
            setPayload(consentRequest as Any)
            setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsent")
        }).asObject(GetPatientConsentResponse::class.java) as GetPatientConsentResponse

        return ConsentMessage().apply {
            if (!response.acknowledge.isIscomplete) {
                isComplete = false
                errors.addAll(response.acknowledge.errors.map { e ->
                    org.taktik.connector.business.domain.Error(
                        StringUtils.join(e.cds.map { it.value }, ","),
                        e.url,
                        e.description.value,
                        HashMap()
                    )
                })
            } else {
                isComplete = true
                consent = response.consent
            }
        }
    }

    override fun revokePatientConsent(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        existingConsent: ConsentType,
        eidCardNumber: String?,
        isiCardNumber: String?
    ): ConsentMessage {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Consent operations")

        val author = makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName)

        existingConsent.patient.ids.removeIf { id -> IDPATIENTschemes.EID_CARDNO == id.s }
        existingConsent.patient.ids.removeIf { id -> IDPATIENTschemes.ISI_CARDNO == id.s }
        eidCardNumber?.let {
            existingConsent.patient.ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.EID_CARDNO; sv =
                "1.0"; value = it
            })
        }
        isiCardNumber?.let {
            existingConsent.patient.ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.ISI_CARDNO; sv =
                "1.0"; value = it
            })
        }
        existingConsent.revokedate = DateTime()

        val consentRequest =
            RequestObjectBuilderFactory.requestObjectBuilder.createRevokeRequest(author, existingConsent)

        // Service
        val response = ServiceFactory.getGenericWsSender().send(getPort(samlToken).apply {
            setPayload(consentRequest as Any)
            setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:RevokePatientConsent")
        }).asObject(RevokePatientConsentResponse::class.java) as RevokePatientConsentResponse

        return ConsentMessage().apply {
            if (!response.acknowledge.isIscomplete) {
                isComplete = false
                errors.addAll(response.acknowledge.errors.map { e ->
                    org.taktik.connector.business.domain.Error(
                        StringUtils.join(e.cds.map { it.value }, ","),
                        e.url,
                        e.description.value,
                        HashMap()
                    )
                })
            } else {
                isComplete = true
            }
        }
    }

    fun makePatient(ssin: String, firstName: String?, lastName: String?, eid: String? = null, isi: String? = null) =
        PatientIdType().apply {
            familyname = lastName
            firstname = firstName
            ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.INSS
                sv = "1.0"
                value = ssin
            })
            eid?.let { ids.add(IDPATIENT().apply { s = IDPATIENTschemes.EID_CARDNO; sv = "1.0"; value = it }) }
            isi?.let { ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ISI_CARDNO; sv = "1.0"; value = it }) }
        }

    private fun makeAuthor(
        nihii: String?,
        inss: String?,
        firstname: String?,
        lastname: String?
    ): AuthorWithPatientAndPersonType = AuthorWithPatientAndPersonType().apply {
        hcparties.add(
            HcPartyBuilder().idHcPartyId(nihii, "1.0").inssId(inss, "1.0").cdHcPartyCd(
                "persphysician",
                "1.0"
            ).firstname(firstname).lastname(lastname).build()
        )
    }

    val config = ConfigFactory.getConfigValidator(listOf<String>())

    protected fun getPort(token: SAMLToken): GenericRequest = GenericRequest().apply {
        setCredential(token, TokenType.SAML)
        setEndpoint(config.getProperty("endpoint.wsconsent", "\$uddi{uddi:ehealth-fgov-be:business:consent:v1}"))
        addHandlerChain(
            HandlerChainUtil.buildChainWithValidator(
                "validation.incoming.wsconsent.message",
                "/ehealth-hubservices/XSD/hubservices_protocol-2_3.xsd"
            )
        )
        addDefaulHandlerChain()
    }
}
