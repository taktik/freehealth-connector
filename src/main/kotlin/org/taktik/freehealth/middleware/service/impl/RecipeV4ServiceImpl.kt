package org.taktik.freehealth.middleware.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.kmehr.v20161201.be.ehealth.logic.recipe.xsd.v20160906.RecipeNotification
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.recipe.prescriber.PrescriberIntegrationModuleV4Impl
import org.taktik.connector.business.recipe.utils.KmehrHelper
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.RecipeService
import org.taktik.freehealth.middleware.service.RecipeV4Service
import org.taktik.freehealth.middleware.service.STSService
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Date
import java.util.Properties
import java.util.UUID
import javax.xml.bind.JAXBContext

@Service
class RecipeV4ServiceImpl(private val stsService: STSService, private val recipeService: RecipeService, private val keyDepotService: KeyDepotService) : RecipeV4Service {
    val log = LoggerFactory.getLogger(this.javaClass)!!
    private val kmehrHelper = KmehrHelper(Properties().apply { load(RecipeServiceImpl::class.java.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    override fun createPrescriptionV4(keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        patient: Patient,
        hcp: HealthcareParty,
        feedback: Boolean,
        medications: List<Medication>,
        prescriptionType: String?,
        notification: String?,
        executorId: String?,
        deliveryDate: LocalDateTime?,
        vision: String?,
        expirationDate: LocalDateTime?): Prescription {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase) ?: throw IllegalArgumentException("Cannot obtain token for Recipe operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val selectedType: String = recipeService.inferPrescriptionType(medications, prescriptionType)

        val m = recipeService.getKmehrPrescription(patient, hcp, medications, deliveryDate, hcpQuality)

        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller().marshal(m, os)
        val prescription = os.toByteArray()

        val service = PrescriberIntegrationModuleV4Impl(stsService, keyDepotService)
        try {
            kmehrHelper.assertValidKmehrPrescription(ByteArrayInputStream(prescription), selectedType)
            log.debug("prescription $selectedType XML:\n${String(prescription)}")
        } catch (e: Exception) {
            log.error("Invalid $selectedType prescription XML:\n${String(prescription)}")
            throw e
        }

        val prescriptionId = service.createPrescription(keystore, samlToken, passPhrase, credential, hcpNihii, feedback, patient.ssin!!, prescription, selectedType, vision, expirationDate ?: LocalDateTime.now().plusMonths(3))

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
        return result    }
}
