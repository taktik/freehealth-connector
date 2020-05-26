package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class RevocationStatusCheckerFactory {
   public static final String PROPS_STATUS_CHECKER_CLASS = "crypto.revocationstatuschecker.classname";
   private static final String DEFAULT_STATUS_CHECKER_CLASS = "be.ehealth.technicalconnector.service.etee.impl.ConnectorRevocationStatusChecker";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<RevocationStatusChecker> helper = new ConfigurableFactoryHelper("crypto.revocationstatuschecker.classname", "be.ehealth.technicalconnector.service.etee.impl.ConnectorRevocationStatusChecker");

   private RevocationStatusCheckerFactory() {
   }

   public static RevocationStatusChecker getStatusChecker() throws TechnicalConnectorException {
      return (RevocationStatusChecker)helper.getImplementation();
   }
}
