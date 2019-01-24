package org.taktik.connector.business.therlink.mappers

import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkSelectType
import be.fgov.ehealth.hubservices.core.v2.HCPartyIdType
import be.fgov.ehealth.hubservices.core.v2.PatientIdType
import be.fgov.ehealth.hubservices.core.v2.ProofType
import be.fgov.ehealth.hubservices.core.v2.RequestType
import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkType
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHOD
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHODschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHODvalues
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDPROOF
import be.fgov.ehealth.standards.kmehr.cd.v1.CDPROOFschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINK
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINKschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedDataType
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedValueType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.slf4j.LoggerFactory
import org.taktik.connector.business.common.domain.Patient
import org.taktik.connector.business.kmehrcommons.HcPartyUtil
import org.taktik.connector.business.therlink.domain.Author
import org.taktik.connector.business.therlink.domain.HcParty
import org.taktik.connector.business.therlink.domain.Proof
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkRequestType
import org.taktik.connector.business.therlink.domain.jaxb.Therapeuticlink
import org.taktik.connector.business.therlink.domain.requests.GetTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.PutTherapeuticLinkRequest
import org.taktik.connector.business.therlink.domain.requests.RevokeTherapeuticLinkRequest
import org.taktik.connector.technical.utils.MarshallerHelper
import java.math.BigDecimal
import java.util.*

class RequestObjectMapper {
    private val LOG = LoggerFactory.getLogger(RequestObjectMapper::class.java)

    fun mapPutTherapeuticLinkToXML(request: PutTherapeuticLinkRequest) = this.generateXML(
        mapPutTherapeuticLinkRequest(request),
        be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest::class.java
    )

    fun mapRevokeTherapeuticLinkToXML(request: RevokeTherapeuticLinkRequest) = this.generateXML(
        this.mapRevokeTherapeuticLinkRequest(request),
        be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest::class.java
    )

    fun mapGetTherapeuticLinkToXml(request: GetTherapeuticLinkRequest) = this.generateXML(
        this.mapGetTherapeuticLinkRequest(request),
        be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest::class.java
    )

    fun mapPutTherapeuticLinkRequest(request: PutTherapeuticLinkRequest) =
        be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest().apply {
            this.request = mapRequest(request.dateTime, request.id, request.author, 0)
            this.therapeuticlink = mapTherapeuticLinkType(request.link)
            this.prooves.addAll(mapProoves(request.proofs))
        }

    fun mapGetTherapeuticLinkRequest(request: GetTherapeuticLinkRequest) =
        be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest().apply {
            this.request =
                mapRequest(
                    request.dateTime,
                    request.id,
                    request.author,
                    if (request.maxRows != 0) request.maxRows else 999
                )
            select = mapGetTherapeuticLinkSelectType(request.link)
            prooves.addAll(mapProoves(request.proofs))
        }

    fun mapRevokeTherapeuticLinkRequest(request: TherapeuticLinkRequestType) =
        be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest().apply {
            this.request = mapRequest(request.dateTime, request.id, request.author, 0)
            therapeuticlink = mapTherapeuticLinkType(request.link)
            prooves.addAll(mapProoves(request.proofs))
        }

    private fun mapProoves(proofs: List<Proof?>) = proofs.filterNotNull().map { p ->
        ProofType().apply {
            cd = CDPROOF().apply {
                s = CDPROOFschemes.CD_PROOFTYPE
                sv = "1.1"
                value = p.type
            }
            binaryproof = p.binaryProof?.let { bp ->
                Base64EncryptedDataType().apply {
                    cd = CDENCRYPTIONMETHOD().apply {
                        s = CDENCRYPTIONMETHODschemes.CD_ENCRYPTION_METHOD
                        sv = "1.0"
                        value = CDENCRYPTIONMETHODvalues.fromValue(bp.method)
                    }
                    base64EncryptedValue = Base64EncryptedValueType().apply {
                        encoding = bp.method
                        value = bp.binary
                    }
                }
            }
        }
    }

    private fun mapGetTherapeuticLinkSelectType(link: TherapeuticLink?): GetTherapeuticLinkSelectType? = link?.let {
        GetTherapeuticLinkSelectType().apply {
            begindate = it.startDate?.let { it.toDateTime(LocalTime.MIDNIGHT) }
            enddate = it.endDate?.let { it.toDateTime(LocalTime.MIDNIGHT) }
            if(link.type != null){
                cds.add(CDTHERAPEUTICLINK().apply {
                    s = CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE
                    sv = "1.0"
                    value = link.type
                })
            }
            it.status?.let { therapeuticlinkstatus = it.toString().toLowerCase(Locale.getDefault()) }
            patientsAndHcparties.add(mapPatient(it.patient))
            patientsAndHcparties.add(mapHcPartyIdType(it.hcParty))
        }
    }

    private fun mapRequest(date: DateTime, id: String, author: Author, maxRows: Int) = RequestType().apply {
        this.id = IDKMEHR().apply {
            s = IDKMEHRschemes.ID_KMEHR
            sv = "1.0"
            value = id
        }
        this.author = AuthorWithPatientAndPersonType().apply {
            this.hcparties.addAll(author.hcParties.map { mapToHcpartyType(it) })
        }
        this.date = DateTime(date)
        time = DateTime(date)
        if (maxRows != 0) {
            maxrows = BigDecimal(maxRows)
        }
    }

    private fun mapPatient(patient: Patient) = PatientIdType().apply {
        familyname = patient.lastName
        firstname = patient.firstName
        if (StringUtils.isEmpty(patient.firstName) && StringUtils.isEmpty(patient.lastName) && patient is org.taktik.connector.business.therlink.domain.Patient) {
            name = patient.name
        }
        ids.add(IDPATIENT().apply {
            s = IDPATIENTschemes.INSS
            sv = "1.0"
            value = patient.inss
        })
        if (patient.eidCardNumber != null) {
            ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.EID_CARDNO
                sv = "1.0"
                value = patient.eidCardNumber
            })
        }
        if (patient.sisCardNumber != null) {
            ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.SIS_CARDNO
                sv = "1.0"
                value = patient.sisCardNumber
            })
        }
        if (patient.isiCardNumber != null) {
            ids.add(IDPATIENT().apply {
                s = IDPATIENTschemes.ISI_CARDNO
                sv = "1.0"
                value = patient.isiCardNumber
            })
        }
    }

    private fun mapTherapeuticLinkType(link: TherapeuticLink?) = link?.let {
        TherapeuticLinkType().apply {
            cd = CDTHERAPEUTICLINK().apply {
                s = CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE
                sv = "1.1"
                value = it.type
            }
            comment = it.comment
            enddate = it.endDate?.let { it.toDateTime(LocalTime.MIDNIGHT) }
            startdate = it.startDate?.let { it.toDateTime(LocalTime.MIDNIGHT) }
            hcparties.add(mapHcPartyIdType(it.hcParty))
            patient = mapPatient(it.patient)
        }
    }

    fun createTherapeuticLinkAsXmlString(therLink: TherapeuticLink): String {
        val therLinkRoot = Therapeuticlink().apply {
            cd = CDTHERAPEUTICLINK().apply {
                s = CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE
                sv = "1.0"
                value = therLink.type
            }
            comment = therLink.comment
            enddate = therLink.endDate?.toDateTimeAtCurrentTime()
            startdate = therLink.startDate?.toDateTimeAtCurrentTime()
            patient = mapPatient(therLink.patient)
            hcparties.add(mapHcPartyIdType(therLink.hcParty))
        }
        return generateXML(therLinkRoot, Therapeuticlink::class.java)
    }

    fun mapHcPartyIdType(hcpartyType: HcParty) = HCPartyIdType().apply {
        name = hcpartyType.name
        familyname = hcpartyType.familyName
        firstname = hcpartyType.firstName
        ids.addAll(mapIdsAndPropertiesToIdList(hcpartyType))
        cd = createHcPartyType(hcpartyType.type)
    }

    fun mapToHcpartyType(hcp: HcParty) = HcpartyType().apply {
        name = hcp.name
        familyname = hcp.familyName
        firstname = hcp.firstName
        ids.addAll(mapIdsAndPropertiesToIdList(hcp))
        cds.add(createHcPartyType(hcp.type))
    }

    @Suppress("DEPRECATION")
    private fun mapIdsAndPropertiesToIdList(hcp: HcParty) =
        hcp.ids.plus(listOf(hcp.inss?.let { IDHCPARTY().apply { sv = "1.0"; s = IDHCPARTYschemes.INSS; value = it } },
                            hcp.nihii?.let {
                                IDHCPARTY().apply {
                                    sv = "1.0"; s = IDHCPARTYschemes.ID_HCPARTY; value =
                                    it
                                }
                            },
                            hcp.cbe?.let {
                                IDHCPARTY().apply {
                                    sv = "1.0"; s = IDHCPARTYschemes.ID_HCPARTY; value =
                                    it
                                }
                            },
                            hcp.ehp?.let {
                                IDHCPARTY().apply {
                                    sv = "1.0"; s = IDHCPARTYschemes.ID_HCPARTY; value =
                                    it
                                }
                            },
                            hcp.applicationID?.let {
                                IDHCPARTY().apply {
                                    sv = "1.0"; s = IDHCPARTYschemes.LOCAL; value = it; sl =
                                    "application_ID"
                                }
                            })).filterNotNull().distinctBy { Triple(it.s, it.sl, it.value) }

    private fun createHcPartyType(type: String?): CDHCPARTY {
        var tempType = type
        if (type != null) {
            tempType = type.trim { it <= ' ' }
        }

        return HcPartyUtil.buildCd("1.1", tempType, CDHCPARTYschemes.CD_HCPARTY, null as String?)
    }

    private fun <T> generateXML(`object`: T, clazz: Class<T>): String {
        val marshaller = MarshallerHelper(clazz, clazz)
        val xml = marshaller.toString(`object`)
        LOG.info("generated XML$xml")
        return xml
    }
}
