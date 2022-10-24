package org.taktik.freehealth.middleware.service.impl

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Assume
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
import org.taktik.freehealth.middleware.domain.recipe.Content
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils
import java.util.UUID

/**
 * @author Bernard Paulus on 3/04/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:/test.properties"])
class InferPrescTypeTest {

    @Autowired
    lateinit var stsService : STSServiceImpl
    @Autowired
    lateinit var recipeService : RecipeV4ServiceImpl

    @Value("\${org.taktik.icure.keystore1.ssin}") var ssin : String? = null
    @Value("\${org.taktik.icure.keystore1.nihii}") var nihii : String? = null
    @Value("\${org.taktik.icure.keystore1.password}") var passPhrase : String? = null

    private var tokenId: UUID? = null
    private var keystoreId: UUID? = null

    private lateinit var hcp: HealthcareParty
    private val patient = RecipeTestUtils.createPatient()

    init {
        System.setProperty("spring.output.ansi.enabled", "always")
    }

    @Before
    fun setup() {
        hcp = RecipeTestUtils.createHealthcareParty()
        keystoreId = stsService.uploadKeystore((MyTestsConfiguration::class).java.getResource("$ssin.acc-p12").readBytes())
        tokenId = stsService.requestToken(keystoreId!!, ssin!!, passPhrase!!)?.tokenId
    }

    @Test
    fun forcedType() {
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(), "P0"))
        Assert.assertEquals("P1", recipeService.inferPrescriptionType(listOf(), "P1"))
        Assert.assertEquals("P2", recipeService.inferPrescriptionType(listOf(), "P2"))
    }

    @Test
    fun defaultPO() {
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(), null))
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(Medication()), null))
    }

    @Test
    fun medicinalProductPO() {
        val panadolPills = RecipeTestUtils.Medications.medicinalProductP0()
        Assert.assertEquals("P1", recipeService.inferPrescriptionType(listOf(panadolPills), null))
    }

    @Test
    fun medicinalProductP1() {
        val paracetamolTeva = RecipeTestUtils.Medications.medicinalProductP1()
        Assert.assertEquals("P1", recipeService.inferPrescriptionType(listOf(paracetamolTeva), null))
    }

    @Test
    fun medicinalProduct0000000P0() {
        val paracetamolTeva = RecipeTestUtils.Medications.medicinalProduct0000000P0()
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(paracetamolTeva), null))
    }

    @Test
    fun reimbursed() {
        val panadolPills = RecipeTestUtils.Medications.medicinalProductP0()
        Assume.assumeThat(recipeService.inferPrescriptionType(listOf(panadolPills), null), CoreMatchers.equalTo("P0"))

        panadolPills.options = mutableMapOf(Medication.REIMBURSED to Content(true)) // fake options
        Assert.assertThat(recipeService.inferPrescriptionType(listOf(panadolPills), null), CoreMatchers.equalTo("P1"))
    }

    @Test
    fun substanceProductP0() {
        val panadolRegulatedDelivery = RecipeTestUtils.Medications.substanceProductP0()
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(panadolRegulatedDelivery), null))
    }

    @Test
    fun substanceProductP1() {
        val paracetamol = RecipeTestUtils.Medications.substanceProductP1()
        Assert.assertEquals("P1", recipeService.inferPrescriptionType(listOf(paracetamol), null))
    }

    @Test
    fun substanceProduct0000000P0() {
        val paracetamol = RecipeTestUtils.Medications.substanceProduct0000000P0()
        Assert.assertEquals("P0", recipeService.inferPrescriptionType(listOf(paracetamol), null))
    }

    @Test(expected = UnsupportedOperationException::class)
    fun substanceProductVMPGROUP_not_supported() {
        val aceclofenac = RecipeTestUtils.Medications.substanceProductVMPGROUP_P1()
        recipeService.inferPrescriptionType(listOf(aceclofenac), null)
    }

    @Test
    fun compoundPrescriptionP2() {
        val snakeOil = RecipeTestUtils.Medications.compoundPrescriptionP2()
        Assert.assertEquals("P2", recipeService.inferPrescriptionType(listOf(snakeOil), null))
    }

    @Test
    fun compoundPrescriptionP2Legacy() {
        val snakeOil = RecipeTestUtils.Medications.compoundPrescriptionP2Legacy()
        Assert.assertEquals("P2", recipeService.inferPrescriptionType(listOf(snakeOil), null))
    }
}
