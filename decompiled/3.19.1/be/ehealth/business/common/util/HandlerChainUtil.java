package be.ehealth.business.common.util;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.handler.SchemaValidatorHandler;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;

public class HandlerChainUtil {
   private static final String TRUE = "true";

   public static HandlerChain buildChainWithValidator(String incomingValidationProperty, String... xsdLocation) {
      HandlerChain chain = new HandlerChain();
      if ("true".equalsIgnoreCase(ConfigFactory.getConfigValidator().getProperty(incomingValidationProperty, "true"))) {
         chain.register(HandlerPosition.BEFORE, new SchemaValidatorHandler(3, xsdLocation));
      } else {
         chain.register(HandlerPosition.BEFORE, new SchemaValidatorHandler(2, xsdLocation));
      }

      return chain;
   }
}
