package be.ehealth.technicalconnector.ws.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.soap.SOAPHandler;
import org.apache.commons.lang3.ArrayUtils;

public class HandlerChain {
   private Map<HandlerPosition, List<SOAPHandler<?>>> registeredHandlers = new EnumMap(HandlerPosition.class);

   public HandlerChain() {
      this.registeredHandlers.put(HandlerPosition.BEFORE, new ArrayList());
      this.registeredHandlers.put(HandlerPosition.SECURITY, new ArrayList());
      this.registeredHandlers.put(HandlerPosition.AFTER, new ArrayList());
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

   public HandlerChain add(HandlerChain handlers) {
      this.add(handlers, HandlerPosition.BEFORE);
      this.add(handlers, HandlerPosition.SECURITY);
      this.add(handlers, HandlerPosition.AFTER);
      return this;
   }

   private void add(HandlerChain handlers, HandlerPosition position) {
      ((List)this.registeredHandlers.get(position)).addAll(handlers.getSOAPHandlers(position));
   }

   private List<SOAPHandler<?>> getSOAPHandlers(HandlerPosition position) {
      return (List)this.registeredHandlers.get(position);
   }

   public List<Handler<?>> getHandlers(HandlerPosition position) {
      List<Handler<?>> handlers = new ArrayList();
      Iterator var3 = ((List)this.registeredHandlers.get(position)).iterator();

      while(var3.hasNext()) {
         SOAPHandler<?> handler = (SOAPHandler)var3.next();
         handlers.add(handler);
      }

      return handlers;
   }

   public Handler<?>[] getHandlers() {
      Handler<?>[] handlers = new Handler[0];
      handlers = (Handler[])((Handler[])ArrayUtils.addAll(handlers, this.getHandlers(HandlerPosition.BEFORE).toArray()));
      handlers = (Handler[])((Handler[])ArrayUtils.addAll(handlers, this.getHandlers(HandlerPosition.SECURITY).toArray()));
      handlers = (Handler[])((Handler[])ArrayUtils.addAll(handlers, this.getHandlers(HandlerPosition.AFTER).toArray()));
      return handlers;
   }
}
