package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.beid.impl.CommonsEidInstantiator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import java.security.KeyStore;

public class BeIDFactory {
   private static final String PROP_BEID_INSTANTIATOR = "be.ehealth.technicalconnector.beid.instantiator";
   private static ConfigurableFactoryHelper<BeIDInstantiator> helper = new ConfigurableFactoryHelper("be.ehealth.technicalconnector.beid.instantiator", CommonsEidInstantiator.class.getName());
   private static BeIDInstantiator instantiator;

   public BeIDFactory() {
   }

   private static BeIDInstantiator getInstantiator() throws TechnicalConnectorException {
      if (instantiator == null) {
         instantiator = (BeIDInstantiator)helper.getImplementation();
      }

      return instantiator;
   }

   public static void setInstantiator(BeIDInstantiator instantiator) {
      BeIDFactory.instantiator = instantiator;
   }

   public static BeIDInfo getBeIDInfo(String scope, boolean useCache) throws TechnicalConnectorException {
      return getInstantiator().instantiateBeIDInfo(scope, useCache);
   }

   public static KeyStore getKeyStore(String scope, boolean useCache) throws TechnicalConnectorException {
      return getInstantiator().instantiateKeyStore(scope, useCache);
   }
}
