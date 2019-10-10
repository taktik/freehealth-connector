package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent
import com.hazelcast.core.IMap
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.kgss.KgssService
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.domain.SerializableKeyResult
import org.taktik.connector.technical.utils.IdentifierType
import java.security.KeyStore
import java.util.UUID

@Service
class KgssServiceImpl constructor(private val kgssMap : IMap<UUID, SerializableKeyResult>, private val keyDepotService: KeyDepotService) : org.taktik.freehealth.middleware.service.KgssService {
    private val service: KgssService
    private val log = LoggerFactory.getLogger(KgssServiceImpl::class.java)

    init {
        this.service = org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl()
    }

    override fun remove(key: UUID): KeyResult? {
        log.debug("removing key from cache: $key")
        val result = this.kgssMap.get(key)
        this.kgssMap.evict(key)
        return result?.toKeyResult()
    }

    override fun containsKey(key: UUID): Boolean {
        return this.kgssMap.get(key) != null
    }

    @Throws(TechnicalConnectorException::class)
    override fun getNewKey(keystoreId: UUID, keyStore: KeyStore, passPhrase: String, allowedReaders: List<CredentialType>, myEtk: ByteArray): KeyResult {
        return (this.kgssMap.computeIfAbsent(keystoreId) { getNewKeyFromKgss(keystoreId, keyStore, passPhrase, allowedReaders, myEtk).toSerializableKeyResult() }).toKeyResult()
    }

    @Throws(TechnicalConnectorException::class)
    fun getNewKeyFromKgss(keystoreId: UUID, keyStore: KeyStore, passPhrase: String, allowedReaders: List<CredentialType>, myEtk: ByteArray): KeyResult {
        val req = GetNewKeyRequestContent()
        req.etk = myEtk
        req.allowedReaders.addAll(allowedReaders)
        val config = ConfigFactory.getConfigValidator()
        val identifierType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.type", "CBE")
        val identifierSubType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.subtype")
        val idType = IdentifierType.lookup(identifierType, identifierSubType, 48)
        val id = config.getLongProperty("org.taktik.connector.technical.service.kgss.identifier.value", 809394427L)
        val appId = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.applicationid", "KGSS")

        return this.service.getNewKey(req, keystoreId, keyStore, passPhrase,  KeyDepotManagerImpl.getInstance(keyDepotService).getEtk(idType, id, appId, keystoreId, false).encoded)
    }

    override fun flushCache() {
        this.kgssMap.clear()
    }

}
