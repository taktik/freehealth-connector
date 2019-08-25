
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

import com.hazelcast.core.IMap
import org.apache.commons.lang3.tuple.Pair
import org.apache.commons.lang3.tuple.Triple
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl
import org.taktik.connector.technical.utils.IdentifierType
import java.util.UUID

@Configuration
class CacheConfiguration {
    @Bean fun cacheManager() = ConcurrentMapCacheManager().apply {  }
    @Bean fun keyDepotService(etksMap: IMap<Triple<IdentifierType, String, String>, Set<EncryptionToken>>, longLivedEtksMap: IMap<Pair<UUID, Triple<IdentifierType, String, String>>, Set<EncryptionToken>>) = KeyDepotServiceImpl(etksMap, longLivedEtksMap)
}
