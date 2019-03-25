package be.ehealth.businessconnector.registration.helper;

import be.cin.nip.sync.reg.v1.RegistrationsAnswer;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseHelper implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String REGISTRATION_RESPONSE = "/mycarenet-registration/XSD/registrations-v1.0.xsd";
   private static final Logger LOG = LoggerFactory.getLogger(ResponseHelper.class);

   public ResponseHelper() {
      LOG.debug("creating ResponseHelper for ModuleBootstrapHook.");
   }

   public static void validateResponse(RegistrationsAnswer responseContent) throws TechnicalConnectorException {
      ValidatorHelper.validate((Object)responseContent, (String)"/mycarenet-registration/XSD/registrations-v1.0.xsd");
   }

   public static RegistrationsAnswer toObject(byte[] responseContent) {
      MarshallerHelper<Object, Object> responseMarshaller = new MarshallerHelper(RegistrationsAnswer.class, RegistrationsAnswer.class);
      return (RegistrationsAnswer)responseMarshaller.toObject(responseContent);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(RegistrationsAnswer.class);
   }
}
