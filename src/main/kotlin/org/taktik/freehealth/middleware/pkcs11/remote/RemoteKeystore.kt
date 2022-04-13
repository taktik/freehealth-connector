package org.taktik.freehealth.middleware.pkcs11.remote

import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import java.security.KeyStore
import java.util.*

class RemoteKeystore(uuid: UUID, remoteKeystoreService: RemoteKeystoreService) : KeyStore(remoteKeystoreService.getSpi(uuid), remoteKeystoreService.getProvider(uuid), "Remote")
