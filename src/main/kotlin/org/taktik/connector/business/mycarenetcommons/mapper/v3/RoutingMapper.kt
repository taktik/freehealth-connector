package org.taktik.connector.business.mycarenetcommons.mapper.v3;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;

public final class RoutingMapper {
   private RoutingMapper() {
   }

   public static RoutingType mapRoutingType(Routing input) {
      return new RoutingType();
   }
}
