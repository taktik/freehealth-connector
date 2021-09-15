package org.taktik.freehealth.middleware.dao

interface KeystoreProviderService {
    fun getKeystore(user: User, key: String): ByteArray?
}
