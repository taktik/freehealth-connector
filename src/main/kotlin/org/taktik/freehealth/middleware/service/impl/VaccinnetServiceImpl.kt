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

import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.MomentType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.QuantityType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.SenderType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.StandardType
import org.taktik.connector.business.domain.kmehr.v20120701.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import org.taktik.connector.business.domain.kmehr.v20120701.s
import org.taktik.connector.business.domain.vaccinnet.VaccineInjection
import org.taktik.connector.business.vaccinnet.AddVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.AddVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.GetVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.GetVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationRequestType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationResponseType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.VaccinnetService
import org.taktik.freehealth.utils.FuzzyValues
import org.taktik.freehealth.utils.FuzzyValues.justDate
import org.taktik.freehealth.utils.FuzzyValues.justTime
import java.math.BigDecimal
import java.util.*

@Service
class VaccinnetServiceImpl(private val stsService: STSService) : VaccinnetService {
    private val freehealthVaccinnetService: org.taktik.connector.business.vaccinnet.service.VaccinnetService =
        org.taktik.connector.business.vaccinnet.service.impl.VaccinnetServiceImpl()

    override fun addVaccinations(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpQuality: String,
        patientId: String,
        patientFirstName: String,
        patientLastName: String,
        patientDateOfBirth: Long,
        softwareId: String,
        vaccinnetId: String,
        injections: List<VaccineInjection>
    ): AddVaccinationsResponseType {
        val now = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(FuzzyValues.currentFuzzyDateTime)
        val marshallerHelper = MarshallerHelper<Kmehrmessage, Kmehrmessage>(Kmehrmessage::class.java, Kmehrmessage::class.java)
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val addVaccinationsRequest = AddVaccinationsRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.base64EncodedKmehrmessage.addAll(injections.map { inj -> AddVaccinationsRequestType.Base64EncodedKmehrmessage().apply {
                value = marshallerHelper.toXMLByteArray( Kmehrmessage().apply {
                    header = HeaderType().apply {
                        standard = StandardType().apply {
                            cd = CDSTANDARD().apply { value = "20120701" }
                        }
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "${hcpNihii}.${System.currentTimeMillis()}" })
                        date = now.justDate()
                        time = now.justTime()
                        sender = SenderType().apply {
                            hcparties.add(HcpartyType().apply {
                                ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; value = hcpNihii })
                                cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY);  value = hcpQuality })
                                name = hcpName
                            })
                        }
                        recipients.add(RecipientType().apply {
                            hcparties.add(HcpartyType().apply {
                                cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY);  value = "orgpublichealth" })
                                name = "VAZG"
                            })
                        })
                    }
                    folders.add(
                        FolderType().apply {
                            ids.add(IDKMEHR().apply { s= IDKMEHRschemes.ID_KMEHR; value="1" })
                            patient = PersonType().apply{
                                ids.add(IDPATIENT().apply{s= IDPATIENTschemes.ID_PATIENT; sv="1.0"; value=patientId})
                                firstnames.add(patientFirstName)
                                familyname = patientLastName
                                birthdate = DateType().apply { date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(patientDateOfBirth) }
                                sex = SexType().apply { cd = CDSEX().apply { s= "CD-SEX"; sv="1.0"; value= CDSEXvalues.MALE } }
                            }
                            transactions.add(
                                TransactionType().apply {
                                    ids.add(IDKMEHR().apply { s= IDKMEHRschemes.ID_KMEHR; value="1" })
                                    cds.add(CDTRANSACTION().apply { s(CDTRANSACTIONschemes.CD_TRANSACTION); value = "vaccination" /* TODO validate with our vaccinnet friend */ })
                                    date = now.justDate()
                                    time = now.justTime()
                                    author = AuthorType().apply {
                                        hcparties.add(HcpartyType().apply {
                                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; value = hcpNihii })
                                            cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY); value = hcpQuality })
                                            name = hcpName
                                        })
                                    }
                                    isIscomplete = true
                                    isIsvalidated = true
                                    headingsAndItemsAndTexts.add(ItemType().apply {
                                        ids.add(IDKMEHR().apply { s= IDKMEHRschemes.ID_KMEHR; value = "1" })
                                        cds.add(CDITEM().apply { s(CDITEMschemes.CD_ITEM) ; value = "vaccine" })
                                        contents.add(ContentType().apply {
                                            medicinalproduct = ContentType.Medicinalproduct().apply {
                                                intendedcd = CDDRUGCNK().apply { value = inj.intendedCd }
                                                intendedname = inj.intendedName
                                            }
                                        })
                                        beginmoment = MomentType().apply { date = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(inj.date) }
                                        quantity = QuantityType().apply { decimal = BigDecimal.ONE }
                                        batch = inj.batch
                                    })
                                }
                            )
                        }
                    )
                })
            }})
        }
        return freehealthVaccinnetService.addVaccinations(samlToken, addVaccinationsRequest)
    }

    override fun removeVaccination(keystoreId: UUID, tokenId: UUID, passPhrase: String, patientId: String, softwareId: String, vaccinnetId: String, vaccinationId: String): RemoveVaccinationResponseType {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val removeVaccinationsRequest = RemoveVaccinationRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.vaccinationId = vaccinationId
        }
        return freehealthVaccinnetService.removeVaccination(samlToken, removeVaccinationsRequest)
    }

    override fun getVaccinations(keystoreId: UUID, tokenId: UUID, passPhrase: String, patientId: String, softwareId: String, vaccinnetId: String, since: Long): GetVaccinationsResponseType {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val getVaccinationsRequest = GetVaccinationsRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.vaccinationDateSince = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(since)
        }
        return freehealthVaccinnetService.getVaccinations(samlToken, getVaccinationsRequest)
    }
}
