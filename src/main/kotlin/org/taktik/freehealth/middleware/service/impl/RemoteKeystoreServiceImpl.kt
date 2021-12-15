package org.taktik.freehealth.middleware.service.impl

import com.google.common.cache.LoadingCache
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.ISet
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.taktik.freehealth.middleware.domain.sts.SamlTokenRequest
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.domain.sts.StompPrincipal
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDCard
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDKeyStoreSpi
import org.taktik.freehealth.middleware.pkcs11.beid.RemoteBeIDProvider
import org.taktik.freehealth.middleware.pkcs11.remote.CardConnection
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import org.taktik.freehealth.middleware.service.STSService
import java.security.KeyStore
import java.security.KeyStoreSpi
import java.security.Provider
import java.security.Security
import java.util.*

@Service
class RemoteKeystoreServiceImpl(
    private val keystoreCache: LoadingCache<Pair<UUID, String>, KeyStore>
    private val keystoreConnections: ISet<UUID>,
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val hazelcastInstance: HazelcastInstance,
    private val stsService: STSService
) : RemoteKeystoreService,
    ApplicationListener<SessionDisconnectEvent> {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val cardConnections = mutableMapOf<UUID, CardConnection>()

    override fun getSpi(uuid: UUID): KeyStoreSpi {
        return RemoteBeIDKeyStoreSpi(RemoteBeIDCard(getCardConnection(uuid)))
    }

    override fun registerConnection(uuid: UUID) {
        keystoreConnections.add(uuid)
        log.info("Registered to $uuid")
        simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", mapOf("action" to "connected", "keystoreId" to uuid.toString()))
    }

    override fun hasConnection(uuid: UUID) = keystoreConnections.contains(uuid)
    override fun hasLocalConnection(uuid: UUID) = cardConnections.containsKey(uuid)

    override fun getProvider(uuid: UUID): Provider {
        return RemoteBeIDProvider(RemoteBeIDCard(getCardConnection(uuid)))
    }

    override fun publishResponse(uuid: UUID, response: String) {
        val decoder = Base64.getDecoder()
        cardConnections[uuid]?.pushResponse(decoder.decode(response))
    }

    private fun getCardConnection(uuid: UUID) = cardConnections.getOrPut(uuid) {
        CardConnection(Messenger(uuid, simpMessagingTemplate)).also {
            hazelcastInstance.getTopic<SamlTokenRequest>("interKeyStore/${uuid}").addMessageListener { msg ->
                msg?.messageObject?.let { tr ->
                    try {
                        stsService.requestToken(
                            tr.keystoreId,
                            tr.nihiiOrSsin,
                            tr.passPhrase,
                            tr.quality,
                            tr.tokenId,
                            tr.extraDesignators,
                            keystoreCache.get(Pair(tr.keystoreId, tr.passPhrase))
                        )
                    }
                }
            }
        }
    }

    class Messenger(val uuid: UUID, private val simpMessagingTemplate: SimpMessagingTemplate) {
        fun send(message: Any) {
            simpMessagingTemplate.convertAndSend("/topic/sts/$uuid", message)
        }
    }

    init {
        Security.addProvider(RemoteBeIDProvider(null))
    }

    override fun onApplicationEvent(event: SessionDisconnectEvent) {
        (event.message.headers["simpUser"] as? StompPrincipal)?.let {
            val uuid = UUID.fromString(it.name)
            keystoreConnections.remove(uuid)
            cardConnections.remove(uuid)
        }
    }
}
