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

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import com.hazelcast.map.listener.EntryEvictedListener
import org.apache.commons.lang3.tuple.Pair
import org.apache.commons.lang3.tuple.Triple
import org.apache.commons.logging.LogFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.kgss.domain.KeyResult
import org.taktik.connector.technical.service.kgss.domain.SerializableKeyResult
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import java.util.UUID

@Component
@ConfigurationProperties("icure.hazelcast")
class HazelcastProperties {
    var groupName : String? = null
    var groupPassword : String? = null
}

@Configuration
class HazelcastConfiguration(val hazelcastProperties: HazelcastProperties) {
    private val log = LogFactory.getLog(this::class.java)

    @Bean fun config() = Config().apply {
        hazelcastProperties.groupName?.let { groupConfig.name = it }
        hazelcastProperties.groupPassword?.let { groupConfig.name = it }
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES").apply {
            timeToLiveSeconds = 18*3600
            asyncBackupCount = 1
            isReadBackupData = true
            maxSizeConfig = MaxSizeConfig(128, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
            evictionPolicy = EvictionPolicy.LRU
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS").apply {
            timeToLiveSeconds = 12*3600
            asyncBackupCount = 1
            isReadBackupData = true
            maxSizeConfig = MaxSizeConfig(128, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
            evictionPolicy = EvictionPolicy.LRU
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK").apply {
            timeToLiveSeconds = 24*3600
            asyncBackupCount = 1
            isReadBackupData = true
            maxSizeConfig = MaxSizeConfig(128, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
            evictionPolicy = EvictionPolicy.LRU
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.LONGLIVEDETK").apply {
            timeToLiveSeconds = 3*365*24*3600
            asyncBackupCount = 1
            isReadBackupData = true
            maxSizeConfig = MaxSizeConfig(512, MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE)
            evictionPolicy = EvictionPolicy.LRU
        })
        addMapConfig(MapConfig("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS").apply {
            timeToLiveSeconds = 12*3600
            asyncBackupCount = 1
            isReadBackupData = true
            maxSizeConfig = MaxSizeConfig(128, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
            evictionPolicy = EvictionPolicy.LRU
        })
    }

    @Bean
    fun keystoresMap(hazelcastInstance: HazelcastInstance): IMap<UUID, ByteArray> {
        val map = hazelcastInstance.getMap<UUID, ByteArray>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES").apply {
            this.addEntryListener(EntryEvictedListener<UUID, SamlTokenResult> {
                log.info("Keystore ${it.key} evicted")
            }, false)
        }
        return map
    }

    @Bean
    fun tokensMap(hazelcastInstance: HazelcastInstance): IMap<UUID, SamlTokenResult> {
        return hazelcastInstance.getMap<UUID, SamlTokenResult>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS").apply {
            this.addEntryListener(EntryEvictedListener<UUID, SamlTokenResult> {
                log.info("Token ${it.key} evicted")
            }, false)
        }
    }

    @Bean
    fun etksMap(hazelcastInstance: HazelcastInstance): IMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>> {
        return hazelcastInstance.getMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK").apply {
            this.addEntryListener(EntryEvictedListener<Triple<IdentifierType, String, String>, Set<EncryptionToken>> {
                log.info("ETK ${it.key} evicted")
            }, false)
        }
    }

    @Bean
    fun longLivedEtksMap(hazelcastInstance: HazelcastInstance): IMap<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>> {
        return hazelcastInstance.getMap<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.LONGLIVEDETK").apply {
            this.addEntryListener(EntryEvictedListener<org.apache.commons.lang3.tuple.Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>> {
                log.info("ETK ${it.key} evicted")
            }, false)
        }
    }

    @Bean
    fun kgssMap(hazelcastInstance: HazelcastInstance): IMap<UUID, SerializableKeyResult> {
        return hazelcastInstance.getMap<UUID, SerializableKeyResult>("ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS").apply {
            this.addEntryListener(EntryEvictedListener<UUID, SerializableKeyResult> {
                log.info("KGSS ${it.key} evicted")
            }, false)
        }
    }
}
