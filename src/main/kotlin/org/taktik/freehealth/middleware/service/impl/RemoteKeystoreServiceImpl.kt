package org.taktik.freehealth.middleware.service.impl

import com.hazelcast.core.ISet
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDCard
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDKeyStoreSpi
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDProvider
import org.taktik.freehealth.middleware.pkcs11.remote.CardConnection
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import java.security.KeyStoreSpi
import java.security.Provider
import java.security.Security
import java.util.*

@Service
class RemoteKeystoreServiceImpl(val keystoreConnections: ISet<UUID>, val simpMessagingTemplate: SimpMessagingTemplate) : RemoteKeystoreService {
    val log = LoggerFactory.getLogger(this::class.java)
    val cardConnections = mutableMapOf<UUID, CardConnection>()

    override fun getSpi(uuid: UUID): KeyStoreSpi {
        return RemoteBeIDKeyStoreSpi(RemoteBeIDCard(getCardConnection(uuid)))
    }

    override fun registerConnection(uuid: UUID) {
        keystoreConnections.add(uuid)
        log.info("Registered to $uuid")
        simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", mapOf("action" to "connected", "keystoreId" to uuid.toString()))
    }

    override fun hasConnection(uuid: UUID) = keystoreConnections.contains(uuid)

    override fun getProvider(uuid: UUID): Provider {
        return RemoteBeIDProvider(RemoteBeIDCard(getCardConnection(uuid)))
    }

    override fun publishResponse(uuid: UUID, response: String) {
        val decoder = Base64.getDecoder()
        cardConnections[uuid]?.pushResponse(decoder.decode(response))
    }

    private fun getCardConnection(uuid: UUID) = cardConnections.getOrPut(uuid) { CardConnection(Messenger(uuid, simpMessagingTemplate)) }

    class Messenger(val uuid: UUID, val simpMessagingTemplate: SimpMessagingTemplate) {
        fun send(message: Any) {
            simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", message)
        }
    }

    init {
        Security.addProvider(RemoteBeIDProvider(null))
    }
}
