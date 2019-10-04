package org.taktik.connector.business.mycarenetcommons.mapper.v3

import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType

object RoutingMapper {
    fun mapRoutingType(input: Routing): RoutingType {
        return RoutingType()
    }
}
