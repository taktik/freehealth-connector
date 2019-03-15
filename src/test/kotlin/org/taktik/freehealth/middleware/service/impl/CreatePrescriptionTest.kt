package org.taktik.freehealth.middleware.service.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.business.recipe.utils.KmehrValidator
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.compoundPrescriptionP2
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.medicinalProductP0
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.medicinalProductP1
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.substanceProductP0
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion.substanceProductP1
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import javax.xml.bind.JAXBContext

/**
 * @author Bernard Paulus on 3/04/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:/test.properties"])
class CreatePrescriptionTest {

    @Autowired
    lateinit var stsService : STSServiceImpl
    @Autowired
    lateinit var recipeService : RecipeServiceImpl

    @Value("\${org.taktik.icure.keystore1.ssin}") var ssin : String? = null
    @Value("\${org.taktik.icure.keystore1.nihii}") var nihii : String? = null
    @Value("\${org.taktik.icure.keystore1.password}") var passPhrase : String? = null

    private var tokenId: UUID? = null
    private var keystoreId: UUID? = null

    private lateinit var hcp: HealthcareParty
    private val patient = RecipeTestUtils.createPatient()
    private val validator by lazy { KmehrValidator(recipeService) }

    init {
        System.setProperty("spring.output.ansi.enabled", "always")
    }

    @Before
    fun setup() {
        hcp = RecipeTestUtils.createHealthcareParty()
        keystoreId = stsService.uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin.acc-p12").readBytes())
        tokenId = stsService.requestToken(keystoreId!!, ssin!!, passPhrase!!, false)!!.tokenId
    }


    @Test
    fun testMedicinalProductP0() {
        testCreatePrescription(medicinalProductP0())
    }

    @Test
    fun testMedicinalProductP1() {
        testCreatePrescription(medicinalProductP1())
    }

    @Test
    fun testSubstanceProductP0() {
        testCreatePrescription(substanceProductP0())
    }

    @Test
    fun testSubstanceProductP1() {
        testCreatePrescription(substanceProductP1())
    }

    @Test
    fun testCompoundPrescriptionP2() {
        testCreatePrescription(compoundPrescriptionP2())
    }

    fun testCreatePrescription(medication: Medication) {
        assertKmehrValid(medication)
        val infos = createTestPrescription(listOf(medication))
        Assert.assertTrue(infos.rid.trim() != "")
    }

    private fun createTestPrescription(medications: List<Medication>): Prescription {

        //InsurabilityInfo infos = generalInsurabilityLogic.getGeneralInsurabity(niss, null, null, "T@kt1k1Cur3", "/Users/aduchate/ehealth/keystore", "SSIN=79121430944 20121128-151901.acc-p12");

        val type = recipeService.inferPrescriptionType(medications, null)
        val infos = recipeService.createPrescription(keystoreId!!, tokenId!!, "persphysician", hcp.nihii!!, hcp.ssin!!, hcp.lastName!!, passPhrase!!, patient, hcp, true, medications, type, null, null, LocalDateTime.now())
        return infos
    }

    fun assertKmehrValid(medication: Medication) {
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createMarshaller().marshal(recipeService.getKmehrPrescription(patient, hcp, listOf(medication), null), os)
        val prescription = os.toByteArray()
        validator.validatePrescription(prescription, recipeService.inferPrescriptionType(listOf(medication), null))

    }
}
