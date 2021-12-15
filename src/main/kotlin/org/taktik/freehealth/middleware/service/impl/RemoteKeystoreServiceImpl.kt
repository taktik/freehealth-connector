package org.taktik.freehealth.middleware.service.impl

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.ISet
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.taktik.freehealth.middleware.domain.sts.StompPrincipal
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDCard
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDKeyStoreSpi
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDProvider
import org.taktik.freehealth.middleware.pkcs11.remote.RelayCardConnection
import org.taktik.freehealth.middleware.pkcs11.remote.StompCardConnection
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import java.security.KeyStoreSpi
import java.security.Provider
import java.security.Security
import java.util.*

@Service
class RemoteKeystoreServiceImpl(val keystoreConnections: ISet<UUID>, val simpMessagingTemplate: SimpMessagingTemplate, val hazelcastInstance: HazelcastInstance) : RemoteKeystoreService,
    ApplicationListener<SessionDisconnectEvent> {
    val log = LoggerFactory.getLogger(this::class.java)
    val cardConnections = mutableMapOf<UUID, StompCardConnection>()

    init {
        Security.addProvider(RemoteBeIDProvider(null))
    }

    override fun getSpi(uuid: UUID): KeyStoreSpi {
        return RemoteBeIDKeyStoreSpi(RemoteBeIDCard(cardConnections.get(uuid) ?: RelayCardConnection(uuid, hazelcastInstance)))
    }

    override fun registerConnection(uuid: UUID) {
        keystoreConnections.add(uuid)
        cardConnections.putIfAbsent(uuid, StompCardConnection(uuid, Messenger(uuid, simpMessagingTemplate), hazelcastInstance))
        log.info("Registered to $uuid")
        simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", mapOf("action" to "connected", "keystoreId" to uuid.toString()))
    }

    override fun hasConnection(uuid: UUID) = keystoreConnections.contains(uuid)

    override fun getProvider(uuid: UUID): Provider {
        return RemoteBeIDProvider(RemoteBeIDCard(cardConnections.get(uuid) ?: RelayCardConnection(uuid, hazelcastInstance)))
    }

    override fun publishResponse(uuid: UUID, response: String) {
        val decoder = Base64.getDecoder()
        cardConnections[uuid]?.pushResponse(decoder.decode(response))
    }

    class Messenger(val uuid: UUID, private val simpMessagingTemplate: SimpMessagingTemplate) {
        fun send(message: Any) {
            simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", message)
        }
    }

    override fun onApplicationEvent(event: SessionDisconnectEvent) {
        (event.message.headers["simpUser"] as? StompPrincipal)?.let {
            val uuid = UUID.fromString(it.name)
            keystoreConnections.remove(uuid)
            cardConnections.remove(uuid)
        }
    }
}
