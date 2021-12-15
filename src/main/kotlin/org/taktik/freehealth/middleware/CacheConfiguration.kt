
/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import com.hazelcast.core.IMap
import org.apache.commons.lang3.tuple.Pair
import org.apache.commons.lang3.tuple.Triple
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import java.security.KeyStore
import java.util.UUID
import java.util.concurrent.TimeUnit

@Configuration
class CacheConfiguration {
    @Bean fun cacheManager() = ConcurrentMapCacheManager().apply {  }
    @Bean fun keyDepotService(etksMap: IMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>>, longLivedEtksMap: IMap<Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>>) = KeyDepotServiceImpl(etksMap, longLivedEtksMap)
    @Bean fun keystoreCache(keystoresMap: IMap<UUID, ByteArray>): LoadingCache<kotlin.Pair<UUID, String>, KeyStore> = CacheBuilder.newBuilder().maximumSize(2000).expireAfterAccess(1, TimeUnit.HOURS).build(object : CacheLoader<kotlin.Pair<UUID, String>, KeyStore>() {
        override fun load(key: kotlin.Pair<UUID, String>): KeyStore {
            val keyStoreData = keystoresMap[key.first]
                    ?: throw(MissingKeystoreException("Missing Keystore, please upload a keystore and use the returned keystoreId"))
            return KeyManager.getKeyStore(keyStoreData.inputStream(), "PKCS12", key.second.toCharArray())
        }
    })

}
