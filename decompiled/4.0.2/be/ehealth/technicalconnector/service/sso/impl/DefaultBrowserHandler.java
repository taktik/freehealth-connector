package be.ehealth.technicalconnector.service.sso.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sso.BrowserHandler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultBrowserHandler implements BrowserHandler {
   private static final Logger LOG = LoggerFactory.getLogger(DefaultBrowserHandler.class);
   private static final String PROP_BROWSER = "browser";

   public DefaultBrowserHandler() {
   }

   public void browse(URI uri) throws TechnicalConnectorException {
      try {
         ConfigValidator config = ConfigFactory.getConfigValidator();
         if (config.hasProperty("browser")) {
            Runtime.getRuntime().exec(config.getProperty("browser") + " " + uri);
         } else {
            if (LOG.isInfoEnabled()) {
               LOG.info("Using system default for opening {}", uri.toASCIIString());
            }

            Desktop.getDesktop().browse(uri);
         }

      } catch (IOException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var3, new Object[]{var3.getMessage()});
      }
   }
}
