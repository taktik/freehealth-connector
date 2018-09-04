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
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import java.util.*

@Component
@ConfigurationProperties("icure.hazelcast")
class HazelcastProperties {
    var groupName : String? = null
    var groupPassword : String? = null
}

@Configuration
class HazelcastConfiguration(val hazelcastProperties: HazelcastProperties) {
    @Bean fun config() = Config().apply {
        hazelcastProperties.groupName?.let { groupConfig.name = it }
        hazelcastProperties.groupPassword?.let { groupConfig.name = it }
    }

    @Bean
    fun keystoresMap(hazelcastInstance: HazelcastInstance): IMap<UUID, ByteArray> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES"
        config.addMapConfig(MapConfig(mapName).apply {
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, ByteArray>(mapName)
    }

    @Bean
    fun tokensMap(hazelcastInstance: HazelcastInstance): IMap<UUID, SamlTokenResult> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, SamlTokenResult>(mapName)
    }

    @Bean
    fun etkCache(hazelcastInstance: HazelcastInstance): IMap<UUID, String> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, String>(mapName)
    }

    @Bean
    fun kgssCache(hazelcastInstance: HazelcastInstance): IMap<UUID, String> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, String>(mapName)
    }
}
