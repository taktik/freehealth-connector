package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.SilentInstantiationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleSecurityProvider implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSecurityProvider.class);
   private List<Provider> providersAdded = new ArrayList();
   public static final String PROP_CLEANUP_ACTION_KEY = "org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.cleanup.action";
   public static final String PROP_CLEANUP_ACTION_PARTIAL_ROOTKEY = "org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.cleanup.action.partial";
   public static final String PROP_ADD_SECURITYPROVIDERS_ROOTKEY = "org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders";
   public static final String PROP_ADD_SECURITYPROVIDERS_ACTIVATED = "org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.activated";
   public static final String PROP_ADD_SECURITYPROVIDERS_POSITION_KEY = "org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.insertProviderAt";

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      String executed = System.getProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider", "false");
      if ("false".equalsIgnoreCase(executed)) {
         this.removeSecurityProviders(config.getProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.cleanup.action", "none"), config);
         this.addSecurityProviders(config);
         this.printCurrentSecurityProviders();
         System.setProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider", "true");
      }

   }

   private void printCurrentSecurityProviders() {
      if (LOG.isDebugEnabled()) {
         Provider[] providers = Security.getProviders();
         LOG.debug("Overview of security providers:");
         Provider[] arr$ = providers;
         int len$ = providers.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Provider prov = arr$[i$];
            LOG.debug("\t." + prov.getName() + "[" + prov.getClass().getName() + "]");
         }
      }

   }

   private void addSecurityProviders(Configuration config) {
      String action = config.getProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.activated");
      if (StringUtils.isEmpty(action)) {
         Security.addProvider(new BouncyCastleProvider());

         try {
            Security.addProvider(this.instantiate("be.fedict.commons.eid.jca.BeIDProvider"));
         } catch (SilentInstantiationException var9) {
            LOG.warn("Unable to load:" + var9.getCause().getMessage());
         }
      } else if ("true".equalsIgnoreCase(action)) {
         try {
            ConfigurableFactoryHelper<Provider> helper = new ConfigurableFactoryHelper("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders", (String)null);
            List<Provider> providerList = helper.getImplementations();
            String position = config.getProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.add.securityproviders.insertProviderAt", "end");
            Iterator i$ = providerList.iterator();

            while(i$.hasNext()) {
               Provider provider = (Provider)i$.next();
               this.removeSecurityProvider(provider.getName());
               if ("end".equals(position)) {
                  LOG.debug("Inserting provider " + provider.getName());
                  Security.addProvider(provider);
               } else if ("begin".equals(position)) {
                  LOG.debug("Inserting provider " + provider.getName() + " at position 1.");
                  Security.insertProviderAt(provider, 1);
               } else if (StringUtils.isNumeric(position)) {
                  Integer positionId = Integer.parseInt(position);
                  LOG.debug("Inserting provider " + provider.getName() + " at position " + positionId + ".");
                  Security.insertProviderAt(provider, positionId);
               } else {
                  LOG.warn("Unsupported position value [" + position + "]");
               }
            }
         } catch (TechnicalConnectorException var10) {
            LOG.error(var10.getClass().getSimpleName() + ": " + var10.getMessage(), var10);
         }
      }

   }

   private void removeSecurityProviders(String action, Configuration config) {
      if ("full".equals(action)) {
         Provider[] providers = Security.getProviders();
         Provider[] arr$ = providers;
         int len$ = providers.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Provider prov = arr$[i$];
            this.removeSecurityProvider(prov.getName());
         }
      } else if ("partial".equals(action)) {
         List<String> providersToRemove = config.getMatchingProperties("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider.cleanup.action.partial");
         Iterator i$ = providersToRemove.iterator();

         while(i$.hasNext()) {
            String providerName = (String)i$.next();
            this.removeSecurityProvider(providerName);
         }
      }

   }

   private void removeSecurityProvider(String providerName) {
      LOG.debug("Removing SecurityProvider with Name [" + providerName + "]");
      Security.removeProvider(providerName);
   }

   public void unload() throws TechnicalConnectorException {
      Iterator i$ = this.providersAdded.iterator();

      while(i$.hasNext()) {
         Provider provider = (Provider)i$.next();
         LOG.debug("Removing provider " + provider.getName());
         Security.removeProvider(provider.getName());
      }

      System.setProperty("org.taktik.connector.technical.config.impl.ConfigurationModuleSecurityProvider", "false");
   }

   private Provider instantiate(String className) throws SilentInstantiationException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());

      try {
         Class clazz = Class.forName(className);
         return (Provider)clazz.newInstance();
      } catch (ClassNotFoundException var3) {
         throw new SilentInstantiationException(var3);
      } catch (InstantiationException var4) {
         throw new SilentInstantiationException(var4);
      } catch (IllegalAccessException var5) {
         throw new SilentInstantiationException(var5);
      }
   }
}
