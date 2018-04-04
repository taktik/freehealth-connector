package org.taktik.connector.technical.service.sts;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;

public final class STSServiceFactory {
   private static final String PROP_STSSERVICE_CLASS = "service.sts.class";
   private static final String DEFAULT_STSSERVICE_CLASS = "org.taktik.connector.technical.service.sts.impl.STSServiceImpl";
   private static ConfigurableFactoryHelper<STSService> factoryHelper = new ConfigurableFactoryHelper("service.sts.class", "org.taktik.connector.technical.service.sts.impl.STSServiceImpl");

   public static STSService getInstance() throws TechnicalConnectorException {
      return (STSService)factoryHelper.getImplementation();
   }
}
