package be.ehealth.business.mycarenetdomaincommons.builders.util;

import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.business.mycarenetdomaincommons.exception.ConnectorValidationException;
import be.ehealth.business.mycarenetdomaincommons.validator.RoutingValidator;
import org.joda.time.DateTime;

public final class GeneralRoutingBuilder {
   private Routing routing = new Routing();
   private boolean validate = true;

   private GeneralRoutingBuilder() {
   }

   public GeneralRoutingBuilder disableValidation() {
      this.validate = false;
      return this;
   }

   public GeneralRoutingBuilder addPeriod(DateTime begin, DateTime end) {
      this.routing.setPeriod(new Period(begin, end));
      return this;
   }

   public GeneralRoutingBuilder addReferenceDate(DateTime referenceDate) {
      this.routing.setReferenceDate(referenceDate);
      return this;
   }

   public GeneralRoutingBuilder addCareReceiverForSsin(String ssin) {
      this.routing.setCareReceiver(new CareReceiverId(ssin));
      return this;
   }

   public GeneralRoutingBuilder addCareReceiverForMutualityIdAndMutuality(String mutualityId, String mutuality) {
      this.routing.setCareReceiver(new CareReceiverId(mutuality, mutuality));
      return this;
   }

   public GeneralRoutingBuilder addCareReceiver(CareReceiverId careReceiver) {
      this.routing.setCareReceiver(careReceiver);
      return this;
   }

   public static GeneralRoutingBuilder create() {
      return new GeneralRoutingBuilder();
   }

   public static GeneralRoutingBuilder createFullForNissAndCurrentReferenceDate(String ssin) {
      GeneralRoutingBuilder routingBuilder = new GeneralRoutingBuilder();
      routingBuilder.addCareReceiverForSsin(ssin);
      routingBuilder.addReferenceDate(new DateTime());
      return routingBuilder;
   }

   public Routing build() throws ConnectorValidationException {
      if (this.validate) {
         RoutingValidator.validate(this.routing);
      }

      return this.routing;
   }
}
