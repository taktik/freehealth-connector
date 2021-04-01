package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.HashMap;
import java.util.Map;
import org.apache.xml.security.Init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleXmlSec implements ConfigurationModule {
   private static final String JSR105PROVIDER_KEY = "jsr105Provider";
   private static final String JSR105PROVIDER_CLASSNAME = "org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleXmlSec.class);
   private Map<String, String> oldValues = new HashMap();

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      if (!Init.isInitialized()) {
         LOG.info("Initializing xmlsec");
         Init.init();
         LOG.info("Setting jsr105Provider with value [org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI]");
         this.oldValues.put("jsr105Provider", System.getProperty("jsr105Provider"));
         System.setProperty("jsr105Provider", "org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI");
      }

   }

   public void unload() {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      ConfigurationModuleLoader.unloadSystemProperties(this.oldValues);
   }
}
