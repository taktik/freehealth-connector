package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import java.security.KeyStore
import java.util.UUID

interface KgssService {
    fun remove(key: UUID): KeyResult?
    fun containsKey(key: UUID): Boolean
    fun getNewKey(keystoreId: UUID, keyStore: KeyStore, passPhrase: String, allowedReaders: List<CredentialType>, myEtk: ByteArray): KeyResult
    fun flushCache()
}
