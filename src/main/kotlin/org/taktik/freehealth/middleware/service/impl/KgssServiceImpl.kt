package org.taktik.freehealth.middleware.service.impl

import org.springframework.stereotype.Service
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory
import org.taktik.connector.technical.service.kgss.KgssService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.utils.IdentifierType
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent
import org.slf4j.LoggerFactory
import java.security.KeyStore

@Service
class KgssServiceImpl constructor(private val kgssKeyCache : org.springframework.cache.Cache) : org.taktik.freehealth.middleware.service.KgssService {
    private val service: KgssService
    private val log = LoggerFactory.getLogger(KgssServiceImpl::class.java)

    init {
        this.service = org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl()
    }

    override fun remove(key: String): KeyResult {
        log.debug("removing key from cache: $key")
        val result = this.kgssKeyCache.get(key)
        this.kgssKeyCache.evict(key)
        return result.get() as KeyResult
    }

    override fun containsKey(key: String): Boolean {
        return this.kgssKeyCache.get(key) != null
    }

    @Throws(TechnicalConnectorException::class)
    override fun getNewKey(keystoreId: String, keyStore: KeyStore, passPhrase: String, allowedReaders: List<CredentialType>, myEtk: ByteArray): KeyResult {
        return this.kgssKeyCache.get(keystoreId) { getNewKeyFromKgss(keyStore, passPhrase, allowedReaders, myEtk) }
    }

    @Throws(TechnicalConnectorException::class)
    fun getNewKeyFromKgss(keyStore: KeyStore, passPhrase: String, allowedReaders: List<CredentialType>, myEtk: ByteArray): KeyResult {
        val req = GetNewKeyRequestContent()
        req.etk = myEtk
        req.allowedReaders.addAll(allowedReaders)
        val config = ConfigFactory.getConfigValidator()
        val identifierType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.type", "CBE")
        val identifierSubType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.subtype")
        val idType = IdentifierType.lookup(identifierType, identifierSubType, 48)
        val id = config.getLongProperty("org.taktik.connector.technical.service.kgss.identifier.value", 809394427L)
        val appId = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.applicationid", "KGSS")

        return this.service.getNewKey(req, keyStore, passPhrase, KeyDepotManagerFactory.getKeyDepotManager().getEtk(idType, id, appId).encoded)
    }

    override fun flushCache() {
        this.kgssKeyCache.clear()
    }

}
