package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.handler.CacheFeederHandler;
import be.ehealth.technicalconnector.handler.ConnectionTimeOutHandler;
import be.ehealth.technicalconnector.handler.LoggingHandler;
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
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class HandlersLoader {
   private static final Logger LOG = LoggerFactory.getLogger(HandlersLoader.class);
   private static List<Class> defaultHandlers = Arrays.asList(ConnectionTimeOutHandler.class, LoggingHandler.class, UserAgentHandler.class, CacheFeederHandler.class);

   static Handler<?>[] addingDefaultHandlers(Handler<?>[] result) {
      ArrayList<Class> requiredHandler = new ArrayList(defaultHandlers.size());
      requiredHandler.addAll(defaultHandlers);
      CollectionUtils.filter(requiredHandler, new HandlersLoader.DefaultHandlersPredicate(result));
      Iterator i$ = requiredHandler.iterator();

      while(i$.hasNext()) {
         Class handler = (Class)i$.next();

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
         Handler[] arr$ = handlers;
         int len$ = handlers.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Handler handler = arr$[i$];
            handlerSet.add(handler.getClass());
         }

         this.handlers = (Class[])handlerSet.toArray(new Class[handlerSet.size()]);
      }

      public boolean evaluate(Object object) {
         return !ArrayUtils.contains(this.handlers, object);
      }
   }
}
