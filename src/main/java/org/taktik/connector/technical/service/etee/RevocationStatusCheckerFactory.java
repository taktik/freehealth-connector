package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RevocationStatusCheckerFactory {
   private static final Logger LOG = LoggerFactory.getLogger(RevocationStatusCheckerFactory.class);
   public static final String PROPS_STATUS_CHECKER_CLASS = "crypto.revocationstatuschecker.classname";
   private static final String DEFAULT_STATUS_CHECKER_CLASS = "org.taktik.connector.technical.service.etee.impl.ConnectorRevocationStatusChecker";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<RevocationStatusChecker> helper = new ConfigurableFactoryHelper("crypto.revocationstatuschecker.classname", "org.taktik.connector.technical.service.etee.impl.ConnectorRevocationStatusChecker");

   public static RevocationStatusChecker getStatusChecker() throws TechnicalConnectorException {
      return (RevocationStatusChecker)helper.getImplementation();
   }
}
