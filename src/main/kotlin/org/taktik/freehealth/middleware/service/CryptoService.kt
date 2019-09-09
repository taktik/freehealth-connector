package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.ehbox.api.domain.Addressee
import java.util.UUID

interface CryptoService {
    fun encrypt(
        keystoreId: UUID,
        passPhrase: String,
        addressee: Addressee,
        plainData: ByteArray
        ): ByteArray?

    fun decrypt(keystoreId: UUID, passPhrase: String, encryptedData: ByteArray): ByteArray?
}
