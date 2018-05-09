package org.taktik.icure.be.ehealth.logic.recipe.impl

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Assume.assumeThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.domain.Content
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.service.RecipeService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.icure.be.ehealth.logic.recipe.impl.RecipeTestUtils.Medications.Companion

/**
* @author Bernard Paulus on 4/04/17.
*/
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InferPrescriptionTypeTest(val recipeService: RecipeService, val stsService: STSService) {

    @Test
    fun forcedType() {
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(), "P0"))
        assertEquals("P1", recipeService.inferPrescriptionType(listOf(), "P1"))
        assertEquals("P2", recipeService.inferPrescriptionType(listOf(), "P2"))
    }

    @Test
    fun defaultPO() {
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(), null))
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(Medication()), null))
    }

    @Test
    fun medicinalProductPO() {
        val panadolPills = Companion.medicinalProductP0()
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(panadolPills), null))
    }

    @Test
    fun medicinalProductP1() {
        val paracetamolTeva = Companion.medicinalProductP1()
        assertEquals("P1", recipeService.inferPrescriptionType(listOf(paracetamolTeva), null))
    }

    @Test
    fun medicinalProduct0000000P0() {
        val paracetamolTeva = Companion.medicinalProduct0000000P0()
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(paracetamolTeva), null))
    }

    @Test
    fun reimbursed() {
        val panadolPills = Companion.medicinalProductP0()
        assumeThat(recipeService.inferPrescriptionType(listOf(panadolPills), null), equalTo("P0"))

        panadolPills.options = mutableMapOf(Medication.REIMBURSED to Content(true)) // fake options
        assertThat(recipeService.inferPrescriptionType(listOf(panadolPills), null), equalTo("P1"))
    }

    @Test
    fun substanceProductP0() {
        val panadolRegulatedDelivery = Companion.substanceProductP0()
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(panadolRegulatedDelivery), null))
    }

    @Test
    fun substanceProductP1() {
        val paracetamol = Companion.substanceProductP1()
        assertEquals("P1", recipeService.inferPrescriptionType(listOf(paracetamol), null))
    }

    @Test
    fun substanceProduct0000000P0() {
        val paracetamol = Companion.substanceProduct0000000P0()
        assertEquals("P0", recipeService.inferPrescriptionType(listOf(paracetamol), null))
    }

	@Test(expected = UnsupportedOperationException::class)
	fun substanceProductVMPGROUP_not_supported() {
		val aceclofenac = Companion.substanceProductVMPGROUP_P1()
		recipeService.inferPrescriptionType(listOf(aceclofenac), null)
	}

    @Test
    fun compoundPrescriptionP2() {
        val snakeOil = Companion.compoundPrescriptionP2()
        assertEquals("P2", recipeService.inferPrescriptionType(listOf(snakeOil), null))
    }

	@Test
	fun compoundPrescriptionP2Legacy() {
		val snakeOil = Companion.compoundPrescriptionP2Legacy()
		assertEquals("P2", recipeService.inferPrescriptionType(listOf(snakeOil), null))
	}
}
