package be.vlaanderen.zg.vaccinnet.batchuploadtest.service

import be.vaccinnet.wupl.uplvaccinatiegegevens.ExtraInfo
import be.vaccinnet.wupl.uplvaccinatiegegevens.GetAanvullingVaccinatieGegevensRequest
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Organisation
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Patient
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.DateUtils
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.KmehrUtils
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.SoapUtils
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.XmlDataUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.ws.client.core.WebServiceTemplate
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes.*
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.KmehrmessageType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.xml.bind.JAXBException
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.XMLGregorianCalendar

@Service
class AanleverenAanvullendeVaccinatieGegevensService(
    private val webServiceTemplate: WebServiceTemplate,
    @Value("\${vaccinnet.batchupload.vaccination_minus_days.aanvullen}") private val minusVaccinationDays: Int
) {
    fun run(organisation: Organisation, patient: Patient) {
        val requestPayload = getAanvullingVaccinatieGegevensRequest(organisation, patient)
        SoapUtils.validateMessage(requestPayload)
        webServiceTemplate.marshalSendAndReceive(requestPayload)
    }

    private fun getAanvullingVaccinatieGegevensRequest(organisation: Organisation, patient: Patient): GetAanvullingVaccinatieGegevensRequest {
        val request = GetAanvullingVaccinatieGegevensRequest()
        request.extraInfo = configureExtraInfo()
        try {
            request.kmehrmessage = configureKmehr(organisation, patient)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JAXBException) {
            e.printStackTrace()
        } catch (e: DatatypeConfigurationException) {
            e.printStackTrace()
        }
        return request
    }

    private fun configureExtraInfo(): ExtraInfo {
        val extraInfo = ExtraInfo()
        extraInfo.type = "auth_eHealth_org"
        extraInfo.pakket = "DXC_VACCINNET_TEST"
        return extraInfo
    }

    @Throws(IOException::class, JAXBException::class, DatatypeConfigurationException::class)
    private fun configureKmehr(organisation: Organisation, patient: Patient): KmehrmessageType {
        val kmehr: KmehrmessageType = XmlDataUtils.templateKmehr
        configureKmehrHeader(organisation, kmehr)
        configureKmehrFolder(organisation, patient, kmehr)
        return kmehr
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureKmehrHeader(organisation: Organisation, kmehr: KmehrmessageType) {
        kmehr.header?.apply {
            val now = LocalDateTime.now()
            val gregorianCalendar: XMLGregorianCalendar? = DateUtils.toXmlGregorianCalendar(now)
            this.date = gregorianCalendar
            this.time = gregorianCalendar
            val idkmehr: org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR =
                this.ids.first { id -> ID_KMEHR == id.s }
            idkmehr.value =
                organisation.riziv.toString() + "." + now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            val hcpartyType: org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType = this.sender.hcparties.first()
            KmehrUtils.getIdHcParty(hcpartyType.ids, IDHCPARTYschemes.LOCAL).value = organisation.userCode
            KmehrUtils.getIdHcParty(hcpartyType.ids, IDHCPARTYschemes.ID_HCPARTY).value = organisation.riziv
            KmehrUtils.getCdHcParty(hcpartyType.cds, CDHCPARTYschemes.CD_HCPARTY).value = organisation.organisationType.cdHcParty.value()
            hcpartyType.name = organisation.name

        }
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureKmehrFolder(organisation: Organisation, patient: Patient, kmehr: KmehrmessageType) {
        configureKmehrPatient(patient, kmehr)
        configureTransaction(organisation, kmehr)
    }

    private fun configureKmehrPatient(patient: Patient, kmehr: KmehrmessageType) {
        val personType: PersonType = kmehr.folders.first().patient
        personType.ids.first().value = patient.nationalRegistryNumber
        personType.firstnames.add(patient.firstName)
        personType.familyname = patient.lastName
        val dateType = DateType()
        try {
            dateType.date = DateUtils.toXmlGregorianCalendar(patient.birthDate)
        } catch (e: DatatypeConfigurationException) {
            e.printStackTrace()
        }
        personType.birthdate = dateType
        personType.sex.cd.value = CDSEXvalues.fromValue(patient.gender?.value)
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureTransaction(organisation: Organisation, kmehr: KmehrmessageType) {
        val transactionTypes: List<TransactionType> = kmehr.folders.first().transactions
        val transactionType = transactionTypes.first()
        val idkmehr: IDKMEHR = transactionType.ids
            .first { id -> LOCAL == id.s }
        idkmehr.value = UUID.randomUUID().toString()
        val date: XMLGregorianCalendar? =
            DateUtils.toXmlGregorianCalendar(LocalDateTime.now().minusDays(minusVaccinationDays.toLong()))
        transactionType.date = date
        transactionType.time = date
        val hcpartyType: HcpartyType = transactionType.author.hcparties.first()
        KmehrUtils.getIdHcParty(hcpartyType.ids, IDHCPARTYschemes.LOCAL).value = organisation.userCode
        KmehrUtils.getCdHcParty(hcpartyType.cds, CDHCPARTYschemes.CD_HCPARTY).value = organisation.organisationType.cdHcParty.value()
        hcpartyType.ids.remove(KmehrUtils.getIdHcParty(hcpartyType.ids, IDHCPARTYschemes.ID_HCPARTY))
        hcpartyType.name = organisation.name
        val itemType = KmehrUtils.getItem(transactionType)
        itemType.contents.first().medicinalproduct.intendedcds.first().value = "1734094"
        itemType.contents.first().medicinalproduct.intendedname = "BOOSTRIX SER PREREMPL 1 X 0,5 ML"
        itemType.beginmoment.date = date
        itemType.batch = "Batch1"
    }
}
