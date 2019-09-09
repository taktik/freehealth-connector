package be.ehealth.businessconnector.dmg.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;

public final class RoutingMapper {
   public static RoutingType mapRoutingType(Routing input) {
      List<String> myMappingFiles = new ArrayList();
      myMappingFiles.add("dozer/dmg-routing.xml");
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.setMappingFiles(myMappingFiles);
      return (RoutingType)mapper.map(input, RoutingType.class);
   }
}
