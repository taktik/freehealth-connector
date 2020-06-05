package be.ehealth.technicalconnector.ws.feature;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.handler.WsAddressingHandlerV200508;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.ws.domain.HandlerChain;
import be.ehealth.technicalconnector.ws.domain.HandlerPosition;
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
      return "be.ehealth.technicalconnector.ws.feature.wsaddressing.v200508";
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
         result.put("be.ehealth.technicalconnector.handler.WsAddressingHandlerV200508.use", Boolean.TRUE);
         result.put("be.ehealth.technicalconnector.handler.WsAddressingHandlerV200508", this.header);
      }

      return result;
   }
}
