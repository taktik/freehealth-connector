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

import be.fgov.ehealth.hubservices.core.v3.*
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import com.google.gson.Gson
import ma.glasnost.orika.MapperFacade
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.Error
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkMessage
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.dto.hub.HcPartyConsentDto
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.hub.PutTransactionResponse
import org.taktik.freehealth.middleware.dto.hub.TransactionSummaryDto
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.common.AuthorDto
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.HubService
import org.taktik.freehealth.middleware.service.STSService
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailRequest
import be.fgov.ehealth.standards.kmehr.cd.v1.*
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashSet

@Service
class HubServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService, val mapper: MapperFacade) : HubService {
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val freehealthHubService: org.taktik.connector.business.hubv3.service.HubTokenService =
        org.taktik.connector.business.hubv3.service.impl.HubTokenServiceImpl(keyDepotService)
    private val nisCodesPerZip =
        Gson().fromJson<Map<String, String>>(
            this.javaClass.getResourceAsStream("/NisCodes.json").bufferedReader(Charsets.UTF_8),
            HashMap<String, String>().javaClass
        )

    override fun getHcPartyConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        hubPackageId: String?
    ): HcPartyConsentDto? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val hcPartyConsent =
            freehealthHubService.getHCPartyConsent(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetHCPartyConsentRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                    select = SelectGetHCPartyConsentType().apply {
                        hcparty = HCPartyIdType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        }
                    }
                })
        return hcPartyConsent.consent?.let {
            mapper.map(it, HcPartyConsentDto::class.java)?.apply {
                hubId = it.author.hcparties.firstOrNull()?.ids?.find { id -> id.s == IDHCPARTYschemes.ID_HCPARTY }?.value
            }
        }
    }

    override fun getPatientConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        hubPackageId: String?
    ): Consent? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val patientConsent =
            freehealthHubService.getPatientConsent(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetPatientConsentRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                    select = SelectGetPatientConsentType().apply {
                        patient =
                            PatientIdType().apply {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = patientSsin
                                })
                            }
                        consent =
                            be.fgov.ehealth.hubservices.core.v3.Consent()
                                .apply {
                                    cds.add(CDCONSENT().apply {
                                        s = CDCONSENTschemes.CD_CONSENTTYPE; sv =
                                        "1.0"; value = CDCONSENTvalues.RETROSPECTIVE
                                    })
                                }
                    }
                })
        return patientConsent.consent?.let {
            mapper.map(it, Consent::class.java)
        }
    }

    override fun registerPatientConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        patientIsiCardNumber: String?,
        hubPackageId: String?
                                       ): PutPatientConsentResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        return freehealthHubService.putPatientConsent(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            PutPatientConsentRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                consent = ConsentType().apply {
                    patient = PatientIdType().apply {
                        ids.add(IDPATIENT().apply {
                            this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                            patientSsin
                        })
                        patientEidCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.EID_CARDNO; this.sv = "1.0"; this.value = patientEidCardNumber
                            })
                        }
                        patientIsiCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.ISI_CARDNO; this.sv = "1.0"; this.value = patientIsiCardNumber
                            })
                        }
                    }
                    cds.add(CDCONSENT().apply {
                        s = CDCONSENTschemes.CD_CONSENTTYPE; sv = "1.0"; value =
                        CDCONSENTvalues.RETROSPECTIVE
                    })
                    author = AuthorType().apply { hcparties.add(request.author.hcparties.first()) }
                }
            })
    }

    override fun revokePatientConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        patientIsiCardNumber: String?,
        hubPackageId: String?
                                       ): RevokePatientConsentResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        return freehealthHubService.revokePatientConsent(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            RevokePatientConsentRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                consent = ConsentType().apply {
                    revokedate = DateTime.now()
                    patient = PatientIdType().apply {
                        ids.add(IDPATIENT().apply {
                            this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                            patientSsin
                        })
                        patientEidCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.EID_CARDNO; this.sv = "1.0"; this.value = patientEidCardNumber
                            })
                        }
                        patientIsiCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.ISI_CARDNO; this.sv = "1.0"; this.value = patientIsiCardNumber
                            })
                        }

                    }
                    cds.add(CDCONSENT().apply {
                        s = CDCONSENTschemes.CD_CONSENTTYPE; sv = "1.0"; value =
                        CDCONSENTvalues.RETROSPECTIVE
                    })
                    author = AuthorType().apply { hcparties.add(request.author.hcparties.first()) }
                }
            })
    }

    override fun registerTherapeuticLink(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        patientIsiCardNumber: String?,
        hubPackageId: String?
    ): PutTherapeuticLinkResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        return freehealthHubService.putTherapeuticLink(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                PutTherapeuticLinkRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                    therapeuticlink = TherapeuticLinkType().apply {
                        cd = CDTHERAPEUTICLINK().apply {
                            s = CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE
                            sv = "1.0"
                            value = "gpconsultation"
                        }
                        hcparty = HCPartyIdType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =  hcpNihii })
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        }
                        patient = PatientIdType().apply {
                            ids.add(IDPATIENT().apply {
                                this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                                patientSsin
                            })
                            patientEidCardNumber?.let {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.EID_CARDNO; this.sv = "1.0"; this.value = patientEidCardNumber
                                })
                            }
                            patientIsiCardNumber?.let {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.ISI_CARDNO; this.sv = "1.0"; this.value = patientIsiCardNumber
                                })
                            }
                        }
                    }
                })
    }

    override fun revokeTherapeuticLink(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        patientIsiCardNumber: String?,
        hubPackageId: String?
                                        ): RevokeTherapeuticLinkResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        return freehealthHubService.revokeTherapeuticLink(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            RevokeTherapeuticLinkRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                therapeuticlink = TherapeuticLinkType().apply {
                    cd = CDTHERAPEUTICLINK().apply {
                        s = CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE
                        sv = "1.0"
                        value = "gpconsultation"
                    }
                    hcparty = HCPartyIdType().apply {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value =  hcpNihii })
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                    }
                    patient = PatientIdType().apply {
                        ids.add(IDPATIENT().apply {
                            this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                            patientSsin
                        })
                        patientEidCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.EID_CARDNO; this.sv = "1.0"; this.value = patientEidCardNumber
                            })
                        }
                        patientIsiCardNumber?.let {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.ISI_CARDNO; this.sv = "1.0"; this.value = patientIsiCardNumber
                            })
                        }

                    }
                }
            })
    }

    override fun getTherapeuticLinks(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        therLinkType: String?,
        from: Instant?,
        to: Instant?,
        hubPackageId: String?
    ): TherapeuticLinkMessage {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val therapeuticLinkResponse =
            freehealthHubService.getTherapeuticLink(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetTherapeuticLinkRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, false)
                    select = SelectGetHCPartyPatientConsentType().apply {
                        therLinkType?.let {
                            cds.add(CDTHERAPEUTICLINK().apply {
                                s =
                                    CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE; sv = "1.0"; value = it
                            })
                        }
                        patientsAndHcparties.add(HCPartyIdType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        })
                        patientsAndHcparties.add(PatientIdType().apply {
                            ids.add(IDPATIENT().apply {
                                this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                                patientSsin
                            })
                        })
                        // We have to disable dates due to a bug in RSW
                        // begindate = from?.let { DateTime(it.toEpochMilli()) } ?: DateTime.now()
                        // enddate = from?.let { DateTime(it.toEpochMilli()) } ?: DateTime.now()
                    }
                })
        val errors = therapeuticLinkResponse.acknowledge.errors.map {
            Error().apply {
                this.url = it.url
                this.descr = it.description.value
            }
        };
        val isComplete = therapeuticLinkResponse.acknowledge.isIscomplete();

        return TherapeuticLinkMessage().apply {
            this.isComplete = isComplete;
            this.errors = errors;
            this.therapeuticLinks = therapeuticLinkResponse.therapeuticlinklist?.therapeuticlinks?.map {
                TherapeuticLink().apply {
                    this.startDate = it.startdate.toLocalDate()
                    this.endDate = it.enddate.toLocalDate()
                    this.type = it.cd.value
                    this.comment = it.comment
                    this.hcParty = HcParty().apply {
                        this.setIds(it.hcparty.ids)
                    }
                    this.patient = org.taktik.connector.business.common.domain.Patient().apply {
                        this.inss = it.patient.ids.find { idpatient -> idpatient.s.name == "INSS" } ?.value
                    }
                }
            } ?: listOf()
        }
    }

    override fun putPatient(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        firstName: String,
        lastName: String,
        gender: Gender,
        dateOfBirth: LocalDateTime,
        hubPackageId: String?
    ): Patient? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val transaction =
            freehealthHubService.putPatient(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                PutPatientRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                    patient = PersonType().apply {
                        firstnames.add(firstName)
                        familyname = lastName
                        sex =
                            SexType().apply {
                                cd =
                                    CDSEX().apply { sv = "1.0"; value = CDSEXvalues.fromValue(gender.name) }
                            }
                        ids.add(IDPATIENT().apply {
                            this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value =
                            patientSsin
                        })
                        birthdate =
                            DateType().apply {
                                date =
                                    org.joda.time.DateTime(
                                        dateOfBirth.year,
                                        dateOfBirth.monthValue,
                                        dateOfBirth.dayOfMonth,
                                        0,
                                        0
                                    )
                            }
                    }
                })
        return transaction.patient?.let {
            Patient().apply {
                this.firstName = it.firstnames.firstOrNull()
                this.lastName = it.familyname
                this.gender = it.sex?.cd?.value?.value()?.let { Gender.valueOf(it) } ?: Gender.unknown
                ssin = it.ids.find { it.s == IDPATIENTschemes.ID_PATIENT }?.value
                addresses = it.addresses?.map { mapper.map(it, Address::class.java) }?.toMutableSet() ?: HashSet()
            }
        }
    }

    override fun getPatient(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        hubPackageId: String?
    ): Patient? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val patient =
            freehealthHubService.getPatient(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetPatientRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                    select = SelectGetPatientType().apply {
                        patient =
                            PatientIdType().apply {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = patientSsin
                                })
                            }
                    }
                })
        return patient.patient?.let {
            Patient().apply {
                firstName = it.firstnames.firstOrNull()
                lastName = it.familyname
                gender = it.sex?.cd?.value?.value()?.let { Gender.valueOf(it) } ?: Gender.unknown
                ssin = it.ids.find { it.s == IDPATIENTschemes.ID_PATIENT }?.value
                addresses = it.addresses?.map { mapper.map(it, Address::class.java) }?.toMutableSet() ?: HashSet()
            }
        }
    }

    override fun getTransaction(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String?,
        sv: String,
        sl: String,
        value: String
    ): Kmehrmessage? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val transaction =
            freehealthHubService.getTransaction(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetTransactionRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, breakTheGlassReason, true)
                    select = SelectGetTransactionType().apply {
                        patient =
                            PatientIdType().apply {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = ssin
                                })
                            }
                        transaction = TransactionBaseType().apply {
                            id =
                                IDKMEHR().apply {
                                    this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl =
                                    sl; this.value = value
                                }
                        }
                    }
                })

        return transaction.kmehrmessage
    }

    override fun revokeTransaction(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String?,
        sv: String,
        sl: String,
        value: String
    ): String {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val revokeresp =
            freehealthHubService.revokeTransaction(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            RevokeTransactionRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, breakTheGlassReason, true)
                select = SelectRevokeTransactionType().apply {
                    patient =
                        PatientIdType().apply {
                            ids.add(IDPATIENT().apply {
                                this.s =
                                    IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = ssin
                            })
                        }
                    transaction = TransactionBaseType().apply {
                        id =
                            IDKMEHR().apply {
                                this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl =
                                sl; this.value = value
                            }
                    }
                }
            }

        )
        return MarshallerHelper(
            ResponseType::class.java,
            ResponseType::class.java
        ).toXMLByteArray(revokeresp.response).toString(Charsets.UTF_8)
    }

    override fun putTransaction(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        transaction: ByteArray,
        hubPackageId: String?
    ): PutTransactionResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val marshallerHelper = MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java)
        return freehealthHubService.putTransaction(
            endpoint,
            hubId,
            hubApplication,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            PutTransactionRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                kmehrmessage =
                    marshallerHelper.toObject(transaction)
            }).let { tr ->
            org.taktik.freehealth.middleware.domain.hub.PutTransactionResponse(
                tr.transaction?.ids,
                tr.acknowledge.errors.map { e -> org.taktik.freehealth.middleware.domain.common.Error(e.cds.firstOrNull()?.value,  e.description.value, e.url, null, mapOf()) }
                                                                              )
        }
    }

    override fun getTransactionsList(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String?,
        from: Long?,
        to: Long?,
        authorNihii: String?,
        authorSsin: String?,
        isGlobal: Boolean
    ): List<TransactionSummaryDto> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val transactionList =
            freehealthHubService.getTransactionList(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetTransactionListRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, breakTheGlassReason, false)
                    select = SelectGetTransactionListType().apply {
                        patient =
                            PatientIdType().apply {
                                ids.add(IDPATIENT().apply {
                                    s = IDPATIENTschemes.INSS; sv =
                                    "1.0"; value = ssin
                                })
                            }
                        transaction = TransactionWithPeriodType().apply {
                            if(isGlobal) { searchtype = LocalSearchType.GLOBAL }
                            from?.let { begindate = DateTime(from) }
                            to?.let { enddate = DateTime(to) }
                            if (!StringUtils.isEmpty(authorNihii) || !StringUtils.isEmpty(authorSsin)) {
                                author = AuthorType().apply {
                                    hcparties.add(HcpartyType().apply {
                                        cds.add(CDHCPARTY().apply {
                                            s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value =
                                            "*"
                                        })
                                        ids.add(IDHCPARTY().apply {
                                            if (StringUtils.isEmpty(authorSsin)) {
                                                s = IDHCPARTYschemes.INSS; sv = "1.0"; value = authorSsin
                                            } else {
                                                s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = authorNihii
                                            }
                                        })
                                    })
                                }
                            }
                        }
                    }
                })

        return transactionList.kmehrheader?.folder?.transactions?.map {
            TransactionSummaryDto().apply {
                author = mapper.map(it.author, AuthorDto::class.java)
                ids = it.ids.map { KmehrId().apply { s = it?.s?.value(); sv = it?.sv; sl = it?.sl; value = it?.value } }
                cds = it.cds.map { KmehrCd().apply { s = it?.s?.value(); sv = it?.sv; sl = it?.sl; value = it?.value } }
                date = it.date.millis
                time = it.time.millis
                recorddatetime = it.recorddatetime.millis
                iscomplete = it.isIscomplete
                isvalidated = it.isIsvalidated

                desc = it.cds.find { it.s == CDTRANSACTIONschemes.CD_TRANSACTION }?.value
                authorsList = it.author.hcparties.filterNot { it.cds.any { it.s == CDHCPARTYschemes.CD_HCPARTY && it.value == "hub" } }.map { listOf(it.firstname, it.familyname, it.name).filterNotNull().joinToString(" ")  }.joinToString(",")
            }
        } ?: listOf()
    }

    override fun getTransactionSet(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String?,
        sv: String,
        sl: String,
        value: String
    ): Kmehrmessage? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val transaction =
            freehealthHubService.getTransactionSet(
                endpoint,
                samlToken,
                keystoreId,
                stsService.getKeyStore(keystoreId, passPhrase)!!,
                passPhrase,
                GetTransactionSetRequest().apply {
                    request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, breakTheGlassReason, true)
                    select = SelectGetTransactionType().apply {
                        patient =
                            PatientIdType().apply {
                                ids.add(IDPATIENT().apply {
                                    this.s =
                                        IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = ssin
                                })
                            }
                        transaction = TransactionBaseType().apply {
                            id =
                                IDKMEHR().apply {
                                    this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl =
                                    sl; this.value = value
                                }
                        }
                    }
                })

        return transaction.kmehrmessage
    }

    override fun putTransactionSet(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        transaction: ByteArray,
        hubPackageId: String?
    ): PutTransactionSetResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Hub operations")
        val marshallerHelper = MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java)
        return freehealthHubService.putTransactionSet(
            endpoint,
            hubId,
            hubApplication,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            PutTransactionSetRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                kmehrmessage =
                    marshallerHelper.toObject(transaction)
            })
    }

    private fun createRequestListType(
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        hubPackageId: String?,
        breakTheGlassReason: String?,
        encrypted: Boolean = false
                                     ): RequestListType {
        require(breakTheGlassReason == null || breakTheGlassReason.length in 10..200) { "Invalid break the glass reason" }
        return RequestListType().apply {
            date = DateTime.now()
            time = DateTime.now()
            id =
                IDKMEHR().apply {
                    s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                    "$hcpNihii.${Instant.now().toEpochMilli()}"
                }
            author = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "persphysician" })

                    if (encrypted) {
                        ids.add(IDHCPARTY().apply {
                            s = IDHCPARTYschemes.ID_ENCRYPTION_ACTOR; sv = "1.0"; value =
                            hcpNihii.substring(0, 8)
                        })
                        cds.add(CDHCPARTY().apply {
                            s = CDHCPARTYschemes.CD_ENCRYPTION_ACTOR; sv = "1.1"; value =
                            IdentifierType.NIHII.getType(48)
                        })
                    }
                    firstname = hcpFirstName
                    familyname = hcpLastName
                    addresses.add(AddressType().apply {
                        cds.add(CDADDRESS().apply { s = CDADDRESSschemes.CD_ADDRESS; sv = "1.1"; value = "work" })
                        country =
                            CountryType().apply {
                                cd =
                                    CDCOUNTRY().apply { s = CDCOUNTRYschemes.CD_FED_COUNTRY; sv = "1.2"; value = "be" }
                            }
                        zip = hcpZip
                        nis = nisCodesPerZip[hcpZip]
                    })
                })
                if(StringUtils.isNotEmpty(hubPackageId ?: config.getProperty("hub.package.id") ?: "ACC_")) {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply {
                            s = IDHCPARTYschemes.LOCAL; sl = "endusersoftwareinfo"; sv =
                            "1.0"; value = hubPackageId ?: config.getProperty("hub.package.id") ?: "ACC_"
                        })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "application" })
                    })
                }
            }
            breakTheGlassReason?.let { breaktheglass = it }
        }
    }

    override fun getPatientAuditTrail(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String?,
        breakTheGlassReason: String?,
        from: Long?,
        to: Long?,
        authorNihii: String?,
        authorSsin: String?,
        isGlobal: Boolean,
        sv: String?,
        sl: String?,
        value: String?,
        hubPackageId: String?): GetPatientAuditTrailResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        return freehealthHubService.getPatientAuditTrail(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            GetPatientAuditTrailRequest().apply{
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                select = SelectGetPatientAuditTrailType().apply {
                    from?.let {begindate = DateTime(from) }
                    to?.let { enddate = DateTime(to) }
                    ssin?.let{ patient =
                        PatientIdType().apply {
                            ids.add(IDPATIENT().apply {
                                this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = ssin
                            })
                        }
                    }
                    if (StringUtils.isNotEmpty(authorNihii) && StringUtils.isNotEmpty(authorSsin)) {
                        hcparty =
                            HCPartyIdType().apply {
                            ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.ID_HCPARTY; this.sv = "1.0"; this.value = authorNihii })
                            ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.INSS; this.sv = "1.0"; this.value = authorSsin })
                        }
                    }
                    if (StringUtils.isNotEmpty(sv) && StringUtils.isNotEmpty(sl) && StringUtils.isNotEmpty(sl)) {
                        transaction = TransactionBaseType().apply {
                            id =
                                IDKMEHR().apply {
                                    this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl =
                                    sl; this.value = value
                                }
                        }
                    }
                    breakTheGlassReason?.let { breaktheglass = it }
                }
            }
        )
    }

    override fun putAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        accessNihii: String?, //hcp to allow/disallow
        accessSsin: String?, //hcp to allow/disallow
        accessRight: String, //allow, disallow
        hubPackageId: String?
    ): PutAccessRightResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        return freehealthHubService.putAccessRight(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            PutAccessRightRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                accessright = AccessRightType().apply {
                    if (StringUtils.isNotEmpty(accessNihii) && StringUtils.isNotEmpty(accessSsin)) {
                        hcparty = be.fgov.ehealth.hubservices.core.v3.HcpartyType().apply {
                            accessNihii?.let { ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.ID_HCPARTY; this.sv = "1.0"; this.value = it }) }
                            accessSsin?.let { ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.INSS; this.sv = "1.0"; this.value = accessSsin }) }
                        }
                    }
                    transaction = TransactionIdType().apply {
                        this.ids.add(IDKMEHR().apply { this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl = sl; this.value = value })
                    }
                    cd = CDACCESSRIGHT().apply{ this.s = CDACCESSRIGHTschemes.CD_ACCESSRIGHT; this.sv = "1.0"; this.value = CDACCESSRIGHTvalues.fromValue(accessRight) }
                }
            }

        )
    }

    override fun getAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        hubPackageId: String?
    ): GetAccessRightResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        return freehealthHubService.getAccessRight(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            GetAccessRightRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                select = SelectGetAccessRightType().apply {
                    transaction = TransactionIdType().apply {
                        this.ids.add(IDKMEHR().apply { this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl = sl; this.value = value })
                    }
                }
            }
        )
    }

    override fun revokeAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        accessNihii: String?, //hcp to allow/disallow
        accessSsin: String?, //hcp to allow/disallow
        hubPackageId: String?
    ): RevokeAccessRightResponse {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        return freehealthHubService.revokeAccessRight(
            endpoint,
            samlToken,
            keystoreId,
            stsService.getKeyStore(keystoreId, passPhrase)!!,
            passPhrase,
            RevokeAccessRightRequest().apply {
                request = createRequestListType(hcpLastName, hcpFirstName, hcpNihii, hcpSsin, hcpZip, hubPackageId, null, true)
                accessright = SelectRevokeAccessRightType().apply {
                    transaction = TransactionIdType().apply {
                        this.ids.add(IDKMEHR().apply { this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl = sl; this.value = value })
                    }
                    if (StringUtils.isNotEmpty(accessNihii) && StringUtils.isNotEmpty(accessSsin)) {
                        hcparty = be.fgov.ehealth.hubservices.core.v3.HcpartyType().apply {
                            accessNihii?.let { ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.ID_HCPARTY; this.sv = "1.0"; this.value = it }) }
                            accessSsin?.let { ids.add(IDHCPARTY().apply { this.s = IDHCPARTYschemes.INSS; this.sv = "1.0"; this.value = accessSsin }) }
                        }
                    }
                }
            }

        )
    }
}
