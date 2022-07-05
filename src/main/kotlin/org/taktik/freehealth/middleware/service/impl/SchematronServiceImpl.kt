package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.services.schematron.SchematronValidationResult
import be.fgov.ehealth.services.schematron.SchematronValidator
import be.fgov.ehealth.services.schematron.impl.SchematronValidatorImpl
import org.springframework.stereotype.Service
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.SchematronService
import java.io.InputStream
import java.util.*

@Service
class SchematronServiceImpl(val stsService: STSService) : SchematronService {
    val validator: SchematronValidator = SchematronValidatorImpl()

    override fun validate(tokenId: UUID, schema: String, body: InputStream): SchematronValidationResult {
        if (!stsService.checkTokenValid(tokenId)) {
            throw MissingTokenException("Cannot obtain token for Schematron operations")
        }
        val schemaStream = this::class.java.classLoader.getResourceAsStream(
            "org/taktik/freehealth/middleware/schematron/${
                schema.replace(
                    Regex("[^A-Za-z]"),
                    ""
                )
            }.schematron.xml"
        )
        return validator.validate(body, schemaStream)
    }
}
