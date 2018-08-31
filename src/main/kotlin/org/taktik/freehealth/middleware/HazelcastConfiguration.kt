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

import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.freehealth.middleware.domain.sts.SamlTokenResult
import java.util.*

@Configuration
class HazelcastConfiguration(val hazelcastInstance: HazelcastInstance) {
    @Bean
    fun keystoresMap(): IMap<UUID, ByteArray> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES"
        config.addMapConfig(MapConfig(mapName).apply {
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, ByteArray>(mapName)
    }

    @Bean
    fun tokensMap(): IMap<UUID, SamlTokenResult> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, SamlTokenResult>(mapName)
    }

    @Bean
    fun etkCache(): IMap<UUID, String> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.ETK"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, String>(mapName)
    }

    @Bean
    fun kgssCache(): IMap<UUID, String> {
        val config = hazelcastInstance.config
        val mapName = "ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KGSS"
        config.addMapConfig(MapConfig(mapName). apply{
            timeToLiveSeconds = 12*3600
            maxSizeConfig = MaxSizeConfig(100000, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        })
        return hazelcastInstance.getMap<UUID, String>(mapName)
    }
}
