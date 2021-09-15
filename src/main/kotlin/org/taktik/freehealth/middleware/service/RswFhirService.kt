package org.taktik.freehealth.middleware.service

import org.taktik.icure.fhir.entities.r4.bundle.Bundle
import java.util.UUID

interface RswFhirService {
    fun search(nihii: String,
        clientId: String,
        clientType: String,
        clientSecret: String,
        keystoreId: UUID,
        passPhrase: String,
        patientSsin: String): Bundle

}
