package org.taktik.freehealth.middleware.pkcs11.remote

import be.fedict.commons.eid.client.FileType
import be.fedict.commons.eid.client.impl.BeIDDigest
import org.taktik.freehealth.middleware.service.impl.RemoteKeystoreServiceImpl
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class CardConnection(val messenger: RemoteKeystoreServiceImpl.Messenger) {
    private val queue = LinkedBlockingQueue<ByteArray>()

    fun pushResponse(byteArray: ByteArray) = queue.put(byteArray)

    fun disconnect() {
        TODO("Not yet implemented")
    }

    fun sign(
        digestValue: ByteArray,
        digestAlgo: BeIDDigest,
        fileType: FileType,
        requireSecureReader: Boolean
    ): ByteArray {
        messenger.send(mapOf("action" to "sign", "data" to Base64.getEncoder().encodeToString(digestValue), "digestType" to digestAlgo.name))
        val result = queue.take()
        return result
    }

    fun getChallenge(size: Int): ByteArray {
        TODO("Not yet implemented")
    }

    fun logoff() {
        TODO("Not yet implemented")
    }

    fun readFile(fileName: String): ByteArray {
        messenger.send(mapOf("action" to "readFile", "name" to fileName))
        val result = queue.take()
        return result
    }
}
