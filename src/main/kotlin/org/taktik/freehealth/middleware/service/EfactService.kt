package org.taktik.freehealth.middleware.service

import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import java.util.UUID

interface EfactService {

    fun sendBatch(keystoreId: UUID, tokenId: UUID, passPhrase: String, batch: InvoicesBatch): EfactSendResponse
    fun loadMessages(keystoreId: UUID,
                     tokenId: UUID,
                     passPhrase: String,
                     hcpNihii: String,
                     hcpSsin: String,
                     hcpFirstName: String,
                     hcpLastName: String,
                     language: String): List<EfactMessage>

    fun makeFlatFile(batch: InvoicesBatch, isTest: Boolean): String
}
