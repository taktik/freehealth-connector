package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.impl.ConnectorCertificateChecker;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;

public final class CertificateCheckerFactory {
   public static final String PROPS_CERT_CHECKER_CLASS = "crypto.certificatechecker.classname";
   private Configuration config = ConfigFactory.getConfigValidator();
   private static ConfigurableFactoryHelper<CertificateChecker> helper = new ConfigurableFactoryHelper("crypto.certificatechecker.classname", ConnectorCertificateChecker.class.getName());

   protected CertificateCheckerFactory() {
   }

   public static CertificateChecker getCertificateChecker() throws TechnicalConnectorException {
      return (CertificateChecker)helper.getImplementation();
   }
}
