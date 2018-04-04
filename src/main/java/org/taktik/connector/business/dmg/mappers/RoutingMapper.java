package org.taktik.connector.business.dmg.mappers;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;

public final class RoutingMapper {
   public static RoutingType mapRoutingType(Routing input) {
//      List<String> myMappingFiles = new ArrayList();
//      myMappingFiles.add("dozer/dmg-routing.xml");
//      DozerBeanMapper mapper = new DozerBeanMapper();
//      mapper.setMappingFiles(myMappingFiles);
//      return (RoutingType)mapper.map(input, RoutingType.class);
      //TODO replace dozer
      return new RoutingType();
   }
}
