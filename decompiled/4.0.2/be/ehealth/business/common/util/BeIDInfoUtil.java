package be.ehealth.business.common.util;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public class BeIDInfoUtil {
   private static final String PROP_USE_CACHE = "be.ehealth.technicalconnector.beid.beidinfo.cache";

   public BeIDInfoUtil() {
   }

   public static BeIDInfo getBeIDInfo(String scope) throws TechnicalConnectorException {
      boolean useCache = ConfigFactory.getConfigValidator().getBooleanProperty("be.ehealth.technicalconnector.beid.beidinfo.cache", Boolean.FALSE);
      return getBeIDInfo(scope, useCache);
   }

   public static BeIDInfo getBeIDInfo(String scope, boolean useCache) throws TechnicalConnectorException {
      return BeIDFactory.getBeIDInfo(scope, useCache);
   }
}
