package org.taktik.freehealth.middleware.service.impl

import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.UpdateFeedbackFlagResult
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.kmehr.v20161201.be.ehealth.logic.recipe.xsd.v20160906.RecipeNotification
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeitemType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDADMINISTRATIONUNIT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGROUTE
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDEXTERNALSOURCE
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDEXTERNALSOURCEschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADING
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADINGschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDINNCLUSTER
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDINNCLUSTERschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLE
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLEvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOM
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITYvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDWEEKDAY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDWEEKDAYvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.AdministrationquantityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.AdministrationunitType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.CompoundprescriptionType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.FolderType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.HeaderType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.HeadingType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.LifecycleType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.MedicinalProductType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.MomentType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.PersonType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.QuantityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.RenewalType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.RouteType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.SenderType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.StandardType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.TelecomType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.TemporalityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.TransactionType
import org.taktik.connector.business.domain.kmehr.v20190301.makeDateTypeFromFuzzyLong
import org.taktik.connector.business.domain.kmehr.v20190301.makeXGC
import org.taktik.connector.business.domain.kmehr.v20190301.makeXMLGregorianCalendarFromFuzzyLong
import org.taktik.connector.business.domain.kmehr.v20190301.makeXmlGregorianCalendar
import org.taktik.connector.business.domain.kmehr.v20190301.s
import org.taktik.connector.business.recipe.prescriber.PrescriberIntegrationModuleV4Impl
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelperV4
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelperV4.mapPeriodToFrequency
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelperV4.toDaytime
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelperV4.toDurationType
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.CodeDao
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.domain.recipe.PrescriptionFullWithFeedback
import org.taktik.freehealth.middleware.drugs.dto.MppId
import org.taktik.freehealth.middleware.drugs.logic.DrugsLogic
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.RecipeV4Service
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionConfig
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.zip.DataFormatException
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import be.recipe.services.feedback.Feedback as FeedbackText

@Service
class RecipeV4ServiceImpl(private val codeDao: CodeDao, private val stsService: STSService, private val drugsLogic: DrugsLogic, keyDepotService: KeyDepotService) : RecipeV4Service {
    val log = LoggerFactory.getLogger(this.javaClass)!!

    private val ridCache = CacheBuilder.newBuilder().build<String, GetPrescriptionForPrescriberResult>()
    private val feedbacksCache : Cache<String, SortedSet<Feedback>> = CacheBuilder.newBuilder().build<String, SortedSet<Feedback>>()
    private val service = PrescriberIntegrationModuleV4Impl(stsService, keyDepotService)

    override fun listOpenPrescriptions(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        patientId: String
    ): List<Prescription> {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)

        val ridList = service.listOpenRids(samlToken, credential, patientId)

        val es = Executors.newFixedThreadPool(5)
        try {
            val getFeedback = es.submit<List<Feedback>> { listFeedbacks(
                keystoreId,
                tokenId,
                passPhrase
            ) }
            val futures = es.invokeAll<GetPrescriptionForPrescriberResult>(ridList.prescriptions.map { rid -> Callable<GetPrescriptionForPrescriberResult> { ridCache[rid, { service.getPrescription(samlToken, credential, hcpNihii, rid) }] } })
            val result = futures.map { f -> f.get() }.map { r -> Prescription(r.creationDate.time, r.encryptionKeyId, r.rid, r.feedbackAllowed, r.patientId) }

            try {
                for (d in getFeedback.get()) {
                    feedbacksCache[d.rid!!, { TreeSet() }].add(d)
                }
            } catch (e: ExecutionException) {
                log.error("Unexpected error", e)
            }

            es.shutdown()
            return result
        } catch (e: InterruptedException) {
            log.error("Unexpected error", e)
        }
        return emptyList()
    }

    override fun listFeedbacks(keystoreId: UUID, tokenId: UUID, passPhrase: String): List<Feedback> {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val feedbackItemList = service.listFeedback(samlToken, credential, false)
        return feedbackItemList.map { Feedback(it.rid, it.sentBy?.toLong(), it.sentDate?.time, it.content?.toString(Charset.forName("UTF-8"))?.let { try {
            MarshallerHelper<FeedbackText, FeedbackText>(FeedbackText::class.java, FeedbackText::class.java).toObject(it)?.text } catch(e:Exception) { null } ?: it }) }
    }

    @Throws(ConnectorException::class, DataFormatException::class)
    override fun getPrescriptionMessage(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String
    ): org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage? {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val p = service.getPrescription(samlToken, credential, hcpNihii, rid)

        return p.prescription?.let { JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createUnmarshaller().unmarshal(
            ByteArrayInputStream(it) as InputStream
        ) as org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
        }
    }

    override fun sendNotification(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        patientId: String,
        executorId: String,
        rid: String,
        text: String
    ) {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(RecipeNotification::class.java).createMarshaller().marshal(RecipeNotification().apply { this.text = text; kmehrmessage = getPrescriptionMessage(
            keystoreId,
            tokenId,
            passPhrase,
            hcpNihii,
            rid
        ) }, os)
        val bytes = os.toByteArray()

        service.sendNotification(samlToken, credential, bytes, patientId, executorId)
    }

    override fun revokePrescription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String,
        reason: String
    ) {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        service.revokePrescription(samlToken, credential, hcpNihii, rid, reason)
    }

    override fun getPrescriptionStatus(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        rid: String
    ): GetPrescriptionStatusResult {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
       return service.getPrescriptionStatus(samlToken, credential, hcpNihii, rid) ?: throw IllegalStateException("Unknown error")
    }

    override fun listRidsHistory(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        patientSsin: String,
        rid: String,
        reason: String
    ): ListRidsHistoryResult {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        return service.listRidsHistory(samlToken, credential, patientSsin, rid, reason)
    }


    override fun setVision(keystoreId: UUID, tokenId: UUID, passPhrase: String, rid: String, vision: String): PutVisionResult {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        return service.setVision(samlToken, credential, rid, vision)
    }

    override fun updateFeedbackFlag(keystoreId: UUID, tokenId: UUID, hcpNihii: String, passPhrase: String, rid: String, feedbackAllowed: Boolean): UpdateFeedbackFlagResult {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        return service.updateFeedbackFlag(samlToken, credential, hcpNihii, rid, feedbackAllowed)
    }

    override fun createPrescription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpQuality: String,
        hcpNihii: String,
        patient: Patient,
        hcp: HealthcareParty,
        feedback: Boolean,
        medications: List<Medication>,
        prescriptionType: String?,
        notification: String?,
        executorId: String?,
        samVersion: String?,
        deliveryDate: LocalDateTime?,
        vendorName: String?,
        packageName: String?,
        packageVersion: String?,
        vendorEmail: String?,
        vendorPhone: String?,
        vision: String?,
        expirationDate: LocalDateTime?
    ): Prescription {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val selectedType: String = inferPrescriptionType(medications, prescriptionType)

        val m = getKmehrPrescription(patient, hcp, medications, samVersion, deliveryDate, hcpQuality, vendorName ?: "phyMedispringTopaz", packageName, packageVersion ?: "1.0-freehealth-connector", vendorEmail, vendorPhone, expirationDate)

        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller().marshal(m, os)
        val prescription = os.toByteArray()

        try {
            //kmehrHelper.assertValidKmehrPrescription(ByteArrayInputStream(prescription), selectedType)
            log.debug("prescription $selectedType XML:\n${String(prescription)}")
        } catch (e: Exception) {
            log.error("Invalid $selectedType prescription XML:\n${String(prescription)}")
            throw e
        }

        val unconstrainedDate = expirationDate ?: deliveryDate?.plusMonths(3)?.minusDays(1) ?: LocalDateTime.now().plusMonths(3).minusDays(1)
        val limitDate =  LocalDateTime.now().plusYears(1).minusDays(1)

        val prescriptionId = service.createPrescription(
            samlToken,
            credential,
            patient.ssin!!,
            hcpNihii,
            feedback,
            selectedType,
            if (unconstrainedDate.isAfter(limitDate)) limitDate else unconstrainedDate,
            prescription,
            vision,
            vendorName ?: "phyMedispringTopaz",
            packageVersion ?: "1.0-freehealth-connector"
        )

        val result = Prescription(Date(), "", prescriptionId, false, null, false, ConnectorXmlUtils.toString(m))

        return result
    }

    fun getKmehrPrescription(
        patient: Patient,
        hcp: HealthcareParty,
        medications: List<Medication>,
        samVersion: String?,
        deliveryDate: LocalDateTime?,
        hcpQuality: String,
        vendorName: String?,
        packageName: String?,
        packageVersion: String?,
        vendorEmail: String?,
        vendorPhone: String?,
        expirationDate: LocalDateTime?
                            ): Kmehrmessage {
        val config = KmehrPrescriptionConfig().apply {
            prescription.apply {
                inami = hcp.nihii!!.replace("[^0-9]".toRegex(), "")
                language = "fr"
                substanceDb = "LOCALDB"
            }
            header.apply {
                _idKhmerId = System.currentTimeMillis().toString()
                makeXGC(Instant.now().toEpochMilli()).let {
                    date = it
                    time = it
                    recorddatetime = it
                }
                messageId = "${prescription.inami}-${hcp.ssin}-$_idKhmerId"
                this.samVersion = samVersion
            }
            softwarePackage.apply {
                name = packageName
                version = packageVersion
                id = name + "-" + version
                this.vendorName = vendorName
                phone = vendorPhone
                mail = vendorEmail
            }
        }
        return getKmehrPrescription(patient, hcp, medications, deliveryDate, config, hcpQuality, expirationDate)
    }

    override fun inferPrescriptionType(medications: List<Medication>, prescriptionType: String?): String {
        if (prescriptionType != null) {
            return prescriptionType
        }

        medications.filter { isAnyReimbursedMedicinalProduct(it.medicinalProduct?.intendedcds) }
            .forEach { return "P1" }

        medications.filter { isAnyReimbursedSubstanceProduct(it.substanceProduct?.intendedcds) }
            .forEach { return "P1" }

        return if (medications.any { it.options?.get(Medication.REIMBURSED)?.booleanValue == true }) {
            "P1"
        } else {
            "P0"
        }
    }

    private fun isAnyReimbursedMedicinalProduct(intendedcds: List<Code>?): Boolean {
        intendedcds?.filter { c -> c.type == "CD-DRUG-CNK" }
            ?.forEach { c ->
                val infos = drugsLogic.getInfos(MppId(c.code, "fr"))
                if (infos != null && !StringUtils.isEmpty(infos.ssec) && !infos.ssec.equals("chr", ignoreCase = true)) {
                    return true
                }
            }
        return false
    }

    private fun isAnyReimbursedSubstanceProduct(intendedcds: List<Code>?): Boolean {
        intendedcds?.filter { c -> c.type == "CD-INNCLUSTER" }
            ?.forEach { c ->
                drugsLogic.getMedecinePackagesFromInn(c.code, "fr")
                    .map { c -> drugsLogic.getInfos(c.id) }
                    .filter { info -> !StringUtils.isEmpty(info.ssec) }
                    .filter { info -> !info.ssec.equals("chr", ignoreCase = true) }
                    .forEach { return true }
            }
        intendedcds?.filter { c -> c.type == "CD-VMPGROUP" }?.forEach {
            return true
        }
        return false
    }

    override fun getKmehrPrescription(patient: Patient, hcp: HealthcareParty, medications: List<Medication>, deliveryDate: LocalDateTime?, config: KmehrPrescriptionConfig, hcpQuality: String,
        expirationDate: LocalDateTime?): Kmehrmessage {

        val language = config.prescription.language
        return Kmehrmessage().apply {
            header = HeaderType().apply {
                standard = StandardType().apply {
                    cd = CDSTANDARD().apply {
                        value = "20190301"
                    }
                }
                date = config.header.date
                time = config.header.time
                externalsource = HeaderType.Externalsource().apply { sources.add(HeaderType.Externalsource.Source().apply { cds.add(CDEXTERNALSOURCE().apply { s = CDEXTERNALSOURCEschemes.CD_EXTERNALSOURCE; value = "samv2"}); version = config.header.samVersion  }) }
                ids.addAll(listOf(
                    IDKMEHR().apply {
                        s = IDKMEHRschemes.ID_KMEHR
                        sv = "1.0"
                        value = config.header.getIdKmehr()
                    },
                    IDKMEHR().apply {
                        s = IDKMEHRschemes.LOCAL
                        sl = config.softwarePackage.name
                        sv = config.softwarePackage.version
                        value = config.header.messageId
                    }
                                 ))
                sender = SenderType().apply {
                    hcparties.addAll(listOf(
                        HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; value = config.prescription.inami })
                            cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY); value = hcpQuality })
                            firstname = hcp.firstName
                            familyname = hcp.lastName
                        },
                        HcpartyType().apply {
                            cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY); value = "application" })
                            name = config.softwarePackage.vendorName
                            telecoms.addAll(listOf(
                                TelecomType().apply {
                                    cds.addAll(listOf(
                                        CDTELECOM().apply { s(CDTELECOMschemes.CD_ADDRESS); value = "work" },
                                        CDTELECOM().apply { s(CDTELECOMschemes.CD_TELECOM); value = "phone" }
                                                     ))
                                    telecomnumber = config.softwarePackage.phone
                                },
                                TelecomType().apply {
                                    cds.addAll(listOf(
                                        CDTELECOM().apply { s(CDTELECOMschemes.CD_ADDRESS); value = "work" },
                                        CDTELECOM().apply { s(CDTELECOMschemes.CD_TELECOM); value = "email" }
                                                     ))
                                    telecomnumber = config.softwarePackage.mail
                                }
                                                  ))
                        }
                                           ))
                }
                recipients.add(RecipientType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "RECIPE" })
                        cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY); value = "orgpublichealth" })
                        name = "Recip-e"
                    })
                })
            }
            folders.add(FolderType().apply {
                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
                this.patient = PersonType().apply {
                    ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ID_PATIENT; value = patient.ssin })
                    patient.firstName?.let { firstnames.add(it) }
                    familyname = patient.lastName
                    patient.dateOfBirth?.let { birthdate = makeDateTypeFromFuzzyLong(it.toLong())!! }
                    patient.gender?.name?.let { gender ->
                        sex = SexType().apply {
                            cd = CDSEX().apply { value = CDSEXvalues.fromValue(gender) }
                        }
                    }
                }
                transactions.add(TransactionType().apply {
                    ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
                    cds.add(CDTRANSACTION().apply { s(CDTRANSACTIONschemes.CD_TRANSACTION); value = "pharmaceuticalprescription" })
                    date = config.header.date
                    time = config.header.time
                    expirationDate?.let { expirationdate = makeXmlGregorianCalendar(expirationDate) }
                    author = AuthorType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s =
                                IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = config.prescription.inami })
                            cds.add(CDHCPARTY().apply { s(CDHCPARTYschemes.CD_HCPARTY); value = hcpQuality })
                            firstname = hcp.firstName
                            familyname = hcp.lastName
                            val address = getPreferredAddress(hcp)
                            addresses.add(AddressType().apply {
                                cds.add(CDADDRESS().apply { s(CDADDRESSschemes.CD_ADDRESS); value = "work" })
                                country = CountryType().apply {
                                    cd = CDCOUNTRY().apply {
                                        s = CDCOUNTRYschemes.CD_FED_COUNTRY
                                        value = address.country?.let { codeDao.getCodeByLabel(it, "CD-FED-COUNTRY")?.code ?: it.toLowerCase() } ?: "be"
                                    }
                                }
                                zip = address.postalCode
                                city = address.city
                                street = address.street
                                housenumber = address.houseNumber
                                postboxnumber = address.postboxNumber
                            })
                            telecoms.add(TelecomType().apply {
                                cds.add(CDTELECOM().apply { s(CDTELECOMschemes.CD_ADDRESS); value = "work" })
                                cds.add(CDTELECOM().apply { s(CDTELECOMschemes.CD_TELECOM); value = "phone" })
                                telecomnumber = address.telecoms.find { (it.telecomType == org.taktik.freehealth.middleware.dto.TelecomType.mobile || it.telecomType == org.taktik.freehealth.middleware.dto.TelecomType.phone) && !it.telecomNumber.isNullOrBlank() }?.telecomNumber ?: "+3220000000"
                            })
                        })
                    }
                    isIscomplete = true
                    isIsvalidated = true
                    headingsAndItemsAndTexts.add(HeadingType().apply {
                        ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = "1" })
                        cds.add(CDHEADING().apply { s = CDHEADINGschemes.CD_HEADING; value = "prescription" })
                        medications.forEachIndexed { idx, med ->
                            headingsAndItemsAndTexts.add(ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; value = (idx + 1).toString() })
                                cds.add(CDITEM().apply { s(CDITEMschemes.CD_ITEM); value = "medication" })
                                med.medicinalProduct?.intendedcds?.let {
                                    it.find { it.type == "CD-DRUG-CNK" }?.let { c ->
                                        if(c.code != "0000000"  || (med.compoundPrescription == null && med.compoundPrescriptionV2 == null)){
                                            contents.add(ContentType().apply {
                                                medicinalproduct = MedicinalProductType().apply {
                                                    intendedcds.add(KmehrPrescriptionHelperV4.toCDDRUGCNK(c))
                                                    intendedname = med.medicinalProduct?.intendedname
                                                }
                                            })
                                            med.medicinalProduct?.samId?.let {
                                                contents.add(ContentType().apply {
                                                    cds.add(CDCONTENT().apply {
                                                        s = CDCONTENTschemes.LOCAL; sv =
                                                        "1.0"; sl = "SAMPROOF"; value = it
                                                    })
                                                })
                                            }
                                        }
                                    }
                                }
                                med.substanceProduct?.intendedcds?.let {
                                    it.find { it.type == "CD-INNCLUSTER" || it.type == "CD-VMPGROUP" }?.let { c ->
                                        val scheme = CDINNCLUSTERschemes.fromValue(c.type)
                                        contents.add(ContentType().apply {
                                            substanceproduct = ContentType.Substanceproduct().apply {
                                                intendedcd = CDINNCLUSTER().apply {
                                                    s(scheme)
                                                    sv = config.prescription.substanceDb
                                                    value = c.code
                                                }
                                                intendedname = med.substanceProduct?.intendedname
                                            }
                                        })
                                        med.substanceProduct?.samId?.let {
                                            contents.add(ContentType().apply {
                                                cds.add(CDCONTENT().apply {
                                                    s = CDCONTENTschemes.LOCAL; sv =
                                                    "1.0"; sl = "SAMPROOF"; value = it
                                                })
                                            })
                                        }

                                    }
                                }
                                if (contents.none { it.medicinalproduct != null || it.substanceproduct != null || it.compoundprescription != null}) {
                                    val compoundPrescription = med.compoundPrescriptionV2
                                    if (compoundPrescription != null) {
                                        contents.add(ContentType().apply {
                                            compoundprescription = CompoundprescriptionType().apply {
                                                content.addAll(KmehrPrescriptionHelperV4.toCompoundPrescriptionElements(compoundPrescription, language))
                                            }
                                        })
                                    } else {
                                        (med.compoundPrescription ?: med.medicinalProduct?.intendedname ?: med.substanceProduct?.intendedname)?.let { text ->
                                            contents.add(ContentType().apply {
                                                compoundprescription = CompoundprescriptionType().apply {
                                                    content.add(KmehrPrescriptionHelper.kmehrJaxbElement(
                                                        "magistraltext",
                                                        TextType().apply {
                                                            value = text
                                                            l = language
                                                        }
                                                                                                        ))
                                                }
                                            })
                                        }
                                    }
                                }
                                beginmoment = MomentType().apply { date =
                                    makeXMLGregorianCalendarFromFuzzyLong(med.beginMoment ?: FuzzyValues.currentFuzzyDate)
                                }

                                med.endMoment?.let { endmoment = MomentType().apply { date =
                                    makeXMLGregorianCalendarFromFuzzyLong(it)
                                } }
                                val posologyText = med.getPosology(config.prescription.language)
                                if (!StringUtils.isEmpty(posologyText)) {
                                    posology = ItemType.Posology().apply { text = TextType().apply { l = language; value = posologyText } }
                                }
                                lifecycle = LifecycleType().apply { cd = CDLIFECYCLE().apply { value = CDLIFECYCLEvalues.PRESCRIBED } }
                                med.temporality?.let {
                                    temporality = TemporalityType().apply {
                                        cd = CDTEMPORALITY().apply { value = CDTEMPORALITYvalues.fromValue(it.code) }
                                    }
                                }
                                if (med.substanceProduct?.intendedcds == null || med.substanceProduct!!.intendedcds.map { it.code }.all { it == "000000" }) {
                                    quantity = QuantityType().apply {
                                        decimal = med.numberOfPackages?.let { nr -> BigDecimal.valueOf(nr.toLong()) } ?: BigDecimal.ONE
                                        // currently no support for units
                                    }
                                }
                                KmehrPrescriptionHelper.inferPeriodFromRegimen(med.regimen)?.let {
                                    frequency = mapPeriodToFrequency(it)
                                }
                                duration = toDurationType(med.duration)
                                med.regimen?.let { intakes ->
                                    if (intakes.isEmpty()) {
                                        return@let; }
                                    regimen = ItemType.Regimen().apply {
                                        for (intake in intakes) {
                                            // choice day specification
                                            intake.dayNumber?.let { dayNumber -> daynumbersAndQuantitiesAndDates.add(BigInteger.valueOf(dayNumber.toLong())) }
                                            intake.date?.let { d -> daynumbersAndQuantitiesAndDates.add(makeXMLGregorianCalendarFromFuzzyLong(d)) }
                                            intake.weekday?.let { day ->
                                                daynumbersAndQuantitiesAndDates.add(ItemType.Regimen.Weekday().apply {
                                                    day.weekDay?.let { dayOfWeek ->
                                                        cd = CDWEEKDAY().apply { value = CDWEEKDAYvalues.fromValue(dayOfWeek.code) }
                                                    }
                                                    day.weekNumber?.let { n -> weeknumber = BigInteger.valueOf(n.toLong()) }
                                                })
                                            }
                                            // choice time of day
                                            daynumbersAndQuantitiesAndDates.add(toDaytime(intake))

                                            // mandatory quantity
                                            intake.administratedQuantity?.let { drugQuantity ->
                                                daynumbersAndQuantitiesAndDates.add(AdministrationquantityType().apply {
                                                    decimal = drugQuantity.quantity?.let { BigDecimal(it) }
                                                    drugQuantity.administrationUnit?.let { drugUnit ->
                                                        unit = AdministrationunitType().apply {
                                                            cd = CDADMINISTRATIONUNIT().apply {
                                                                value = codeDao.ensureValid(drugUnit, ofType = "CD-ADMINISTRATIONUNIT", orDefault = Code("CD-ADMINISTRATIONUNIT", defaultDosis))?.code
                                                                if (value != drugUnit.code) {
                                                                    log.warn("illegal CD-ADMINISTRATIONUNIT ${drugUnit.code} changed to ${defaultDosis}")
                                                                }
                                                            }
                                                        }
                                                    }
                                                })
                                            }
                                        }
                                    }
                                }
                                med.renewal?.let {
                                    renewal = RenewalType().apply {
                                        it.decimal?.let { decimal = BigDecimal(it.toLong()) }
                                        duration = toDurationType(it.duration)
                                    }
                                }
                                med.intakeRoute?.code?.let { c ->
                                    route = RouteType().apply { cd = CDDRUGROUTE().apply { value = c } }
                                }
                                deliverydate = deliveryDate?.let { makeXMLGregorianCalendarFromFuzzyLong(FuzzyValues.getFuzzyDate(it, ChronoUnit.DAYS)) }
                                instructionforpatient = KmehrPrescriptionHelperV4.toTextType(language, med.recipeInstructionForPatient)
                                med.instructionsForReimbursement?.translations?.get(language)?.let {
                                    instructionforreimbursement = KmehrPrescriptionHelperV4.toTextType(language, it)
                                }
                                isIssubstitutionallowed = med.substitutionAllowed
                            })
                        }
                    })
                })
            })
        }
    }

    private fun getPreferredAddress(hcp: HealthcareParty): Address {
        return hcp.addresses.find { it.addressType == org.taktik.freehealth.middleware.dto.AddressType.work || it.addressType == org.taktik.freehealth.middleware.dto.AddressType.clinic } ?: hcp.addresses.iterator().next() ?: throw IllegalArgumentException("${hcp.lastName} (${hcp.nihii}) has no address")
    }

    override fun getGalToAdministrationUnit(galId: String): Code? {
        return getAdministrationCode(when (galId) {
            "00001" -> "00005" // compr
            "00003" -> "00005"
            "00004" -> "00005"
            "00005" -> "00005"
            "00006" -> "00005"
            "00007" -> "00026" // suppo
            "00010" -> "00005" // compr
            "00011" -> "00008" // flac
            "00012" -> "00002" // amp
            "00013" -> "00008" // flac
            "00015" -> defaultDosis
            "00017" -> "00004" // caps
            "00019" -> "00007" // drips
            "00020" -> "00005" // compr
            "00023" -> "00005"
            "00026" -> "00002" // amp
            "00029" -> "00022" // pen
            "00030" -> "00008" // flac
            "00032" -> "00005" // compr
            "00033" -> defaultDosis
            "00035" -> "00008" // flac
            "00036" -> defaultDosis
            "00037" -> "00005" // compr
            "00040" -> defaultDosis
            "00046" -> "00004" // caps
            "00047" -> "00008" // flac
            "00048" -> "00003" // cream -> applic
            "00049" -> defaultDosis
            "00053" -> "00005" // compr
            "00058" -> "00028" // meche
            "00059" -> "00002" // amp
            "00062" -> defaultDosis
            "00063" -> "00007" // drips
            "00065" -> "00002" // amp
            "00066" -> defaultDosis
            "00067" -> defaultDosis
            "00068" -> "00008" // flac
            "00070" -> "00029" // sac
            "00071" -> "00008" // flac
            "00073" -> "00001" // caps
            "00074" -> "00007" // drips
            "00075" -> "00008" // flac
            "00076" -> "00002" // amp
            "00077" -> "00008" // flac
            "00080" -> "00030" // zakjes
            "00081" -> "00002" // amp
            "00084" -> "00005" // compr
            "00088" -> "00002" // amp
            "00091" -> "00008" // flac
            "00092" -> "00008"
            "00093" -> "00005" // compr
            "00094" -> "00005"
            "00099" -> defaultDosis
            "00102" -> "00005" // compr
            "00104" -> "00003" // pommade -> applic
            "00112" -> defaultDosis
            "00113" -> "00002" // amp
            "00118" -> "00011" // inhal
            "00136" -> defaultDosis
            "00139" -> defaultDosis
            "00140" -> defaultDosis
            "00148" -> "00004" // caps
            "00152" -> "00005" // compr
            "00153" -> "00004" // caps
            "00155" -> "00008" // flac
            "00156" -> defaultDosis
            "00157" -> "00005" // compr
            "00161" -> defaultDosis
            "00167" -> "00005" // compr
            "00174" -> "00002" // amp
            "00175" -> "00005" // compr
            "00177" -> defaultDosis
            "00178" -> "00008" // flac
            "00180" -> "00022" // stylo
            "00181" -> "00007" // drips
            "00184" -> defaultDosis
            "00191" -> "00008" // flac
            "00198" -> defaultDosis
            "00199" -> "00005" // compr
            "00204" -> "00008" // flac
            "00213" -> defaultDosis
            "00218" -> "00008" // flac
            "00221" -> "00030" // zakjes
            "00226" -> "00008" // flac
            "00229" -> defaultDosis
            "00242" -> "00002" // amp
            "00243" -> "00008" // flac
            "00246" -> "00005" // compr
            "00256" -> "00005"
            "00269" -> "00005"
            "00270" -> "00008" // flac
            "00308" -> "00005"
            "00322" -> "00007" // drips
            "00383" -> "00008" // flac
            "00420" -> defaultDosis
            "00422" -> "00011" // inhal
            "00440" -> "00007" // drips
            "00447" -> "00004" // caps
            "00508" -> defaultDosis
            "00537" -> "00030"
            else -> defaultDosis
        })
    }

    @Throws(JAXBException::class)
    override fun getPrescription(rid: String): PrescriptionFullWithFeedback? {
        val r = ridCache.getIfPresent(rid) ?: return null
        val fd = feedbacksCache.getIfPresent(rid)
        val result =
            PrescriptionFullWithFeedback(r.creationDate.time, r.encryptionKeyId, r.rid, r.feedbackAllowed, r.patientId)
        fd?.let { result.feedbacks = ArrayList(fd) }

        val jaxbContext = JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java)
        val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
        val pm = jaxbUnmarshaller.unmarshal(ByteArrayInputStream(r.prescription)) as org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage

        pm.folder?.transaction?.let { t ->
            t.heading?.let { hs ->
                t.author?.hcparties?.firstOrNull()?.let { hcp ->
                    result.nihii = hcp.ids?.find { it.s == org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes.ID_HCPARTY }?.value
                    result.fullAuthorName = hcp.name ?: ((hcp.firstname?.plus(" ") ?: "") + (hcp.familyname ?: ""))
                }
                hs.items.forEach { item ->
                    when (item) {
                        is RecipeitemType -> {
                            item.deliverydate?.toGregorianCalendar()?.time?.let { dd -> result.deliverableFrom = if (result.deliverableFrom?.after(dd) ?: false) result.deliverableFrom else dd }
                            item.content.let { c ->
                                (
                                    c.medicinalproduct?.intendedname ?:
                                    c.substanceproduct?.intendedname ?:
                                    c?.compoundprescription?.content?.find { it is org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType }.let { (it as org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType).value }
                                    ).let {
                                        result.medicines.add(it + (item.posology?.let {
                                            "\nS/ " + it.text
                                        } ?: ""))
                                    }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    private fun getAdministrationCode(code: String): Code? {
        return codeDao.findCode("CD-ADMINISTRATIONUNIT", code, "1")
    }

    companion object {
        const val defaultDosis = "00006"
    }

}
