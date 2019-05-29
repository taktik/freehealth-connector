package be.ehealth.business.mycarenetcommons.mapper.v3;

import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.mapper.MapperFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;

public final class RoutingMapper {
   private RoutingMapper() {
   }

   public static RoutingType mapRoutingType(Routing input) {
      return (RoutingType)MapperFactory.getMapper("dozer/routing.xml").map(input, RoutingType.class);
   }
}
