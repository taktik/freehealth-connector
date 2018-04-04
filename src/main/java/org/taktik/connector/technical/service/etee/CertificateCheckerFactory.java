package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CertificateCheckerFactory {
   private static final Logger LOG = LoggerFactory.getLogger(CertificateCheckerFactory.class);
   public static final String PROPS_CERT_CHECKER_CLASS = "crypto.certificatechecker.classname";
   private static final String DEFAULT_CERT_CHECKER_CLASS = "org.taktik.connector.technical.service.etee.impl.ConnectorCertificateChecker";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<CertificateChecker> helper = new ConfigurableFactoryHelper("crypto.certificatechecker.classname", "org.taktik.connector.technical.service.etee.impl.ConnectorCertificateChecker");

   public static CertificateChecker getCertificateChecker() throws TechnicalConnectorException {
      return (CertificateChecker)helper.getImplementation();
   }
}
