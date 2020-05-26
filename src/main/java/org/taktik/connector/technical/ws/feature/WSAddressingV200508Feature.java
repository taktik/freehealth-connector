package org.taktik.connector.technical.ws.feature;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.WsAddressingHandlerV200508;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.ws.domain.HandlerChain;
import org.taktik.connector.technical.ws.domain.HandlerPosition;
import java.util.HashMap;
import java.util.Map;

public class WSAddressingV200508Feature extends GenericFeature {
   private WsAddressingHeader header;

   public WSAddressingV200508Feature(WsAddressingHeader header) throws TechnicalConnectorException {
      super(true);
      validate(header);
      this.header = header;
   }

   private static void validate(WsAddressingHeader header) throws TechnicalConnectorException {
      if (header == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"WsAddressing object is null."});
      }
   }

   public String getID() {
      return "org.taktik.connector.technical.ws.feature.wsaddressing.v200508";
   }

   public HandlerChain getHandlers() {
      HandlerChain handlerChain = new HandlerChain();
      if (this.header != null) {
         handlerChain.register(HandlerPosition.BEFORE, new WsAddressingHandlerV200508());
      }

      return handlerChain;
   }

   public Map<String, Object> requestParamOptions() {
      Map<String, Object> result = new HashMap();
      if (this.header != null) {
         result.put("org.taktik.connector.technical.handler.WsAddressingHandlerV200508.use", Boolean.TRUE);
         result.put("org.taktik.connector.technical.handler.WsAddressingHandlerV200508", this.header);
      }

      return result;
   }
}
