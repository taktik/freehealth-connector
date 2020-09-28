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

@file:Suppress("DEPRECATION")

package org.taktik.freehealth.middleware.service.impl

import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.kmehr.v20161201.Utils.Companion.makeDateTypeFromFuzzyLong
import org.taktik.connector.business.domain.kmehr.v20161201.Utils.Companion.makeXGC
import org.taktik.connector.business.domain.kmehr.v20161201.Utils.Companion.makeXMLGregorianCalendarFromFuzzyLong
import org.taktik.connector.business.domain.kmehr.v20161201.be.ehealth.logic.recipe.xsd.v20160906.RecipeNotification
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADMINISTRATIONUNIT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGROUTE
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes.CD_HCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADINGschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDINNCLUSTERschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLE
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDLIFECYCLEvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEX
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSEXvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSTANDARD
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes.CD_ADDRESS
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes.CD_TELECOM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTEMPORALITYvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDWEEKDAY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDWEEKDAYvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes.ID_HCPARTY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes.ID_KMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes.LOCAL
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes.ID_PATIENT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.AdministrationunitType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDHEADING
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDINNCLUSTER
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDITEM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeCDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeadministrationquantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeauthorType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipebasicIDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipecompoundprescriptionType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipecontentType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipefolderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeheaderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeitemType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipelifecycleType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipemedicinalProductType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipemomentType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipepatientpersonType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipequantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.ReciperenewalType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.ReciperouteType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipetemporalityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipetransactionType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipetransactionheadingType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipientType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.SenderType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.SexType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.StandardType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TelecomType
import org.taktik.connector.business.recipe.prescriber.PrescriberIntegrationModule
import org.taktik.connector.business.recipe.prescriber.PrescriberIntegrationModuleImpl
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.mapPeriodToFrequency
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.toDaytime
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.toDurationType
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.toTextType
import org.taktik.connector.business.recipe.utils.KmehrPrescriptionHelper.versions
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
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
import org.taktik.freehealth.middleware.dto.TelecomType.mobile
import org.taktik.freehealth.middleware.dto.TelecomType.phone
import org.taktik.freehealth.middleware.service.RecipeService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.utils.FuzzyValues
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionConfig
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Long
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.KeyStoreException
import java.security.cert.CertificateExpiredException
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.ArrayList
import java.util.Date
import java.util.Properties
import java.util.SortedSet
import java.util.TreeSet
import java.util.UUID
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.zip.DataFormatException
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes.CD_ADDRESS as ADDRESS_CD_ADDRESS

/**
 * Created with IntelliJ IDEA.
 * User: aduchateTechn
 * Date: 16/06/13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
@Service
class RecipeServiceImpl(private val codeDao: CodeDao, private val drugsLogic: DrugsLogic, private val stsService: STSService, private val keyDepotService: KeyDepotService) : RecipeService {
    private val ridCache = CacheBuilder.newBuilder().build<String, GetPrescriptionForPrescriberResult>()
    private val icureName = "freehealth-connector"
    private val icureVersion = "1.0.0"

    val log = LoggerFactory.getLogger(this.javaClass)!!
    private val kmehrHelper = KmehrHelper(Properties().apply { load(RecipeServiceImpl::class.java.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    private val feedbacksCache : Cache<String, SortedSet<Feedback>>
    private val service : PrescriberIntegrationModule

    init {
        feedbacksCache = CacheBuilder.newBuilder().build<String, SortedSet<Feedback>>()
        service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
    }

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun revokePrescription(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, rid: String, reason: String) {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
        service.revokePrescription(samlToken, credential, hcpNihii, rid, reason)
    }

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun updateFeedbackFlag(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, rid: String, feedbackFlag: Boolean) {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
        service.updateFeedbackFlag(samlToken, credential, hcpNihii, rid, feedbackFlag)

        ridCache.getIfPresent(rid)?.let {
            it.feedbackAllowed = feedbackFlag
        }
    }

    @Throws(ConnectorException::class, DataFormatException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun sendNotification(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, patientId: String, executorId: String, rid: String, text: String) {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(RecipeNotification::class.java).createMarshaller().marshal(RecipeNotification().apply { this.text = text; kmehrmessage = getPrescriptionMessage(keystoreId, tokenId, hcpQuality, hcpNihii, hcpSsin, hcpName, passPhrase, rid) }, os)
        val bytes = os.toByteArray()

        service.sendNotification(samlToken, credential, hcpNihii, bytes, patientId, executorId)
    }

    @Throws(ConnectorException::class, DataFormatException::class)
    override fun getPrescriptionMessage(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, rid: String): Kmehrmessage? {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
        val p = service.getPrescription(samlToken, credential, keystore, passPhrase, hcpNihii, rid)

        return p?.prescription?.let { JAXBContext.newInstance(Kmehrmessage::class.java).createUnmarshaller().unmarshal(ByteArrayInputStream(it) as InputStream) as Kmehrmessage }
    }

    @Throws(ConnectorException::class, DataFormatException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun listFeedbacks(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String): List<Feedback> {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val feedbackItemList = service!!.listFeedback(samlToken, credential, hcpNihii, true)
        return feedbackItemList.map { Feedback(it.rid, Long.parseLong(it.sentBy), it.sentDate?.time, it.content?.toString(Charset.forName("UTF-8"))) }
    }

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun listOpenPrescriptions(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String): List<Prescription> {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val ridList = service.listOpenPrescription(samlToken, credential, hcpNihii)

        val es = Executors.newFixedThreadPool(5)
        try {
            val getFeedback = es.submit<List<Feedback>> { listFeedbacks(keystoreId, tokenId, hcpQuality, hcpNihii, hcpSsin, hcpName, passPhrase) }
            val futures = es.invokeAll<GetPrescriptionForPrescriberResult>(ridList.map { rid -> Callable<GetPrescriptionForPrescriberResult> { ridCache[rid, { service!!.getPrescription(samlToken, credential, keystore, passPhrase, hcpNihii, rid) }] } })
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

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    override fun listOpenPrescriptions(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, patientId: String): List<Prescription> {
        //val prescritpionsList = listOpenPrescriptions(keystoreId, tokenId, hcpQuality, hcpNihii, hcpSsin, hcpName, passPhrase)
        //return prescritpionsList.filter { it.patientId == patientId }
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Ehealth Box operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val ridList = service.listOpenPrescription(samlToken, credential, hcpNihii, patientId)

        val es = Executors.newFixedThreadPool(5)
        try {
            val getFeedback = es.submit<List<Feedback>> { listFeedbacks(keystoreId, tokenId, hcpQuality, hcpNihii, hcpSsin, hcpName, passPhrase) }
            val futures = es.invokeAll<GetPrescriptionForPrescriberResult>(ridList.map { rid -> Callable<GetPrescriptionForPrescriberResult> { ridCache[rid, { service!!.getPrescription(samlToken, credential, keystore, passPhrase, hcpNihii, rid) }] } })
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

    @Throws(JAXBException::class)
    override fun getPrescription(rid: String): PrescriptionFullWithFeedback? {
        val r = ridCache.getIfPresent(rid) ?: return null
        val fd = feedbacksCache!!.getIfPresent(rid)
        val result =
            PrescriptionFullWithFeedback(r.creationDate.time, r.encryptionKeyId, r.rid, r.feedbackAllowed, r.patientId)
        fd?.let { result.feedbacks = ArrayList(fd) }

        val jaxbContext = JAXBContext.newInstance(Kmehrmessage::class.java)
        val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
        val pm = jaxbUnmarshaller.unmarshal(ByteArrayInputStream(r.prescription)) as Kmehrmessage

        pm.folder?.transaction?.let { t ->
            t.heading?.let { hs ->
                t.author?.hcparties?.firstOrNull()?.let { hcp ->
                    result.nihii = hcp.ids?.find { it.s == ID_HCPARTY }?.value
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
                                                c?.compoundprescription?.content?.find { it is TextType }.let { (it as TextType).value }
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

    override fun getKmehrPrescription(patient: Patient, hcp: HealthcareParty, medications: List<Medication>, deliveryDate: LocalDateTime?, hcpQuality: String): Kmehrmessage {
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
            }
            softwarePackage.apply {
                name = icureName
                version = icureVersion
                id = name + "-" + version
                vendorName = icureName
                phone = "+3223335840"
                mail = "support@icure.eu"
            }
        }
        return getKmehrPrescription(patient, hcp, medications, deliveryDate, config, hcpQuality)
    }

    override fun getKmehrPrescription(patient: Patient, hcp: HealthcareParty, medications: List<Medication>, deliveryDate: LocalDateTime?, config: KmehrPrescriptionConfig, hcpQuality: String): Kmehrmessage {
        val language = config.prescription.language
        return Kmehrmessage().apply {
            header = RecipeheaderType().apply {
                standard = StandardType().apply {
                    cd = CDSTANDARD().apply {
                        s = "CD-STANDARD"
                        value = "20161201"
                        sv = "1.20"
                    }
                }
                date = config.header.date
                time = config.header.time
                ids.addAll(listOf(
                        IDKMEHR().apply {
                            s = ID_KMEHR
                            sv = "1.0"
                            value = config.header.getIdKmehr()
                        },
                        IDKMEHR().apply {
                            s = LOCAL
                            sl = config.softwarePackage.name
                            sv = config.softwarePackage.version
                            value = config.header.messageId
                        }
                ))
                sender = SenderType().apply {
                    hcparties.addAll(listOf(
                            HcpartyType().apply {
                                ids.add(IDHCPARTY().apply { s = ID_HCPARTY; sv = "1.0"; value = config.prescription.inami })
                                cds.add(CDHCPARTY().apply { s = CD_HCPARTY; sv = versions["CD-HCPARTY"]; value = hcpQuality })
                                firstname = hcp.firstName
                                familyname = hcp.lastName
                            },
                            HcpartyType().apply {
                                cds.add(CDHCPARTY().apply { s = CD_HCPARTY; sv = versions["CD-HCPARTY"]; value = "application" })
                                name = config.softwarePackage.vendorName
                                telecoms.addAll(listOf(
                                        TelecomType().apply {
                                            cds.addAll(listOf(
                                                    CDTELECOM().apply { s = CD_ADDRESS; sv = versions["CD-ADDRESS"]; value = "work" },
                                                    CDTELECOM().apply { s = CD_TELECOM; sv = versions["CD-TELECOM"]; value = "phone" }
                                            ))
                                            telecomnumber = config.softwarePackage.phone
                                        },
                                        TelecomType().apply {
                                            cds.addAll(listOf(
                                                    CDTELECOM().apply { s = CD_ADDRESS; sv = versions["CD-ADDRESS"]; value = "work" },
                                                    CDTELECOM().apply { s = CD_TELECOM; sv = versions["CD-TELECOM"]; value = "email" }
                                            ))
                                            telecomnumber = config.softwarePackage.mail
                                        }
                                ))
                            }
                    ))
                }
                recipients.add(RecipientType().apply {
                    hcparties.add(HcpartyType().apply {
                        ids.add(IDHCPARTY().apply { s = ID_HCPARTY; sv = "1.0"; value = "RECIPE" })
                        cds.add(CDHCPARTY().apply { s = CD_HCPARTY; sv = versions["CD-HCPARTY"]; value = "orgpublichealth" })
                        name = "Recip-e"
                    })
                })
            }
            folder = RecipefolderType().apply {
                id = RecipebasicIDKMEHR().apply { s = ID_KMEHR; sv = "1.0"; value = "1" }
                this.patient = RecipepatientpersonType().apply {
                    ids.add(IDPATIENT().apply { s = ID_PATIENT; sv = "1.0"; value = patient.ssin })
                    patient.firstName?.let { firstnames.add(it) }
                    familyname = patient.lastName
                    patient.dateOfBirth?.let { birthdate = makeDateTypeFromFuzzyLong(it.toLong())!! }
                    patient.gender?.name?.let { gender ->
                        sex = SexType().apply {
                            cd = CDSEX().apply { s = "CD-SEX"; sv = versions["CD-SEX"]; value = CDSEXvalues.fromValue(gender) }
                        }
                    }
                }
                transaction = RecipetransactionType().apply {
                    id = RecipebasicIDKMEHR().apply { s = ID_KMEHR; sv = "1.0"; value = "1" }
                    cd = RecipeCDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION; sv = versions["CD-TRANSACTION"]; value = "pharmaceuticalprescription" }
                    date = config.header.date
                    time = config.header.time
                    // expirationDate?.let { expirationdate = makeXGC(expirationDate.time) } // deprecated as of Kmehr 1.18 - 20161201
                    author = RecipeauthorType().apply {
                        hcparties.add(HcpartyType().apply {
                            ids.add(IDHCPARTY().apply { s = ID_HCPARTY; sv = "1.0"; value = config.prescription.inami })
                            cds.add(CDHCPARTY().apply { s = CD_HCPARTY; sv = versions["CD-HCPARTY"]; value = hcpQuality })
                            firstname = hcp.firstName
                            familyname = hcp.lastName
                            val address = getPreferredAddress(hcp)
                            addresses.add(AddressType().apply {
                                cds.add(CDADDRESS().apply { s = ADDRESS_CD_ADDRESS; sv = versions["CD-ADDRESS"]; value = "work" })
                                country = CountryType().apply {
                                    cd = CDCOUNTRY().apply {
                                        s = CDCOUNTRYschemes.CD_FED_COUNTRY
                                        sv = "1.2"
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
                                cds.add(CDTELECOM().apply { s = CD_ADDRESS; sv = versions["CD-ADDRESS"]; value = "work" })
                                cds.add(CDTELECOM().apply { s = CD_TELECOM; sv = versions["CD-TELECOM"]; value = "phone" })
                                telecomnumber = address.telecoms.find { (it.telecomType == mobile || it.telecomType == phone) && !it.telecomNumber.isNullOrBlank() }?.telecomNumber ?: "+3220000000"
                            })
                        })
                    }
                    isIscomplete = true
                    isIsvalidated = true
                    heading = RecipetransactionheadingType().apply {
                        id = RecipebasicIDKMEHR().apply { s = ID_KMEHR; sv = "1.0"; value = "1" }
                        cd = RecipeCDHEADING().apply { s = CDHEADINGschemes.CD_HEADING; sv = versions["CD-HEADING"]; value = "prescription" }
                        medications.forEachIndexed { idx, med ->
                            items.add(RecipeitemType().apply {
                                id = RecipebasicIDKMEHR().apply { s = ID_KMEHR; sv = "1.0"; value = (idx + 1).toString() }
                                cd = RecipeCDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = versions["CD-ITEM"]; value = "medication" }
                                med.medicinalProduct?.intendedcds?.let {
                                    it.find { it.type == "CD-DRUG-CNK" }?.let { c ->
                                        if(c.code != "0000000"  || (med.compoundPrescription == null && med.compoundPrescriptionV2 == null)){
                                            content = RecipecontentType().apply {
                                                medicinalproduct = RecipemedicinalProductType().apply {
                                                    intendedcds.add(KmehrPrescriptionHelper.toCDDRUGCNK(c))
                                                    intendedname = med.medicinalProduct?.intendedname
                                                }
                                            }
                                        }
                                    }
                                }
                                med.substanceProduct?.intendedcds?.let {
                                    it.find { it.type == "CD-INNCLUSTER" || it.type == "CD-VMPGROUP" }?.let { c ->
                                        content = RecipecontentType().apply {
                                            substanceproduct = RecipecontentType.Substanceproduct().apply {
                                                intendedcd = RecipeCDINNCLUSTER().apply { s = CDINNCLUSTERschemes.fromValue(c.type); sv = config.prescription.substanceDb; value = c.code }
                                                intendedname = med.substanceProduct?.intendedname
                                            }
                                        }
                                    }
                                }
                                if (content?.medicinalproduct == null && content?.substanceproduct == null && content?.compoundprescription == null) {
                                    val compoundPrescription = med.compoundPrescriptionV2
                                    if (compoundPrescription != null) {
                                        content = RecipecontentType().apply {
                                            compoundprescription = RecipecompoundprescriptionType().apply {
                                                content.addAll(KmehrPrescriptionHelper.toCompoundPrescriptionElements(compoundPrescription, language))
                                            }
                                        }
                                    } else {
                                        (med.compoundPrescription ?: med.medicinalProduct?.intendedname ?: med.substanceProduct?.intendedname)?.let { text ->
                                            content = RecipecontentType().apply {
                                                compoundprescription = RecipecompoundprescriptionType().apply {
                                                    content.add(KmehrPrescriptionHelper.kmehrJaxbElement(
                                                            "magistraltext",
                                                            TextType().apply {
                                                                value = text
                                                                l = language
                                                            }
                                                                                                        ))
                                                }
                                            }
                                        }
                                    }
                                }
                                beginmoment = RecipemomentType().apply { date = makeXMLGregorianCalendarFromFuzzyLong(med.beginMoment ?: FuzzyValues.currentFuzzyDate ) }

                                med.endMoment?.let { endmoment = RecipemomentType().apply { date = makeXMLGregorianCalendarFromFuzzyLong(it) } }
                                val posologyText = med.getPosology(config.prescription.language)
                                if (!StringUtils.isEmpty(posologyText)) {
                                    posology = RecipeitemType.Posology().apply { text = TextType().apply { l = language; value = posologyText } }
                                }
                                lifecycle = RecipelifecycleType().apply { cd = CDLIFECYCLE().apply { s = "CD-LIFECYCLE"; sv = versions["CD-LIFECYCLE"]; value = CDLIFECYCLEvalues.PRESCRIBED } }
                                med.temporality?.let {
                                    temporality = RecipetemporalityType().apply {
                                        cd = CDTEMPORALITY().apply { s = "CD-TEMPORALITY"; sv = versions["CD-TEMPORALITY"]; value = CDTEMPORALITYvalues.fromValue(it.code) }
                                    }
                                }
                                if (med.substanceProduct?.intendedcds == null || med.substanceProduct!!.intendedcds.map { it.code }.all { it == "000000" }) {
                                    quantity = RecipequantityType().apply {
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
                                    regimen = RecipeitemType.Regimen().apply {
                                        for (intake in intakes) {
                                            // choice day specification
                                            intake.dayNumber?.let { dayNumber -> daynumbersAndQuantitiesAndDaytimes.add(BigInteger.valueOf(dayNumber.toLong())) }
                                            intake.date?.let { d -> daynumbersAndQuantitiesAndDaytimes.add(makeXMLGregorianCalendarFromFuzzyLong(d)) }
                                            intake.weekday?.let { day ->
                                                daynumbersAndQuantitiesAndDaytimes.add(RecipeitemType.Regimen.Weekday().apply {
                                                    day.weekDay?.let { dayOfWeek ->
                                                        cd = CDWEEKDAY().apply { s = "CD-WEEKDAY"; sv = versions["CD-WEEKDAY"]; value = CDWEEKDAYvalues.fromValue(dayOfWeek.code) }
                                                    }
                                                    day.weekNumber?.let { n -> weeknumber = BigInteger.valueOf(n.toLong()) }
                                                })
                                            }
                                            // choice time of day
                                            daynumbersAndQuantitiesAndDaytimes.add(toDaytime(intake))

                                            // mandatory quantity
                                            intake.administratedQuantity?.let { drugQuantity ->
                                                daynumbersAndQuantitiesAndDaytimes.add(RecipeadministrationquantityType().apply {
                                                    decimal = drugQuantity.quantity?.let { BigDecimal(it) }
                                                    drugQuantity.administrationUnit?.let { drugUnit ->
                                                        unit = AdministrationunitType().apply {
                                                            cd = CDADMINISTRATIONUNIT().apply {
                                                                s = "CD-ADMINISTRATIONUNIT"
                                                                sv = "1.2"
                                                                value = codeDao.ensureValid(drugUnit, ofType = "CD-ADMINISTRATIONUNIT", orDefault = Code("CD-ADMINISTRATIONUNIT", defaultDosis))?.code
                                                                if (value != drugUnit.code) {
                                                                    log.warn("illegal CD-ADMINISTRATIONUNIT ${drugUnit.code} changed to $defaultDosis")
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
                                    renewal = ReciperenewalType().apply {
                                        it.decimal?.let { decimal = BigDecimal(it.toLong()) }
                                        duration = toDurationType(it.duration)
                                    }
                                }
                                med.intakeRoute?.code?.let { c ->
                                    route = ReciperouteType().apply { cd = CDDRUGROUTE().apply { s = "CD-DRUG-ROUTE"; sv = versions["CD-DRUGROUTE"]; value = c } }
                                }
                                deliverydate = deliveryDate?.let { makeXMLGregorianCalendarFromFuzzyLong(FuzzyValues.getFuzzyDate(it, ChronoUnit.DAYS))}
                                instructionforpatient = toTextType(language, med.recipeInstructionForPatient)
                                med.instructionsForReimbursement?.translations?.get(language)?.let {
                                    instructionforreimbursement = toTextType(language, it)
                                }
                                isIssubstitutionallowed = med.substitutionAllowed
                            })
                        }
                    }
                }
            }
        }
    }

    private fun getPreferredAddress(hcp: HealthcareParty): Address {
        return hcp.addresses.find { it.addressType == org.taktik.freehealth.middleware.dto.AddressType.work || it.addressType == org.taktik.freehealth.middleware.dto.AddressType.clinic } ?: hcp.addresses.iterator().next() ?: throw IllegalArgumentException("${hcp.lastName} (${hcp.nihii}) has no address")
    }

    @Throws(ConnectorException::class)
    override fun createPrescription(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, patient: Patient, hcp: HealthcareParty, feedback: Boolean, medications: List<Medication>, prescriptionType: String?, notification: String?, executorId: String?, deliveryDate: LocalDateTime?): Prescription {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val selectedType: String = inferPrescriptionType(medications, prescriptionType)

        val m = getKmehrPrescription(patient, hcp, medications, deliveryDate, hcpQuality)

        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller().marshal(m, os)
        val prescription = os.toByteArray()

        val service = PrescriberIntegrationModuleImpl(stsService, keyDepotService)
        try {
            kmehrHelper.assertValidKmehrPrescription(ByteArrayInputStream(prescription), selectedType)
            log.debug("prescription $selectedType XML:\n${String(prescription)}")
        } catch (e: Exception) {
            log.error("Invalid $selectedType prescription XML:\n${String(prescription)}")
            throw e
        }

        val prescriptionId = service.createPrescription(keystore, samlToken, passPhrase, credential, hcpNihii, feedback, patient.ssin!!, prescription, selectedType)

        val result = Prescription(Date(), "", prescriptionId!!)

        if (notification != null && executorId != null) {
            try {
                val osn = ByteArrayOutputStream()
                JAXBContext.newInstance(RecipeNotification::class.java).createMarshaller().marshal(RecipeNotification().apply { text = notification; kmehrmessage = m }, osn)
                service.sendNotification(samlToken, credential, hcpNihii, osn.toByteArray(), patient.ssin!!, executorId)
                result.notificationWasSent = true
            } catch (e: IntegrationModuleException) {
                log.error("Notification could not be sent", e)
                result.notificationWasSent = false
            }
        }
        return result
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
            throw UnsupportedOperationException("CD-VMPGROUP not integrated yet. See ICCL-661")
        }
        return false
    }

    private fun getAdministrationCode(code: String): Code? {
        return codeDao.findCode("CD-ADMINISTRATIONUNIT", code, "1")
    }

    companion object {
        const val defaultDosis = "00006"
    }

    class UnsupportedCodeValueException(msg: String) : RuntimeException(msg)
}

