package org.taktik.connector.business.mycarenet.attest.mappers;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import org.taktik.connector.technical.mapper.MapperFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;

public final class RoutingMapper {
   public static RoutingType mapRoutingType(Routing input) {
      return (RoutingType)MapperFactory.getMapper("dozer/attest-routing.xml").map(input, RoutingType.class);
   }
}
