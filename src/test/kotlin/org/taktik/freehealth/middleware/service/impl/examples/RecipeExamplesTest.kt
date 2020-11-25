package org.taktik.freehealth.middleware.service.impl.examples

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.business.recipe.utils.KmehrValidator
import org.taktik.freehealth.middleware.service.RecipeService
import org.taktik.icure.be.ehealth.logic.recipe.impl.KmehrPrescriptionConfig
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample1
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample10
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample11
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample12
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample13
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample14
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample15
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample16
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample17
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample18
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample19
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample2
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample20
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample21
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample3
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample4
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample5
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample5Legacy
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample6
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample7
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample8
import org.taktik.icure.be.ehealth.logic.recipe.impl.examples.prescriptionExample9
import java.io.ByteArrayOutputStream
import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeFactory

/**
 * @author Bernard Paulus on 5/04/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeExamplesTest(val recipeService: RecipeService) {
    // build even later than spring injection
    private val validator by lazy { KmehrValidator(recipeService) }

    @Test
    fun recipePPexample1() {
        val prescriptionExample = prescriptionExample1()
        val xml = javaClass.getResourceAsStream("recipePPexample1.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample2() {
        val prescriptionExample = prescriptionExample2()
        val xml = javaClass.getResourceAsStream("recipePPexample2.xml").readBytes()
        val prescriptionType = "P0"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample3() {
        val prescriptionExample = prescriptionExample3()
        val xml = javaClass.getResourceAsStream("recipePPexample3.xml").readBytes()
        val prescriptionType = "P0"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample4() {
        val prescriptionExample = prescriptionExample4()
        val xml = javaClass.getResourceAsStream("recipePPexample4.xml").readBytes()
        val prescriptionType = "P2"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample5() {
        val prescriptionExample = prescriptionExample5()
        val xml = javaClass.getResourceAsStream("recipePPexample5.xml").readBytes()
        val prescriptionType = "P2"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

	@Test
	fun recipePPexample5Legacy() {
		val prescriptionExample = prescriptionExample5Legacy()
		val xml = javaClass.getResourceAsStream("recipePPexample5.xml").readBytes()
		val prescriptionType = "P2"

		checkGeneratedXml(prescriptionExample, prescriptionType, xml)
	}

    @Test
    fun recipePPexample6() {
        val prescriptionExample = prescriptionExample6()
        val xml = javaClass.getResourceAsStream("recipePPexample6.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample7() {
        val prescriptionExample = prescriptionExample7()
        val xml = javaClass.getResourceAsStream("recipePPexample7.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample8() {
        val prescriptionExample = prescriptionExample8()
        val xml = javaClass.getResourceAsStream("recipePPexample8.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample9() {
        val prescriptionExample = prescriptionExample9()
        val xml = javaClass.getResourceAsStream("recipePPexample9.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample10() {
        val prescriptionExample = prescriptionExample10()
        val xml = javaClass.getResourceAsStream("recipePPexample10.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample11() {
        val prescriptionExample = prescriptionExample11()
        val xml = javaClass.getResourceAsStream("recipePPexample11.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample12() {
        val prescriptionExample = prescriptionExample12()
        val xml = javaClass.getResourceAsStream("recipePPexample12.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample13() {
        val prescriptionExample = prescriptionExample13()
        val xml = javaClass.getResourceAsStream("recipePPexample13.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample14() {
        val prescriptionExample = prescriptionExample14()
        val xml = javaClass.getResourceAsStream("recipePPexample14.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml, config = createBaseConfig(prescriptionExample.hcp.nihii!!).apply { prescription.language = "fr" })
    }

    @Test
    fun recipePPexample15() {
        val prescriptionExample = prescriptionExample15()
        val xml = javaClass.getResourceAsStream("recipePPexample15.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml, config = createBaseConfig(prescriptionExample.hcp.nihii!!).apply { prescription.language = "fr" })
    }

    @Test
    fun recipePPexample16() {
        val prescriptionExample = prescriptionExample16()
        val xml = javaClass.getResourceAsStream("recipePPexample16.xml").readBytes()
        val prescriptionType = "P1"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample17() {
        val prescriptionExample = prescriptionExample17()
        val xml = javaClass.getResourceAsStream("recipePPexample17.xml").readBytes()
        val prescriptionType = "P0"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample18() {
        val prescriptionExample = prescriptionExample18()
        val xml = javaClass.getResourceAsStream("recipePPexample18.xml").readBytes()
        val prescriptionType = "P2"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

    @Test
    fun recipePPexample19() {
        val prescriptionExample = prescriptionExample19()
        val xml = javaClass.getResourceAsStream("recipePPexample19.xml").readBytes()
        val prescriptionType = "P2"

        checkGeneratedXml(prescriptionExample, prescriptionType, xml)
    }

	@Test
	fun recipePPexample20() {
		// SAMv2 Webservice : currently not supported
		val aceclofenac = prescriptionExample20()
		val xml = javaClass.getResourceAsStream("recipePPexample20.xml").readBytes()
		val prescriptionType = "P0"

		checkGeneratedXml(aceclofenac, prescriptionType, xml,
			skipPrescriptionTypeCheck = true,
			config = createBaseConfig(aceclofenac.hcp.nihii!!).apply {
				header.date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-02-16")
				prescription.substanceDb = "WSSAMv2"
			}
		)
	}

	@Test
	fun recipePPexample21() {
		// SAMv2 local database : allow to generate XML but support yet
		val aceclofenac = prescriptionExample21()
		val xml = javaClass.getResourceAsStream("recipePPexample21.xml").readBytes()
		val prescriptionType = "P0"

		checkGeneratedXml(aceclofenac, prescriptionType, xml,
			skipPrescriptionTypeCheck = true,
			config = createBaseConfig(aceclofenac.hcp.nihii!!).apply {
				header.date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-02-16")
			})
	}

    private fun checkGeneratedXml(prescriptionExample: PrescriptionExample, prescriptionType: String, xml: ByteArray, skipPrescriptionTypeCheck: Boolean = false, config: KmehrPrescriptionConfig = createBaseConfig(prescriptionExample.hcp.nihii!!)) {
        validator.validatePrescription(xml, prescriptionType)
		if (!skipPrescriptionTypeCheck) {
			assumeThat(recipeService.inferPrescriptionType(prescriptionExample.medications, null), equalTo(prescriptionType))
        }
        assertXmlEquals(String(xml), toXmlPrescription(prescriptionExample, skipPrescriptionTypeCheck = skipPrescriptionTypeCheck, config = config))
    }

    fun assertXmlEquals(expected: String, actual: String) {
        val trimCleanEOL = "(^\\s*|\\s*\n\\s*|\\s*$)".toRegex(RegexOption.MULTILINE)
        val cleanXmlns = "\\s*xmlns(?::(\\w+))\\s*=\\s*\"[^\"]*\"(?:\\s+\\1:\\w+\\s*=\\s*\"[^\"]*\")?".toRegex()
        assertEquals("XML modified to remove indents and XML namespaces don't match", expected.replace(trimCleanEOL, "").replace(cleanXmlns, "").replace("><", ">\n<"), actual.replace(trimCleanEOL, "").replace(cleanXmlns, "").replace("><", ">\n<"))
    }

    fun toXmlPrescription(prescriptionExample: PrescriptionExample, config: KmehrPrescriptionConfig = createBaseConfig(prescriptionExample.hcp.nihii!!), skipPrescriptionTypeCheck: Boolean = false): String {
        val os = ByteArrayOutputStream()
		val kmehrPrescription = recipeService.getKmehrPrescription(prescriptionExample.patient, prescriptionExample.hcp, prescriptionExample.medications, prescriptionExample.deliveryDate, config, "doctor")
		JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller().marshal(kmehrPrescription, os)
        val prescription = os.toByteArray()
		if (!skipPrescriptionTypeCheck) {
			try {
				validator.validatePrescription(prescription,recipeService.inferPrescriptionType(prescriptionExample.medications, null))
			} catch (e: Exception) {
				throw AssertionError("xml problem in:\n${String(prescription)}", e)
			}
        }
        return String(prescription)
    }

    companion object {
        private fun createBaseConfig(hcpNihii : String): KmehrPrescriptionConfig {
            return KmehrPrescriptionConfig().apply {
                prescription.apply {
                    inami = hcpNihii.replace("[^0-9]".toRegex(), "")
                    language = "nl"
					substanceDb = "LOCALDB"
                }
                header.apply {
                    _idKhmerId = "20090110090000000"
                    date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2016-09-28")
                    time = DatatypeFactory.newInstance().newXMLGregorianCalendar("09:00:00")
                    messageId = "8e1c4ea4-3825-48e4-bcc2b8cadfa7a897"
                }
                softwarePackage.apply {
                    name = "ID-MEDISOFT"
                    version = "versie 1.23.25.0"
                    id = "8e1c4ea4-3825-48e4-bcc2b8cadfa7a897"
                    vendorName = "MySoftware"
                    phone = "02/100.11.12"
                    mail = "tom@mysoftware.com"
                }
            }
        }
    }
}
