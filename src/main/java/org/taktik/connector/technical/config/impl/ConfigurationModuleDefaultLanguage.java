package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleDefaultLanguage implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleDefaultLanguage.class);
   private Locale oldLocale;

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      String lang = config.getProperty("locale.default.lang", Locale.getDefault().getLanguage());
      LOG.info("Changing default language to: " + lang);
      this.oldLocale = Locale.getDefault();
      Locale.setDefault(new Locale(lang));
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      Locale.setDefault(this.oldLocale);
   }
}
