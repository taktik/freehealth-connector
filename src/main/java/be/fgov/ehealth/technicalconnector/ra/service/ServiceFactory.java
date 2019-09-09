package be.fgov.ehealth.technicalconnector.ra.service;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.ra.service.impl.AuthenticationCertificateRegistrationServiceImpl;
import be.fgov.ehealth.technicalconnector.ra.service.impl.EncryptionTokenRegistrationServiceImpl;

public final class ServiceFactory {
   private static final String PROP_CERTRA_PROVIDER = "be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class";
   private static final String PROP_ETKRA_PROVIDER = "be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class";

   public static AuthenticationCertificateRegistrationService getAuthenticationCertificateRegistrationService() throws TechnicalConnectorException {
      return (AuthenticationCertificateRegistrationService)(new ConfigurableFactoryHelper("be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class", AuthenticationCertificateRegistrationServiceImpl.class.getName())).getImplementation();
   }

   public static EncryptionTokenRegistrationService getEncryptionTokenRegistrationService() throws TechnicalConnectorException {
      return (EncryptionTokenRegistrationService)(new ConfigurableFactoryHelper("be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class", EncryptionTokenRegistrationServiceImpl.class.getName())).getImplementation();
   }
}
