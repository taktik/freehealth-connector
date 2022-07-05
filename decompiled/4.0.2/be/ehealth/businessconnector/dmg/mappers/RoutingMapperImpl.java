package be.ehealth.businessconnector.dmg.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.fgov.ehealth.globalmedicalfile.core.v1.CareReceiverIdType;
import be.fgov.ehealth.globalmedicalfile.core.v1.PeriodType;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;

public class RoutingMapperImpl implements RoutingMapper {
   public RoutingMapperImpl() {
   }

   public RoutingType map(Routing input) {
      if (input == null) {
         return null;
      } else {
         RoutingType routingType = new RoutingType();
         routingType.setCareReceiver(this.map(input.getCareReceiver()));
         routingType.setReferenceDate(input.getReferenceDate());
         routingType.setPeriod(this.map(input.getPeriod()));
         return routingType;
      }
   }

   public CareReceiverIdType map(CareReceiverId input) {
      if (input == null) {
         return null;
      } else {
         CareReceiverIdType careReceiverIdType = new CareReceiverIdType();
         careReceiverIdType.setSsin(input.getSsinNumber());
         careReceiverIdType.setRegNrWithMut(input.getRegistrationNumberWithMutuality());
         careReceiverIdType.setMutuality(input.getMutuality());
         return careReceiverIdType;
      }
   }

   public PeriodType map(Period input) {
      if (input == null) {
         return null;
      } else {
         PeriodType periodType = new PeriodType();
         periodType.setStart(input.getBegin());
         periodType.setEnd(input.getEnd());
         return periodType;
      }
   }
}
