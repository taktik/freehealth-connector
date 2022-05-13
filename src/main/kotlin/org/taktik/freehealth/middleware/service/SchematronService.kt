package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.services.schematron.SchematronValidationResult
import java.io.InputStream
import java.util.*

interface SchematronService {
 fun validate(tokenId: UUID, schema: String, body: InputStream): SchematronValidationResult
}
