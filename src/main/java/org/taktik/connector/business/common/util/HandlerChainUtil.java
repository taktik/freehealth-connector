package org.taktik.connector.business.common.util;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.handler.SchemaValidatorHandler;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;

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
