package org.taktik.freehealth.middleware.service.impl

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.fail
import org.junit.Assume.assumeThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipecompoundType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeformularyreferenceType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipegalenicformType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipequantityType
import org.taktik.connector.business.recipe.utils.KmehrValidator
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.recipe.CompoundPrescription
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.impl.examples.PrescriptionExample
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample1
import java.io.Serializable
import java.util.UUID
import java.util.regex.Pattern
import javax.xml.bind.JAXBElement

/**
 * @author Bernard Paulus on 18/04/17.
 */

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:/test.properties"])
class RecipeStories {

    @Autowired
    lateinit var stsService : STSServiceImpl
    @Autowired
    lateinit var recipeService : RecipeV4ServiceImpl

    @Value("\${org.taktik.icure.keystore1.ssin}") var ssin : String? = null
    @Value("\${org.taktik.icure.keystore1.nihii}") var nihii : String? = null
    @Value("\${org.taktik.icure.keystore1.password}") var passPhrase : String? = null

    private var tokenId: UUID? = null
    private var keystoreId: UUID? = null

    private var hcp: HealthcareParty = RecipeTestUtils.createHealthcareParty()
    private val patient = RecipeTestUtils.createPatient()
    private val validator by lazy { KmehrValidator(recipeService) }

    init {
        System.setProperty("spring.output.ansi.enabled", "always")
    }

    @Before
    fun setup() {
        keystoreId = stsService.uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin.acc-p12").readBytes())
        tokenId = stsService.requestToken(keystoreId!!, ssin!!, passPhrase!!)?.tokenId
    }

    private val prescriptions = listOf(
    prescriptionExample1()/*,
    prescriptionExample2(),
    prescriptionExample3(),
    prescriptionExample4(),
    prescriptionExample5(),
    prescriptionExample6(),
    prescriptionExample7(),
    prescriptionExample8(),
    prescriptionExample9(),
    prescriptionExample10(),
    prescriptionExample11(),
    prescriptionExample12(),
    prescriptionExample13(),
    prescriptionExample14(),
    prescriptionExample15(),
    prescriptionExample16(),
    prescriptionExample17(),
    prescriptionExample18(),
    prescriptionExample19()*/
    ).map { it.copy(hcp = hcp) }

    @Test
    fun prescription_createGet_requestedEqualsResult() {
        prescriptions.forEach { prescription_createGet_requestedEqualsResult(it) }
    }

    @Test
    fun prescription_doubleCreate_ridsDifferent() {
        prescriptions.forEach { prescription_doubleCreate_ridsDifferent(it) }
    }

    @Test
    fun prescription_createListOpenByPatient_ridInList() {
        prescriptions.forEach { prescription_createListOpenByPatient_ridInList(it) }
    }

    @Test
    fun prescription_createWithNotificationGet_requestedEqualsResult() {
        prescriptions.forEach { prescription_createWithNotificationGet_requestedEqualsResult(it) }
    }

    @Test
    fun prescription_createSendNotification_noException() {
        prescriptions.forEach { prescription_createSendNotification_noException(it) }
    }

    @Test
    fun prescription_createWithNotificationSendNotification_noException() {
        prescriptions.forEach { prescription_createWithNotificationSendNotification_noException(it) }
    }

    @Test
    fun prescription_createUpdateFeedback_noException() {
        prescriptions.forEach { prescription_createUpdateFeedback_noException(it) }
    }

    @Test
    fun prescription_createFeedbackTrueUpdateFeedbackFalse_noException() {
        prescriptions.forEach { prescription_createFeedbackTrueUpdateFeedbackFalse_noException(it) }
    }

    @Test
    fun prescription_createUpdateFeedbackRevertFeedback_noException() {
        prescriptions.forEach { prescription_createUpdateFeedbackRevertFeedback_noException(it) }
    }

    @Test
    fun prescription_createRevokeGet_revokedException() {
        prescriptions.forEach { prescription_createRevokeGet_revokedException(it) }
    }

    @Test
    fun prescription_createListByPatientsRevokeListByPatients_revokedNotInOpenPrescriptions() {
        prescriptions.forEach { prescription_createListByPatientsRevokeListByPatients_revokedNotInOpenPrescriptions(it) }
    }

    @Test
    fun prescription_createRevokeSendNotification_revokedException() {
        prescriptions.forEach { prescription_createRevokeSendNotification_revokedException(it) }
    }

    @Test
    fun prescription_createRevokeUpdateFeedbackFlag_revokedException() {
        prescriptions.forEach { prescription_createRevokeUpdateFeedbackFlag_revokedException(it) }
    }

    fun prescription_createGet_requestedEqualsResult(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        val retrievedPrescription = getPrescription(createdPrescription.rid)

        assertEqualPrescriptions(prescription, retrievedPrescription!!)
    }

    fun prescription_doubleCreate_ridsDifferent(prescription: PrescriptionExample) {
        val createdPrescription1 = createPrescription(prescription)
        val createdPrescription2 = createPrescription(prescription)
        assertThat(createdPrescription1.rid, not(equalTo(createdPrescription2.rid)))
    }

    fun prescription_createListOpenByPatient_ridInList(prescription: PrescriptionExample): Prescription {
        val createdPrescription = createPrescription(prescription)
        val openPrescriptions = listOpenPrescriptionsByPatient(prescription.patient.ssin!!)
        assumeThat("cannot find back ${createdPrescription.rid} in open prescriptions: list is empty", openPrescriptions.size, greaterThan(0))
        val rids = openPrescriptions.map { it.rid }
        assumeThat(rids.toString(), rids, contains(createdPrescription.rid))
        fail("test case currently not working. If you get to this line, it means Recip-E fixed it, so remove it and change the assumeThat() to assertThat()")
        return createdPrescription
    }

    fun prescription_createWithNotificationGet_requestedEqualsResult(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription, notification = "create notification")
        val retrievedPrescription = getPrescription(createdPrescription.rid)

        assertEqualPrescriptions(prescription, retrievedPrescription!!)
    }

    fun prescription_createSendNotification_noException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        sendNotification(prescription.patient.ssin!!, createdPrescription.rid, "27456344", "regular notification")
    }

    fun prescription_createWithNotificationSendNotification_noException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription, notification = "create notification")
        sendNotification(prescription.patient.ssin!!, createdPrescription.rid, "27456344", "regular notification")
    }

    fun prescription_createUpdateFeedback_noException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        updateFeedbackFlag(createdPrescription.rid, true)
    }

    fun prescription_createFeedbackTrueUpdateFeedbackFalse_noException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription, feedbackRequested = true)
        updateFeedbackFlag(createdPrescription.rid, false)
    }

    fun prescription_createUpdateFeedbackRevertFeedback_noException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        updateFeedbackFlag(createdPrescription.rid, true)
        updateFeedbackFlag(createdPrescription.rid, false)
    }

    fun prescription_createRevokeGet_revokedException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        revokePrescription(createdPrescription.rid)
        assertThrowsIntegrationModuleException({ getPrescription(createdPrescription.rid) }, "ERR100028")
    }

    fun prescription_createListByPatientsRevokeListByPatients_revokedNotInOpenPrescriptions(prescription: PrescriptionExample) {
        val createdPrescription = prescription_createListOpenByPatient_ridInList(prescription)
        revokePrescription(createdPrescription.rid)
        val openPrescriptions = listOpenPrescriptionsByPatient(prescription.patient.ssin!!)
        assertThat(openPrescriptions.map { it.rid }, not(contains(createdPrescription.rid)))
    }

    fun prescription_createRevokeSendNotification_revokedException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        revokePrescription(createdPrescription.rid)
        assertThrowsIntegrationModuleException(
                { sendNotification(prescription.patient.ssin!!, createdPrescription.rid, "27456344", "notify revoked... this should not work") },
                "ERR100028")
    }

    fun prescription_createRevokeUpdateFeedbackFlag_revokedException(prescription: PrescriptionExample) {
        val createdPrescription = createPrescription(prescription)
        revokePrescription(createdPrescription.rid)
        assertThrowsIntegrationModuleException({ updateFeedbackFlag(createdPrescription.rid, true) }, "ERR100028")
    }

    // clean API functions

    private fun getPrescription(rid: String): Kmehrmessage? {
        return recipeService.getPrescriptionMessage(keystoreId!!, tokenId!!, passPhrase!!, nihii!!, rid)
    }

    private fun createPrescription(prescription: PrescriptionExample, notification: String? = null, feedbackRequested: Boolean = false): Prescription {
        val executorId = null
        val createdPrescription = recipeService.createPrescription(keystoreId!!, tokenId!!, passPhrase!!, "persphysican", nihii!!, prescription.patient, prescription.hcp, feedbackRequested, prescription.medications, recipeService.inferPrescriptionType(prescription.medications, null), notification, executorId, "1.0", prescription.deliveryDate)
        return createdPrescription
    }

    private fun listOpenPrescriptionsByPatient(patientSsin: String): List<Prescription> {
        return recipeService.listOpenPrescriptions(keystoreId!!, tokenId!!, passPhrase!!, nihii!!, patientSsin)
    }

    private fun sendNotification(patientSsin: String, prescriptionId: String, pharmacyId: String, notificationTest: String) {
        recipeService.sendNotification(keystoreId!!, tokenId!!, passPhrase!!, nihii!!, patientSsin, pharmacyId, prescriptionId, notificationTest)
    }

    fun listFeedbacks(): List<Feedback> {
        return recipeService.listFeedbacks(keystoreId!!, tokenId!!, passPhrase!!)
    }

    private fun updateFeedbackFlag(rid: String, feedbackFlag: Boolean) {
        recipeService.updateFeedbackFlag(keystoreId!!, tokenId!!, passPhrase!!, nihii!!, rid, feedbackFlag)
    }

    private fun revokePrescription(rid: String) {
        recipeService.revokePrescription(keystoreId!!, tokenId!!, passPhrase!!, nihii!!, rid, "I want to revoke this prescription")
    }

    // special assertion functions

    private fun assertEqualPrescriptions(prescription: PrescriptionExample, retrievedPrescription: Kmehrmessage) {
        val folder = retrievedPrescription.folder
        assertThat(folder.patient.ids[0].value, equalTo(prescription.patient.ssin))
        val transaction = folder.transaction
        val authorHcp = assertSingle(transaction.author.hcparties)
        assertThat(assertSingle(authorHcp.ids).value, equalTo(prescription.hcp.nihii))
        val heading = transaction.heading
        assertThat(heading.items.size, equalTo(prescription.medications.size))
        heading.items.zip(prescription.medications).forEachIndexed { index, pair ->
            val (items, it) = pair
            val item = items.content
            val itemStringRepresentation = when {
                item.medicinalproduct != null -> "(product ${item.medicinalproduct.intendedcds?.joinToString { it.value }})"
                item.substanceproduct != null -> "(substance ${item.substanceproduct.intendedcd!!.value})"
                item.compoundprescription != null -> item.compoundprescription.content.joinToString(separator = "", transform = {
                    toItemCompoundStringRepresentation(it)
                })
                else -> null

            }
            val compoundPrescription = it.compoundPrescriptionV2
            val medicationStringRepresentation = when {
                it.medicinalProduct != null -> "(product ${it.medicinalProduct?.intendedcds?.joinToString { it.code ?: "N/A" }})"
                it.substanceProduct != null -> "(substance ${it.substanceProduct?.intendedcds?.joinToString { it.code ?: "N/A" }})"
                compoundPrescription != null -> when (compoundPrescription) {
                    is CompoundPrescription.Compounds -> compoundPrescription.compounds.joinToString(separator = "", transform = { "(compound ${it.medicinalProduct?.intendedcds?.get(0)?.code ?: it.substanceProduct?.name})" })
                    is CompoundPrescription.MagistralText -> "(text ${compoundPrescription.text})"
                    is CompoundPrescription.FormularyReference.FormularyName -> "(formulary ${compoundPrescription.name})"
                    is CompoundPrescription.FormularyReference.Formulary -> "(formulary ${compoundPrescription.formularyId}-${compoundPrescription.reference?.code})"
                } + (compoundPrescription.galenicForm?.let { "(galenicform ${it.text ?: it.galenicForm?.code})" } ?: "") + (compoundPrescription.quantity?.let { "(quantity ${it.amount.toString() + " " + it.unit?.code})" } ?: "")

                else -> null
            }
            assertThat("$index", itemStringRepresentation, equalTo(medicationStringRepresentation))
        }
    }

    private fun toItemCompoundStringRepresentation(it: Serializable?): CharSequence {
        return when (it) {
            is RecipecompoundType -> "(compound ${it.medicinalproduct?.intendedcds?.get(0)?.value ?: it.substance?.substancename})"
            is RecipegalenicformType -> "(galenicform ${it.galenicformtext?.value ?: it.cd?.value})"
            is RecipequantityType -> "(quantity ${it.decimal?.toString() + " " + it.unit?.cd?.value})"
            is RecipeformularyreferenceType -> "(formulary ${it.formularyname ?: it.cds?.joinToString(separator = "-", transform = { it.value ?: "" })})"
            is TextType -> "(text ${it.value})"
            is JAXBElement<*> -> toItemCompoundStringRepresentation(it.value as Serializable)
            null -> "null"
            else -> it.toString()
        }
    }

    private fun assertThrowsIntegrationModuleException(operation: () -> Unit, errorCode: String) {
        if (isThrowingIntegrationModuleException(operation, errorCode) != null) {
            fail("the above call should throw")
        }
    }

    private fun <T> isThrowingIntegrationModuleException(operation: () -> T, vararg errorCodes: String): T? {
        try {
            val result = operation()
            assertThat("operation result should not be null", operation, notNullValue())
            return result
        } catch (e: IntegrationModuleException) {
            val matcher = Pattern.compile("\\[(.*?)].*").matcher(e.message!!)
            if (!matcher.matches() || !errorCodes.contains(matcher.group(1))) {
                throw e
            }
            // got "revoked" error code => success
        }
        return null
    }

    private fun <T> assertSingle(items: MutableList<T>): T {
        assertThat(items.size, equalTo(1))
        return items[0]
    }
}
