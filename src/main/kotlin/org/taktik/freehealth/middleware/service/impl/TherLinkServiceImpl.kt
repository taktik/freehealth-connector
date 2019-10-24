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

import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse
import be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse
import be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.therlink.domain.TherapeuticLinkMessage
import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.ProofTypeValues
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.requests.BinaryProof
import org.taktik.connector.business.therlink.domain.requests.GetTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.PutTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.RevokeTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.TherapeuticLinkStatus
import org.taktik.connector.business.therlink.mappers.RequestObjectMapper
import org.taktik.connector.business.therlink.mappers.ResponseObjectMapper
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.BeIDCredential
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.TherLinkService
import java.util.*
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.exception.TechnicalConnectorException
import java.net.MalformedURLException
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse
import org.taktik.connector.business.therlink.domain.HasTherapeuticLinkMessage
import org.taktik.connector.business.therlink.domain.requests.HasTherapeuticLinkRequest
import org.taktik.connector.business.therlink.exception.TherLinkBusinessConnectorException
import javax.xml.soap.SOAPException


@Service
class TherLinkServiceImpl(private val stsService: STSService) : TherLinkService {
    private val responseObjectMapper = ResponseObjectMapper()
    private val requestObjectMapper = RequestObjectMapper()

    override fun getAllTherapeuticLinks(
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
        isiCardNumber: String?,
        startDate: Date?,
        endDate: Date?,
        type: String?,
        sign: Boolean?
                                       ): TherapeuticLinkMessage? {
        val linkBuilder = TherapeuticLink.Builder()
            .withHcParty(makeHcParty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName))
            .withPatient(makePatient(patientSsin, eidCardNumber, isiCardNumber, patientFirstName, patientLastName))
            .withStartDateTime(startDate?.let { DateTime(it.time) })
            .withEndDateTime(endDate?.let { DateTime(it.time) })
            .withStatus(TherapeuticLinkStatus.ACTIVE)

        type?.let { linkBuilder.withType(it) }

        return getAllTherapeuticLinksWithQueryLink(
            keystoreId,
            tokenId,
            passPhrase,
            linkBuilder.build(),
            sign)
    }

    override fun getAllTherapeuticLinksWithQueryLink(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        queryLink: TherapeuticLink,
        sign: Boolean?
                                                    ): TherapeuticLinkMessage? =
        getAllTherapeuticLinksWithQueryLink(
            keystoreId,
            tokenId,
            passPhrase,
            queryLink,
            makeProof(sign ?: false, queryLink.patient, queryLink.hcParty)
                                           )

    private fun getAllTherapeuticLinksWithQueryLink(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        queryLink: TherapeuticLink,
        proof: Proof?
                                                   ): TherapeuticLinkMessage? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Therlink operations")

        val query = GetTherapeuticLinkRequest(DateTime.now(),getNihii(queryLink.hcParty)!!, Author().apply { hcParties.add(queryLink.hcParty!!) }, queryLink, 100, proof)

        val rawResponse =
            org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender()
                .send(getTherLinkPort(samlToken).apply {
                    setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:GetTherapeuticLink")
                    setPayload(requestObjectMapper.mapGetTherapeuticLinkRequest(query))
                })

        val response = rawResponse.asObject(GetTherapeuticLinkResponse::class.java)

        return if (response.acknowledge.isIscomplete) {
            TherapeuticLinkMessage(responseObjectMapper.mapJaxbToGetTherapeuticLinkResponse(response)
                .listOfTherapeuticLinks)
        } else {
            TherapeuticLinkMessage().apply {
                this.isComplete = response.acknowledge.isIscomplete
                this.errors =
                    responseObjectMapper.mapAcknowledge(response.acknowledge)
                        .listOfErrors.filter { it.errorCode != "NIP.META.TlServiceBean" }
                        .map {
                            org.taktik.connector.business.domain.Error(
                                it.errorCode,
                                null,
                                it.errorDescription,
                                mapOf()
                                                                      )
                        }
            }
        }
    }

    override fun doesLinkExist(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        therLink: TherapeuticLink
    ): TherapeuticLink? = getAllTherapeuticLinksWithQueryLink(
        keystoreId,
        tokenId,
        passPhrase,
        therLink,
        false
    )?.let { if (it.isComplete) it.therapeuticLinks?.firstOrNull() else null }

    @Throws(TechnicalConnectorException::class, TherLinkBusinessConnectorException::class)
    override fun hasTherapeuticLink(
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
        isiCardNumber: String?,
        startDate: Date?,
        endDate: Date?,
        therLinkType: String?
        ): HasTherapeuticLinkMessage? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")


        try {
            val response: HasTherapeuticLinkResponse =
            org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender()
                .send(getTherLinkPort(samlToken).apply {
                    setPayload(requestObjectMapper.mapHasTherapeuticLinkRequest(HasTherapeuticLinkRequest(
                        DateTime(),
                        hcpNihii,
                        makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                        makeTherapeuticLink(
                            therLinkType ?: "gpconsultation",
                            makeHcParty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                            makePatient(patientSsin, eidCardNumber, isiCardNumber, patientFirstName, patientLastName),
                            startDate?.let { DateTime(it.time) }, //Maybe not supported
                            endDate?.let { DateTime(it.time) }, //Maybe not supported
                            null
                                           )
                                                                                                         ))
                              )
                    setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:HasTherapeuticLink")
                }).asObject(HasTherapeuticLinkResponse::class.java)

            return HasTherapeuticLinkMessage().apply {
                isComplete = response.acknowledge.isIscomplete
                if (isComplete) {
                    result = response.isValue
                } else {
                    errors =
                        responseObjectMapper.mapAcknowledge(response.acknowledge)
                            .listOfErrors.filter { it.errorCode != "NIP.META.TlServiceBean" }
                            .map {
                                org.taktik.connector.business.domain.Error(
                                    it.errorCode,
                                    null,
                                    it.errorDescription,
                                    mapOf()
                                                                          )
                            }
                }
            }
        } catch (urlException: MalformedURLException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, urlException.message)
        } catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, soapException.message)
        }

    }


    override fun registerTherapeuticLink(
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
        isiCardNumber: String?,
        start: Date?,
        end: Date?,
        therLinkType: String?,
        comment: String?,
        sign: Boolean?,
        proofType: ProofTypeValues?
                                        ): TherapeuticLinkMessage {


        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val therLink =
            makeTherapeuticLink(
                therLinkType ?: "gpconsultation",
                makeHcParty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                makePatient(patientSsin, eidCardNumber, isiCardNumber, patientFirstName, patientLastName),
                start?.let { DateTime(it.time) },
                end?.let { DateTime(it.time) },
                comment
                               )

        doesLinkExist(keystoreId, tokenId, passPhrase, therLink)?.let {
            revokeLink(
                keystoreId,
                tokenId,
                passPhrase,
                it,
                false,
                proofType
                      )
        }

        val response =
            org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender()
                .send(getTherLinkPort(samlToken).apply {
                    setPayload(
                        requestObjectMapper.mapPutTherapeuticLinkRequest(
                            PutTherapeuticLinkRequest(
                                DateTime(),
                                hcpNihii,
                                makeAuthor(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                                therLink,
                                makeProof(sign ?: false, therLink.patient, therLink.hcParty, proofType)
                                                     )
                                                                        )
                              )
                    setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:PutTherapeuticLink")
                }).asObject(PutTherapeuticLinkResponse::class.java)

        return TherapeuticLinkMessage().apply {
            isComplete = response.acknowledge.isIscomplete
            if (isComplete) {
                therapeuticLinks = listOf(therLink)
            } else {
                errors =
                    responseObjectMapper.mapAcknowledge(response.acknowledge)
                        .listOfErrors.filter { it.errorCode != "NIP.META.TlServiceBean" }
                        .map {
                            org.taktik.connector.business.domain.Error(
                                it.errorCode,
                                null,
                                it.errorDescription,
                                mapOf()
                                                                      )
                        }
            }
        }
    }

    override fun revokeLink(
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
        isiCardNumber: String?,
        start: Date?,
        end: Date?,
        therLinkType: String?,
        comment: String?,
        sign: Boolean?,
        proofType: ProofTypeValues?
                           ): TherapeuticLinkMessage? {
        return doesLinkExist(
            keystoreId,
            tokenId,
            passPhrase,
            makeTherapeuticLink(
                therLinkType ?: "gpconsultation",
                makeHcParty(hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                makePatient(
                    patientSsin,
                    eidCardNumber,
                    isiCardNumber,
                    patientFirstName,
                    patientLastName
                           ),
                start?.let { DateTime(it.time) },
                end?.let { DateTime(it.time) },
                comment
                               )
                            )?.let { revokeLink(keystoreId, tokenId, passPhrase, it, sign, proofType) }
    }

    override fun revokeLink(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        therLink: TherapeuticLink,
        sign: Boolean?,
        proofType: ProofTypeValues?
                           ): TherapeuticLinkMessage {

        val patient = requireNotNull(therLink.patient)
        val hcParty = requireNotNull(therLink.hcParty)

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")

        val mapRevokeTherapeuticLinkRequest = requestObjectMapper.mapRevokeTherapeuticLinkRequest(
            RevokeTherapeuticLinkRequest(
                DateTime(),
                getNihii(hcParty)!!,
                makeAuthor(
                    getNihii(hcParty),
                    getSsin(hcParty),
                    hcParty.firstName,
                    hcParty.familyName
                          ),
                makeTherapeuticLink(
                    therLink.type!!,
                    hcParty,
                    patient,
                    therLink.startDate?.toDateTime(LocalTime.MIDNIGHT) ?: DateTime(),
                    therLink.endDate?.toDateTime(LocalTime.MIDNIGHT),
                    therLink.comment
                                   ),
                makeProof(
                    sign ?: false, patient, hcParty, proofType
                         )
                                        )
                                                                                                 )

        val response =
            org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(getTherLinkPort(samlToken).apply {
                setPayload(mapRevokeTherapeuticLinkRequest)
                setSoapAction("urn:be:fgov:ehealth:therlink:protocol:v1:RevokeTherapeuticLink")
            }).asObject(RevokeTherapeuticLinkResponse::class.java) as RevokeTherapeuticLinkResponse

        return TherapeuticLinkMessage().apply {
            isComplete = response.acknowledge.isIscomplete
            if (isComplete) {
                therapeuticLinks = listOf(therLink)
            } else {
                errors =
                    responseObjectMapper.mapAcknowledge(response.acknowledge)
                        .listOfErrors.filter { it.errorCode != "NIP.META.TlServiceBean" }
                        .map {
                            org.taktik.connector.business.domain.Error(
                                it.errorCode,
                                null,
                                it.errorDescription,
                                mapOf()
                                                                      )
                        }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun getSsin(hcParty: HcParty?) =
        hcParty?.ids?.find { id -> IDHCPARTYschemes.INSS == id.s }?.value ?: hcParty?.inss

    @Suppress("DEPRECATION")
    private fun getNihii(hcParty: HcParty?) =
        hcParty?.ids?.find { id -> IDHCPARTYschemes.ID_HCPARTY == id.s }?.value ?: hcParty?.nihii

    private fun makeProofForEidSigning(patient: Patient, hcp: HcParty, signature: BeIDCredential): Proof {
        val proof = Proof(ProofTypeValues.EIDSIGNING.value)

        val therapeuticLink = makeTherapeuticLink("ignored", hcp, patient, DateTime(), DateTime().plusMinutes(5), null)
        val contentToSign = requestObjectMapper.createTherapeuticLinkAsXmlString(therapeuticLink)
        val signatureBuilder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.CAdES)
        val options = HashMap<String, Any>()
        options["encapsulate"] = java.lang.Boolean.TRUE
        val signatureBytes = signatureBuilder.sign(signature, contentToSign.toByteArray(), options)
        val binaryProof = BinaryProof("CMS", signatureBytes)
        proof.binaryProof = binaryProof

        return proof
    }

    private fun makeTherapeuticLink(
        therLinkType: String,
        hcParty: HcParty,
        patient: Patient,
        startDate: DateTime? = null,
        endDate: DateTime? = null,
        comment: String? = null
                                   ): TherapeuticLink = TherapeuticLink(patient, hcParty, therLinkType).apply {
        startDate?.let { this.startDate = LocalDate(it) }
        endDate?.let { this.endDate = LocalDate(it) }
        this.comment = comment
    }

    private fun makePatient(niss: String, eid: String?, isi: String?, firstName: String, lastName: String): Patient {
        return Patient.Builder().withEid(eid).withIsiPlus(isi).withFamilyName(lastName).withFirstName(firstName)
            .withInss(niss).build()
    }

    /*private fun makeAuthorWithPatient(nihii: String, inss: String, firstname: String, lastname: String): AuthorWithPatientAndPersonType {
        val author = AuthorWithPatientAndPersonType()
        author.hcparties.add(HcPartyBuilder().idHcPartyId(nihii, "1.0").inssId(inss, "1.0").cdHcPartyCd("persphysician", "1.0").firstname(firstname).lastname(lastname).build())

        return author
    }*/

    private fun makeProof(sign: Boolean, patient: Patient?, hcParty: HcParty?, proofType:ProofTypeValues? = null): Proof? {
        requireNotNull(patient)
        requireNotNull(hcParty)

        Proof((proofType ?: ProofTypeValues.EIDREADING).value).apply { binaryProof = null }
        return when {
            sign -> makeProofForEidSigning(
                patient,
                hcParty,
                BeIDCredential.getInstance("Therapeutic Link", "Signature")
                                          )
            patient.eidCardNumber != null -> Proof(ProofTypeValues.EIDREADING.value)
            patient.isiCardNumber != null -> Proof(ProofTypeValues.ISIREADING.value)
            else -> null
        }
    }

    private fun makeAuthor(nihii: String?, inss: String?, firstname: String?, lastname: String?): Author {
        val author = Author()
        author.hcParties.add(makeHcParty(nihii, inss, firstname, lastname))

        return author
    }

    private fun makeHcParty(nihii: String?, inss: String?, firstname: String?, lastname: String?) =
        HcParty.Builder().withNihii(nihii).withInss(inss).withType("persphysician").withFirstName(firstname).withFamilyName(
            lastname
                                                                                                                           ).build()

    val config = ConfigFactory.getConfigValidator(listOf("endpoint.therlink"))

    internal fun getTherLinkPort(token: SAMLToken): GenericRequest =
        GenericRequest().setEndpoint(config.getProperty("endpoint.therlink")).setCredential(
            token,
            TokenType.SAML
                                                                                           ).addDefaulHandlerChain().addHandlerChain(
            HandlerChainUtil.buildChainWithValidator(
                "validation.incoming.therlink.message",
                "/ehealth-hubservices/XSD/hubservices_protocol-2_2.xsd"
                                                    )
                                                                                                                                    )
}
