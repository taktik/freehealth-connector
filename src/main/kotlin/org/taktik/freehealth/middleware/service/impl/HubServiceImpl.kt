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

import be.fgov.ehealth.hubservices.core.v3.GetTransactionListRequest
import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.PatientIdType
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequest
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse
import be.fgov.ehealth.hubservices.core.v3.RequestType
import be.fgov.ehealth.hubservices.core.v3.SelectGetTransactionListType
import be.fgov.ehealth.hubservices.core.v3.SelectGetTransactionType
import be.fgov.ehealth.hubservices.core.v3.TransactionBaseType
import be.fgov.ehealth.hubservices.core.v3.TransactionWithPeriodType
import be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import com.google.gson.Gson
import ma.glasnost.orika.MapperFacade
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.business.kmehrcommons.HcPartyUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.domain.Consent
import org.taktik.freehealth.middleware.domain.HcPartyConsent
import org.taktik.freehealth.middleware.domain.TransactionSummary
import org.taktik.freehealth.middleware.dto.common.AuthorDto
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.service.HubService
import org.taktik.freehealth.middleware.service.STSService
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class HubServiceImpl(val stsService: STSService, val mapper: MapperFacade) : HubService {
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val freehealthHubService: org.taktik.connector.business.hubv3.service.HubTokenService = org.taktik.connector.business.hubv3.service.impl.HubTokenServiceImpl()
    private val nisCodesPerZip = Gson().fromJson<Map<String,String>>(this.javaClass.getResourceAsStream("/NisCodes.json").bufferedReader(Charsets.UTF_8), HashMap<String, String>().javaClass)

    override fun checkHcPartyConsent(token: String, inss: String, nihii: String): HcPartyConsent {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPatientConsent(token: String, nissPatient: String): Consent {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerPatientConsent(token: String, dateOfBirth: LocalDateTime, niss: String, firstName: String, lastName: String, gender: Gender) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerTherapeuticLink(token: String, ssin: String, start: Date, comment: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTherapeuticLinks(token: String, nissPatient: String, inamiDoctor: String, nissDoctor: String): List<TherapeuticLinkDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTransactionsList(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, from: Long?, to: Long?, authorNihii: String?, authorSsin: String?, isGlobal: Boolean): List<TransactionSummary> {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        val transactionList = freehealthHubService.getTransactionList(endpoint, samlToken, stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase , GetTransactionListRequest().apply {
            request = createRequestType(hcpNihii, hcpSsin, hcpZip, false)
            select = SelectGetTransactionListType().apply {
                patient = PatientIdType().apply { ids.add(IDPATIENT().apply { s = IDPATIENTschemes.INSS; sv = "1.0"; value = ssin }) }
                transaction = TransactionWithPeriodType().apply {
                    from?.let { begindate = DateTime(from) }
                    to?.let { enddate = DateTime(to) }
                    if (!StringUtils.isEmpty(authorNihii) || !StringUtils.isEmpty(authorSsin)) {
                        author = AuthorType().apply {
                            hcparties.add(HcpartyType().apply {
                                cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "*" })
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
            TransactionSummary().apply {
                author = mapper.map(it.author, AuthorDto::class.java)
                ids = it.ids.map { KmehrId().apply { s =  it?.s?.value(); sv = it?.sv; sl = it?.sl; value = it?.value } }
                cds = it.cds.map { KmehrCd().apply { s =  it?.s?.value(); sv = it?.sv; sl = it?.sl; value = it?.value } }
                date = it.date.millis
                time = it.time.millis
                recorddatetime = it.recorddatetime.millis
                iscomplete = it.isIscomplete
                isvalidated = it.isIsvalidated

            }
        } ?: listOf()
    }

    override fun getTransaction(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, sv: String, sl: String, value: String): String {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        val transaction = freehealthHubService.getTransaction(endpoint, samlToken, stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase , GetTransactionRequest().apply {
            request = createRequestType(hcpNihii, hcpSsin, hcpZip, true)
            select = SelectGetTransactionType().apply {
                patient = PatientIdType().apply { ids.add(IDPATIENT().apply { this.s = IDPATIENTschemes.INSS; this.sv = "1.0"; this.value = ssin }) }
                transaction = TransactionBaseType().apply {
                    id = IDKMEHR().apply { this.s = IDKMEHRschemes.LOCAL; this.sv = sv; this.sl = sl; this.value = value }
                }
            }
        })

        return MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java).toXMLByteArray(transaction.kmehrmessage).toString(Charsets.UTF_8)
    }


    override fun putPatient(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, niss: String, firstName: String, lastName: String, gender: Gender, dateOfBirth: LocalDateTime) {
    }

    override fun putTransaction(endpoint: String, hubId : Long, hubApplication : String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, transaction: String): PutTransactionResponse {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Hub operations")
        val marshallerHelper = MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java)
        return freehealthHubService.putTransaction(endpoint, hubId, hubApplication, samlToken, stsService.getKeyStore(keystoreId, passPhrase)!!, passPhrase , PutTransactionRequest().apply {
            request = createRequestType(hcpNihii, hcpSsin, hcpZip, true)
            kmehrmessage = marshallerHelper.toObject(transaction.toByteArray(Charsets.UTF_8))
        })
    }

    private fun createRequestType(hcpNihii: String, hcpSsin: String, hcpZip: String, encrypted: Boolean = false): RequestType {
        return RequestType().apply {
            date = DateTime.now()
            time = DateTime.now()
            id = IDKMEHR().apply { s=IDKMEHRschemes.ID_KMEHR; sv="1.0"; value="${hcpNihii}.${Instant.now().toEpochMilli()}" }
            author = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "persphysician" })

                    if (encrypted) {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_ENCRYPTION_ACTOR; sv = "1.0"; value = hcpNihii.substring(0,8)})
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_ENCRYPTION_ACTOR; sv = "1.1"; value = IdentifierType.NIHII.getType(48) })
                    }
                    firstname = "Antoine"
                    familyname = "Baudoux"
                    addresses.add(AddressType().apply {
                        cds.add(CDADDRESS().apply { s=CDADDRESSschemes.CD_ADDRESS; sv="1.1"; value = "work" })
                        country = CountryType().apply { cd = CDCOUNTRY().apply { s = CDCOUNTRYschemes.CD_FED_COUNTRY; sv = "1.2"; value = "be" } }
                        zip = hcpZip
                        nis = nisCodesPerZip[hcpZip]
                    })
                })
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.LOCAL; sl = "endusersoftwareinfo"; sv = "1.0"; value = config.getProperty("package.name") ?: "iCure" })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "application" })
                })
            }
        }
    }
}