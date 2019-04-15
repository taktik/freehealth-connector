package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.dmg.DmgAcknowledge
import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import java.util.UUID

interface EfactService {

    fun sendBatch(keystoreId: UUID, tokenId: UUID, passPhrase: String, batch: InvoicesBatch, isGuardPost: Boolean): EfactSendResponse
    fun loadMessages(keystoreId: UUID,
                     tokenId: UUID,
                     passPhrase: String,
                     hcpNihii: String,
                     hcpSsin: String,
                     hcpFirstName: String,
                     hcpLastName: String,
                     language: String,
                     isGuardPost: Boolean): List<EfactMessage>
    fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>,
        isGuardPost: Boolean
    ): Boolean

    fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>,
        isGuardPost: Boolean
    ): Boolean

    fun makeFlatFile(batch: InvoicesBatch, isTest: Boolean): String
}
