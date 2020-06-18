package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.dmg.DmgAcknowledge
import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.FlatFileWithMetadata
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import java.util.UUID

interface EfactService {

    fun sendBatch(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        batch: InvoicesBatch,
        hcpQuality: String): EfactSendResponse

    fun loadMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        language: String,
        limit: Int,
        hcpQuality: String
        ): List<EfactMessage>

    fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>,
        hcpQuality: String
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
        hcpQuality: String
    ): Boolean

    fun makeFlatFile(
        batch: InvoicesBatch,
        isTest: Boolean
    ): String

    fun makeFlatFileCoreWithMetadata(
        batch: InvoicesBatch,
        isTest: Boolean
    ): FlatFileWithMetadata
}
