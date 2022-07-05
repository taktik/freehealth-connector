package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.handler.CacheFeederHandler;
import be.ehealth.technicalconnector.handler.ConnectionTimeOutHandler;
import be.ehealth.technicalconnector.handler.FromHandler;
import be.ehealth.technicalconnector.handler.LoggingHandler;
import be.ehealth.technicalconnector.handler.MessageLevelRetryHandler;
import be.ehealth.technicalconnector.handler.UserAgentHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.ws.handler.Handler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class HandlersLoader {
   private static final Logger LOG = LoggerFactory.getLogger(HandlersLoader.class);
   private static List<Class> defaultHandlers = Arrays.asList(MessageLevelRetryHandler.class, ConnectionTimeOutHandler.class, LoggingHandler.class, UserAgentHandler.class, FromHandler.class, CacheFeederHandler.class);

   private HandlersLoader() {
   }

   static Handler[] addingDefaultHandlers(Handler[] result) {
      List<Class> requiredHandler = new ArrayList(defaultHandlers.size());
      requiredHandler.addAll(defaultHandlers);
      CollectionUtils.filter(requiredHandler, new DefaultHandlersPredicate(result));
      Iterator var2 = requiredHandler.iterator();

      while(var2.hasNext()) {
         Class handler = (Class)var2.next();

         try {
            LOG.debug("Adding required handler [{}]", handler.getName());
            result = (Handler[])((Handler[])ArrayUtils.add(result, handler.newInstance()));
         } catch (Exception var5) {
            LOG.warn("Unable to add required handler", var5);
         }
      }

      return result;
   }

   private static class DefaultHandlersPredicate implements Predicate {
      private Class<?>[] handlers;

      public DefaultHandlersPredicate(Handler<?>[] handlers) {
         Set<Class> handlerSet = new HashSet();
         Handler[] var3 = handlers;
         int var4 = handlers.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Handler handler = var3[var5];
            handlerSet.add(handler.getClass());
         }

         this.handlers = (Class[])handlerSet.toArray(new Class[handlerSet.size()]);
      }

      public boolean evaluate(Object object) {
         return !ArrayUtils.contains(this.handlers, object);
      }
   }
}
