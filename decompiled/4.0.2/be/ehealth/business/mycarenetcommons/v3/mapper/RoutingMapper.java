package be.ehealth.business.mycarenetcommons.v3.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.mycarenet.commons.core.v3.CareReceiverIdType;
import be.fgov.ehealth.mycarenet.commons.core.v3.PeriodType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RoutingMapper {
   RoutingType map(Routing var1);

   @Mappings({@Mapping(
   source = "ssinNumber",
   target = "ssin"
), @Mapping(
   source = "registrationNumberWithMutuality",
   target = "regNrWithMut"
)})
   CareReceiverIdType map(CareReceiverId var1);

   @Mapping(
      source = "begin",
      target = "start"
   )
   PeriodType map(Period var1);
}
