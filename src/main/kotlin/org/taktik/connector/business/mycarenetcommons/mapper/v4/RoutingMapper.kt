package org.taktik.connector.business.mycarenetcommons.mapper.v4

import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType

object RoutingMapper {
    fun mapRoutingType(input: Routing): RoutingType {
        return RoutingType()
    }
}
