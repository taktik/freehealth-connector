package org.taktik.freehealth.middleware.service

import java.security.KeyStoreSpi
import java.security.Provider
import java.util.*

interface RemoteKeystoreService {
    fun getSpi(uuid: UUID): KeyStoreSpi
    fun getProvider(uuid: UUID): Provider
    fun registerConnection(uuid: UUID)
    fun hasConnection(uuid: UUID): Boolean
    fun publishResponse(uuid: UUID, response: String)
}
