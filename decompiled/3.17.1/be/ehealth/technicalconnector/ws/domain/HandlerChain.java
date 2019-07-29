package be.ehealth.technicalconnector.ws.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.handler.soap.SOAPHandler;

public class HandlerChain {
   private Map<HandlerPosition, List<SOAPHandler<?>>> registeredHandlers = new HashMap();

   public HandlerChain() {
      this.registeredHandlers.put(HandlerPosition.BEFORE, new ArrayList());
      this.registeredHandlers.put(HandlerPosition.SECURITY, new ArrayList());
      this.registeredHandlers.put(HandlerPosition.AFTER, new ArrayList());
   }

   /** @deprecated */
   @Deprecated
   public HandlerChain registerHandler(HandlerPosition position, SOAPHandler<?> handler) {
      return this.register(position, handler);
   }

   public HandlerChain register(HandlerPosition position, SOAPHandler<?> handler) {
      List<SOAPHandler<?>> resultHandler = (List)this.registeredHandlers.get(position);
      resultHandler.add(handler);
      return this;
   }

   public HandlerChain unregisterHandler(HandlerPosition position, SOAPHandler<?> handler) {
      List<SOAPHandler<?>> resultHandler = (List)this.registeredHandlers.get(position);
      resultHandler.remove(handler);
      return this;
   }

   public List<SOAPHandler<?>> getHandlers(HandlerPosition position) {
      return (List)this.registeredHandlers.get(position);
   }
}
