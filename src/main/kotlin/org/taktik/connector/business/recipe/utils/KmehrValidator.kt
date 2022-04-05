package org.taktik.connector.business.recipe.utils

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.slf4j.LoggerFactory
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipeKmehrmessageType
import org.taktik.connector.technical.validator.impl.handler.ErrorCollectorHandler
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.service.RecipeV4Service
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Properties
import javax.xml.bind.JAXBContext
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

/**
 * validate Kmehr messages
 */

class KmehrValidator(val kmehrXsd: String = "ehealth-kmehr/XSD/recipe/recipe_PP_kmehr_elements-1_28.xsd") {
    val log = LoggerFactory.getLogger(KmehrValidator::class.java)
    val kmehrHelper = KmehrHelper(Properties().apply {
        load(KmehrValidator::class.java.getResourceAsStream("/org/taktik/connector/business/recipe/validation.properties"))
    })

    fun validatePrescription(prescription: ByteArray, prescriptionType: String) {
        log.warn("Validating: "+prescription.toString(Charsets.UTF_8))
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
