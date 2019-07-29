package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CertificateCheckerFactory {
   private static final Logger LOG = LoggerFactory.getLogger(CertificateCheckerFactory.class);
   public static final String PROPS_CERT_CHECKER_CLASS = "crypto.certificatechecker.classname";
   private static final String DEFAULT_CERT_CHECKER_CLASS = "be.ehealth.technicalconnector.service.etee.impl.ConnectorCertificateChecker";
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<CertificateChecker> helper = new ConfigurableFactoryHelper("crypto.certificatechecker.classname", "be.ehealth.technicalconnector.service.etee.impl.ConnectorCertificateChecker");

   public static CertificateChecker getCertificateChecker() throws TechnicalConnectorException {
      return (CertificateChecker)helper.getImplementation();
   }
}
