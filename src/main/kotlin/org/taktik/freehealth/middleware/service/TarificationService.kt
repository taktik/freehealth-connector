package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import java.time.LocalDateTime
import java.util.UUID

interface TarificationService {
    fun consultTarif(keystoreId: UUID,
                     tokenId: UUID,
                     hcpFirstName: String,
                     hcpLastName: String,
                     hcpNihii: String,
                     hcpSsin: String,
                     passPhrase: String,
                     patientSsin: String?,
                     consultationDate: LocalDateTime,
                     justification: String?,
                     gmdNihii: String?,
                     codes: List<String>): TarificationConsultationResult
}
