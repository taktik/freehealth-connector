package be.business.connector.core.utils;

import be.business.connector.core.handlers.LoggingHandler;
import be.business.connector.core.handlers.SoapFaultHandler;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggingUtil {
   private static final Logger LOG = Logger.getLogger(LoggingUtil.class);

   public static void addInOutLoggerHandler(Object port, boolean soapfaultHandler) {
      if (port instanceof BindingProvider) {
         BindingProvider bindingProvider = (BindingProvider)port;
         List<Handler> handlerChain = new ArrayList();
         handlerChain.addAll(bindingProvider.getBinding().getHandlerChain());
         handlerChain.add(new LoggingHandler());
         if (soapfaultHandler) {
            handlerChain.add(new SoapFaultHandler());
         }

         bindingProvider.getBinding().setHandlerChain(handlerChain);
      } else {
         LOG.warn("BindingProvider provider expected, get a " + port);
      }

   }

   public static void initLog4J(PropertyHandler propertyHandler) {
      LOG.info("****************  Init LOG4J");
      if (propertyHandler != null) {
         Path path = Paths.get(propertyHandler.getProperty("LOG4J", "log4j.xml"));
         if (Files.exists(path, new LinkOption[0])) {
            LogManager.resetConfiguration();
            DOMConfigurator.configure(path.toAbsolutePath().toString());
            LOG.info("Loading log4j config from " + path.toAbsolutePath().toString());
         }
      }

   }
}
