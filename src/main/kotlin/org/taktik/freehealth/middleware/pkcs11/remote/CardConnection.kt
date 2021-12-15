package org.taktik.freehealth.middleware.pkcs11.remote

import be.fedict.commons.eid.client.FileType
import be.fedict.commons.eid.client.impl.BeIDDigest
import com.hazelcast.core.HazelcastInstance
import org.taktik.freehealth.middleware.domain.sts.CardConnectionRelay
import org.taktik.freehealth.middleware.service.impl.RemoteKeystoreServiceImpl
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

interface CardConnection {
    fun disconnect()
    fun sign(digestValue: ByteArray, digestAlgo: BeIDDigest, fileType: FileType, requireSecureReader: Boolean): ByteArray
    fun getChallenge(size: Int): ByteArray
    fun logoff()
    fun readFile(fileName: String): ByteArray
}

class RelayCardConnection(val uuid: UUID, private val  hazelcastInstance: HazelcastInstance): CardConnection {
    override fun disconnect() {
        TODO("Not yet implemented")
    }

    override fun sign(
        digestValue: ByteArray,
        digestAlgo: BeIDDigest,
        fileType: FileType,
        requireSecureReader: Boolean
    ) = relayRequest(
        CardConnectionRelay(
            "sign",
            Base64.getEncoder().encodeToString(digestValue),
            digestAlgo.name
        )
    )

    private fun relayRequest(ccr: CardConnectionRelay): ByteArray {
        val q = LinkedBlockingQueue<ByteArray?>()
        val id = hazelcastInstance.getTopic<ByteArray>("/cc/relay/response/${uuid}").addMessageListener {
            if (it.messageObject.size == 1) q.put(null) else q.put(it.messageObject)
        }
        hazelcastInstance.getTopic<CardConnectionRelay>("/cc/relay/request/${uuid}").publish(ccr)
        return q.poll(2, TimeUnit.MINUTES)?.also {
            hazelcastInstance.getTopic<ByteArray>("/cc/relay/response/${uuid}").removeMessageListener(id)
        } ?: throw TimeoutException("Timeout reached while waiting for eid signature from hzc relay")
    }

    override fun getChallenge(size: Int): ByteArray {
        TODO("Not yet implemented")
    }

    override fun logoff() {
        TODO("Not yet implemented")
    }

    override fun readFile(fileName: String) = relayRequest(CardConnectionRelay("sign", fileName))

}

class StompCardConnection(val uuid: UUID, val messenger: RemoteKeystoreServiceImpl.Messenger, private val  hazelcastInstance: HazelcastInstance) : CardConnection {
    private val queue = LinkedBlockingQueue<ByteArray>()
    private val markerByteArray = ByteArray(1) { 0 }

    fun init() {
        hazelcastInstance.getTopic<CardConnectionRelay>("/cc/relay/request/${uuid}").addMessageListener {
            when (it.messageObject.action) {
                "sign" -> hazelcastInstance.getTopic<ByteArray>("/cc/relay/response/${uuid}").publish(try { sendSign(it.messageObject.data, it.messageObject.digestType) } catch (e: TimeoutException) { markerByteArray })
                "readFile" -> hazelcastInstance.getTopic<ByteArray>("/cc/relay/response/${uuid}").publish(try { readFile(it.messageObject.data) } catch (e: TimeoutException) { markerByteArray })
            }
        }
    }

    fun destroy() {

    }

    fun pushResponse(byteArray: ByteArray) = queue.put(byteArray)

    override fun disconnect() {
        TODO("Not yet implemented")
    }

    override fun sign(
        digestValue: ByteArray,
        digestAlgo: BeIDDigest,
        fileType: FileType,
        requireSecureReader: Boolean
    ): ByteArray {
        val digestString = Base64.getEncoder().encodeToString(digestValue)
        return sendSign(digestString, digestAlgo.name)
    }

    private fun sendSign(
        digestString: String,
        digestAlgo: String?
    ): ByteArray {
        messenger.send(
            mapOf(
                "action" to "sign",
                "data" to digestString,
                "digestType" to (digestAlgo ?: "SHA_1")
            )
        )
        return queue.poll(2, TimeUnit.MINUTES)
            ?: throw TimeoutException("Timeout reached while waiting for eid signature")
    }

    override fun getChallenge(size: Int): ByteArray {
        TODO("Not yet implemented")
    }

    override fun logoff() {
        TODO("Not yet implemented")
    }

    override fun readFile(fileName: String): ByteArray {
        messenger.send(mapOf("action" to "readFile", "name" to fileName))
        return queue.poll(15, TimeUnit.SECONDS) ?: throw TimeoutException("Timeout reached while waiting for eid file $fileName")
    }
}
