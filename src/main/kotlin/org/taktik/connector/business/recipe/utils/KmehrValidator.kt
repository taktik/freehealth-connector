package org.taktik.connector.business.recipe.utils

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.slf4j.LoggerFactory
import org.taktik.connector.technical.validator.impl.handler.ErrorCollectorHandler
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.service.RecipeService
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Properties
import javax.xml.bind.JAXBContext
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

/**
 * validate Kmehr messages
 */

class KmehrValidator(val recipeService : RecipeService, val kmehrXsd: String = "/ehealth-kmehr/XSD/kmehr_elements-1_19.xsd") {
    val log = LoggerFactory.getLogger(KmehrValidator::class.java)
    val kmehrHelper = KmehrHelper(Properties().apply { load(javaClass.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties")) })

    fun validatePrescription(kmehrPrescription: org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage, medications: List<Medication>) {
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage::class.java).createMarshaller().marshal(kmehrPrescription, os)
        val prescription = os.toByteArray()

        validatePrescription(prescription, medications)
    }
    fun validatePrescription(kmehrPrescription: Kmehrmessage, medications: List<Medication>) {
        val os = ByteArrayOutputStream()
        JAXBContext.newInstance(Kmehrmessage::class.java).createMarshaller().marshal(kmehrPrescription, os)
        val prescription = os.toByteArray()

        validatePrescription(prescription, medications)
    }

    fun validatePrescription(prescription: ByteArray, medications: List<Medication>) {
        val prescriptionType = recipeService.inferPrescriptionType(medications, null)
        validatePrescription(prescription, prescriptionType)
    }

    fun validatePrescription(prescription: ByteArray, prescriptionType: String) {
        val errors = validateStream(prescription)

        assert(errors.isEmpty(), { errors.toString() + " in:\n" + String(prescription) })
        kmehrHelper.assertValidKmehrPrescription(prescription, prescriptionType)
    }

    fun validateStream(xmlDocument: ByteArray): List<String> {
        val s = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(this.javaClass.classLoader.getResource(kmehrXsd))
        s?.newValidator()?.let { v ->
            val ech = ErrorCollectorHandler()
            v.errorHandler = ech
            v.validate(StreamSource(ByteArrayInputStream(xmlDocument)))

            ech.getExceptionList("WARN").forEach { log.warn(it) }
            return ech.getExceptionList("ERROR", "FATAL")
        }
        return emptyList()
    }

}
