package be.fgov.ehealth.technicalconnector.ra.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.fgov.ehealth.technicalconnector.ra.service.impl.AuthenticationCertificateRegistrationServiceImpl;
import be.fgov.ehealth.technicalconnector.ra.service.impl.EncryptionTokenRegistrationServiceImpl;
import java.util.HashMap;
import java.util.Map;

public final class ServiceFactory {
   private static final String PROP_CERTRA_PROVIDER = "be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class";
   private static final String PROP_ETKRA_PROVIDER = "be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class";

   public static AuthenticationCertificateRegistrationService getAuthenticationCertificateRegistrationService() throws TechnicalConnectorException {
      return (AuthenticationCertificateRegistrationService)(new ConfigurableFactoryHelper("be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class", AuthenticationCertificateRegistrationServiceImpl.class.getName())).getImplementation();
   }

   public static EncryptionTokenRegistrationService getEncryptionTokenRegistrationService(Credential cred) throws TechnicalConnectorException {
      Map<String, Object> options = new HashMap();
      options.put("credential", cred);
      return (EncryptionTokenRegistrationService)(new ConfigurableFactoryHelper("be.fgov.ehealth.technicalconnector.ra.service.authenticationcertificateregistrationservice.class", EncryptionTokenRegistrationServiceImpl.class.getName())).getImplementation(options);
   }
}
