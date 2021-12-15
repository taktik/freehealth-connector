package org.taktik.freehealth.middleware.dispatcher.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.SAMLTokenFactory
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.service.sts.utils.SAMLHelper
import org.taktik.connector.technical.utils.CertificateParser
import org.taktik.freehealth.middleware.dispatcher.STSDispatcher
import org.taktik.freehealth.middleware.domain.sts.NonLocalKeystoreException
import org.taktik.freehealth.middleware.domain.sts.SamlTokenRequest
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import org.taktik.freehealth.middleware.dto.CertificateInfo
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.pkcs11.remote.RemoteKeystore
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.IOException
import java.io.StringReader
import java.security.KeyStore
import java.time.Instant
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutionException
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.stream.StreamSource

@Service
class STSDispatcherImpl(val keystoreCache: LoadingCache<Pair<UUID, String>, KeyStore>, val keystoresMap: IMap<UUID, ByteArray>, val tokensMap: IMap<UUID, SamlTokenResult>, val stsService: STSService, val remoteKeystoreService: RemoteKeystoreService, private val hazelcastInstance: HazelcastInstance) : STSDispatcher {
    private val log = LogFactory.getLog(this.javaClass)
    private val topicsCallbackMap = ConcurrentHashMap<UUID, BlockingQueue<SamlTokenResult>>()
    val transformerFactory: TransformerFactory = TransformerFactory.newInstance()

    init {
        hazelcastInstance.getTopic<SamlTokenResult>("interKeyStore/callback").addMessageListener { msg ->
            msg?.messageObject?.let { tr ->
                tr.sessionId?.let { tid -> topicsCallbackMap[tid]?.put(tr) }
            }
        }
    }

    override fun registerToken(tokenId: UUID, token: String, quality: String) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse(InputSource(StringReader(token)))
        val assertion = document.documentElement

        tokensMap[tokenId] = SamlTokenResult(
            null,
            tokenId,
            null,
            token,
            System.currentTimeMillis(),
            SAMLHelper.getNotOnOrAfterCondition(assertion).toInstant().millis,
            quality
        )
        log.info("tokensMap size: ${tokensMap.size}")
    }

    override fun getSAMLToken(tokenId: UUID, keystoreId: UUID, passPhrase: String): SAMLToken? {
        return tokensMap[tokenId]?.let {
            val keystore = getKeyStore(keystoreId, passPhrase)
            val result = DOMResult()
            transformerFactory.newTransformer().transform(StreamSource(StringReader(it.token!!)), result)
            return result.node?.firstChild?.let { el ->
                SAMLTokenFactory.getInstance()
                    .createSamlToken(el as Element, KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, it.quality))
            }
        }
    }

    override fun getKeystoreInfo(keystoreId: UUID, passPhrase: String): CertificateInfo {
        val keystore = getKeyStore(keystoreId, passPhrase)
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, "doctor") //Shouldn't assume but won't be used
        val parser = CertificateParser(credential.certificate)

        return CertificateInfo(credential.certificate.notAfter.time, parser.type, parser.id, parser.application, parser.owner)
    }

    override fun requestToken(
        keystoreId: UUID,
        nihiiOrSsin: String,
        passPhrase: String,
        quality: String,
        tokenId: UUID?,
        extraDesignators: List<Pair<String, String>>
    ): SamlTokenResult? {
        val now = System.currentTimeMillis()
        val currentToken = tokenId?.let { id -> tokensMap[id] }
        val isStillRecommendedForUse = currentToken?.let {
            val valid = it.validity
            val ts = it.timestamp

            if (valid == null || ts == null || quality != it.quality) {
                false
            } else {
                val totalValidity = valid - ts
                val remainingValidity = valid - now
                remainingValidity > 0 && totalValidity > 0 && remainingValidity.toDouble() / totalValidity.toDouble() > 0.5
            }
        } ?: false

        if (isStillRecommendedForUse) return currentToken

        return try {
            val keystore = getKeyStore(keystoreId, passPhrase)
            stsService.requestToken(keystoreId, nihiiOrSsin, passPhrase, quality, tokenId, extraDesignators, keystore).also { token ->
                tokensMap[token.tokenId] = token
            }
        } catch (e: NonLocalKeystoreException) {
            val queue = LinkedBlockingDeque<SamlTokenResult>()
            val tokenSessionId = UUID.randomUUID()
            topicsCallbackMap[tokenSessionId] = queue
            hazelcastInstance.getTopic<SamlTokenRequest>("interKeyStore/${keystoreId}")
                .publish(SamlTokenRequest(tokenSessionId, tokenId, keystoreId, nihiiOrSsin, passPhrase, quality, extraDesignators))
            queue.poll(2, TimeUnit.MINUTES)?.also {
                topicsCallbackMap.remove(tokenSessionId)
            }?.copy(sessionId = null) ?: throw TimeoutException("Cannot get token from keystore holder")
        } catch (e:TechnicalConnectorException) {
            log.info("STS token request failure: ${e.errorCode} : ${e.message} : ${e.stackTrace}")
            currentToken
        }
    }

    override fun checkTokenValid(tokenId: UUID): Boolean {
        return tokensMap[tokenId]?.let {
            (it.token?.length ?: 0) > 0 && (it.validity ?: 0) > Instant.now().toEpochMilli()
        } ?: false
    }

    override fun uploadKeystore(file: MultipartFile): UUID {
        val keystoreId = UUID.nameUUIDFromBytes(file.bytes)
        keystoresMap[keystoreId] = file.bytes
        log.info("keystoresMap size: ${keystoresMap.size}")

        return keystoreId
    }

    override fun uploadKeystore(data: ByteArray): UUID {
        val keystoreId = UUID.nameUUIDFromBytes(data)
        keystoresMap[keystoreId] = data
        log.info("keystoresMap size: ${keystoresMap.size}")

        return keystoreId
    }

    override fun getKeyStore(keystoreId: UUID, passPhrase: String): KeyStore? {
        return try {
            if (remoteKeystoreService.hasConnection(keystoreId)) {
                if (remoteKeystoreService.hasLocalConnection(keystoreId)) {
                    RemoteKeystore(keystoreId, remoteKeystoreService).apply { load { null } }
                } else {
                    throw NonLocalKeystoreException()
                }
            } else keystoreCache.get(Pair(keystoreId, passPhrase))
        } catch(ex: ExecutionException) {
            (ex.cause as? IOException)?.let { throw IllegalArgumentException(it.message ?: "Decryption exception") } ?: throw (ex.cause ?: ex)
        }
    }

    override fun checkIfKeystoreExist(keystoreId: UUID): Boolean {
        return keystoresMap.get(keystoreId) != null
    }

}
