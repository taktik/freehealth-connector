package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public class ConfigurationModuleWindows10 implements ConfigurationModule {
   public static final String SYSPROP_W10_PATCH_ACTIVE = "be.ehealth.technicalconnector.config.impl.configurationmodule.windows.10.patch.active";
   private String oldOsName;

   public ConfigurationModuleWindows10() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      if ("true".equals(System.getProperty("be.ehealth.technicalconnector.config.impl.configurationmodule.windows.10.patch.active", "true")) && ConfigUtils.isNet()) {
         this.oldOsName = System.getProperty("os.name");
         System.setProperty("os.name", "Windows 8");
      }

   }

   public void unload() throws TechnicalConnectorException {
      if (this.oldOsName != null) {
         System.setProperty("os.name", this.oldOsName);
      }

   }
}
