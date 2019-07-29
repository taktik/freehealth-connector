package be.ehealth.businessconnector.mycarenet.attest.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.mapper.MapperFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;

public final class RoutingMapper {
   public static RoutingType mapRoutingType(Routing input) {
      return (RoutingType)MapperFactory.getMapper("dozer/attest-routing.xml").map(input, RoutingType.class);
   }
}
