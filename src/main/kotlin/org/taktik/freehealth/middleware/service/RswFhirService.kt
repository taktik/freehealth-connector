package org.taktik.freehealth.middleware.service

import java.util.UUID

interface RswFhirService {
    fun search(nihii: String,
        clientId: String,
        clientType: String,
        clientSecret: String,
        keystoreId: UUID,
        passPhrase: String,
        patientSsin: String): Any

}
