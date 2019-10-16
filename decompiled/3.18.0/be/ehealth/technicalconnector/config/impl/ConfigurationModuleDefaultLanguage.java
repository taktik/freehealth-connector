package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleDefaultLanguage implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleDefaultLanguage.class);
   private Locale oldLocale;

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      String lang = config.getProperty("locale.default.lang", Locale.getDefault().getLanguage());
      LOG.info("Changing default language to: {}", lang);
      this.oldLocale = Locale.getDefault();
      Locale.setDefault(new Locale(lang));
   }

   public void unload() {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      Locale.setDefault(this.oldLocale);
   }
}
