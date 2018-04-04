package be.ehealth.technicalconnector.service.sts;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class STSServiceFactory {
   private static final String PROP_STSSERVICE_CLASS = "service.sts.class";
   private static final String DEFAULT_STSSERVICE_CLASS = "be.ehealth.technicalconnector.service.sts.impl.STSServiceImpl";
   private static ConfigurableFactoryHelper<STSService> factoryHelper = new ConfigurableFactoryHelper("service.sts.class", "be.ehealth.technicalconnector.service.sts.impl.STSServiceImpl");

   public static STSService getInstance() throws TechnicalConnectorException {
      return (STSService)factoryHelper.getImplementation();
   }
}
