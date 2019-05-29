package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RevocationStatusCheckerFactory {
   private static final Logger LOG = LoggerFactory.getLogger(RevocationStatusCheckerFactory.class);
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
