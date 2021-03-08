package be.vlaanderen.zg.vaccinnet.batchuploadtest.service

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import be.fgov.ehealth.standards.kmehr.schema.v1.KmehrmessageType
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import be.vlaanderen.zg.vaccinnet.batchuploadtest.model.Patient
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.DateUtils
import be.vlaanderen.zg.vaccinnet.batchuploadtest.utils.SoapUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.xml.bind.JAXBException

@Service
@Slf4j
class AanleverenVaccinatieGegevensService(
    webServiceTemplate: WebServiceTemplate,
    applicationArguments: ApplicationArguments,
    @Value("\${vaccinnet.batchupload.vaccination_minus_days.aanleveren}") minusVaccinationDays: Int
) {
    private val webServiceTemplate: WebServiceTemplate
    private val applicationArguments: ApplicationArguments

    @Value("\${vaccinnet.batchupload.vaccination_minus_days.aanleveren}")
    private val minusVaccinationDays: Int
    fun run() {
        val requestPayload: GetAanleverenVaccinatieGegevensRequest = getAanleverenVaccinatieGegevensRequest
        SoapUtils.validateMessage(requestPayload)
        webServiceTemplate.marshalSendAndReceive(requestPayload)
    }

    private val getAanleverenVaccinatieGegevensRequest: GetAanleverenVaccinatieGegevensRequest
        private get() {
            val request = GetAanleverenVaccinatieGegevensRequest()
            request.setExtraInfo(configureExtraInfo())
            try {
                request.setKmehrmessage(configureKmehr())
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
        extraInfo.setType("auth_eHealth_org")
        extraInfo.setPakket("DXC_VACCINNET_TEST")
        return extraInfo
    }

    @Throws(IOException::class, JAXBException::class, DatatypeConfigurationException::class)
    private fun configureKmehr(): KmehrmessageType {
        val kmehr: KmehrmessageType = XmlDataUtils.getTemplateKmehr()
        configureKmehrHeader(kmehr)
        configureKmehrFolder(kmehr)
        return kmehr
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureKmehrHeader(kmehr: KmehrmessageType) {
        val organisation: Organisation = XmlDataUtils.getOrganisation(applicationArguments)
        val header: HeaderType = kmehr.getHeader()
        val now = LocalDateTime.now()
        val gregorianCalendar: XMLGregorianCalendar? = DateUtils.toXmlGregorianCalendar(now)
        header.date = gregorianCalendar
        header.time = gregorianCalendar
        val idkmehr: IDKMEHR = header.getId().stream()
            .filter({ id -> IDKMEHRschemes.ID_KMEHR == id.getS() })
            .findFirst()
            .orElseThrow()
        idkmehr.value =
            organisation.getRiziv().toString() + "." + now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        val hcpartyType: HcpartyType = header.sender.getHcparty().get(0)
        KmehrUtils.getIdHcParty(hcpartyType.getId(), IDHCPARTYschemes.LOCAL).setValue(organisation.getUserCode())
        KmehrUtils.getIdHcParty(hcpartyType.getId(), IDHCPARTYschemes.ID_HCPARTY).setValue(organisation.getRiziv())
        KmehrUtils.getCdHcParty(hcpartyType.getCd(), CDHCPARTYschemes.CD_HCPARTY)
            .setValue(organisation.getOrganisationType().getCdHcParty().value())
        hcpartyType.name = organisation.getName()
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureKmehrFolder(kmehr: KmehrmessageType) {
        configureKmehrPatient(kmehr)
        configureTransaction(kmehr)
    }

    private fun configureKmehrPatient(kmehr: KmehrmessageType) {
        val patient: Patient = XmlDataUtils.getPatient(
            applicationArguments
        )
        val personType: PersonType = kmehr.getFolder().get(0).getPatient()
        personType.getId().get(0).setValue(patient.getNationalRegistryNumber())
        personType.getFirstname().add(patient.getFirstName())
        personType.familyname = patient.getLastName()
        val dateType = DateType()
        try {
            dateType.setDate(DateUtils.toXmlGregorianCalendar(patient.birthDate))
        } catch (e: DatatypeConfigurationException) {
            e.printStackTrace()
        }
        personType.birthdate = dateType
        personType.sex.cd.value = CDSEXvalues.fromValue(patient.gender.getValue())
    }

    @Throws(DatatypeConfigurationException::class)
    private fun configureTransaction(kmehr: KmehrmessageType) {
        val transactionTypes: List<TransactionType> = kmehr.getFolder().get(0).getTransaction()
        val transactionType = transactionTypes[0]
        val idkmehr: IDKMEHR = transactionType.getId().stream()
            .filter({ id -> IDKMEHRschemes.LOCAL == id.getS() })
            .findFirst()
            .orElseThrow()
        idkmehr.value = UUID.randomUUID().toString()
        val date: XMLGregorianCalendar? =
            DateUtils.toXmlGregorianCalendar(LocalDateTime.now().minusDays(minusVaccinationDays.toLong()))
        transactionType.date = date
        transactionType.time = date
        val organisation: Organisation = XmlDataUtils.getOrganisation(applicationArguments)
        val hcpartyType: HcpartyType = transactionType.author.getHcparty().get(0)
        KmehrUtils.getIdHcParty(hcpartyType.getId(), IDHCPARTYschemes.LOCAL).setValue(organisation.getUserCode())
        KmehrUtils.getIdHcParty(hcpartyType.getId(), IDHCPARTYschemes.ID_HCPARTY).setValue(organisation.getRiziv())
        KmehrUtils.getCdHcParty(hcpartyType.getCd(), CDHCPARTYschemes.CD_HCPARTY)
            .setValue(organisation.getOrganisationType().getCdHcParty().value())
        hcpartyType.name = organisation.getName()
        val itemType: ItemType = KmehrUtils.getItem(transactionType)
        itemType.getContent().get(0).getMedicinalproduct().getIntendedcd().setValue("1734094")
        itemType.getContent().get(0).getMedicinalproduct().setIntendedname("BOOSTRIX SER PREREMPL 1 X 0,5 ML")
        itemType.beginmoment.date = date
        itemType.batch = "Batch1"
    }

    init {
        this.webServiceTemplate = webServiceTemplate
        this.applicationArguments = applicationArguments
        this.minusVaccinationDays = minusVaccinationDays
    }
}
