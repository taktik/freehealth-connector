package be.ehealth.businessconnector.mediprimauma.session.impl;

import be.ehealth.business.common.validator.ValidatorFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class MediprimaUmaImplementationClassFactory extends ImplementationClassFactory {
   private static final String PROP_MEDIPRIMAUMA_EHEALTH_VALIDATOR = "be.ehealth.businessconnector.mediprimauma.ehealthreplyvalidator";

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(MediprimaUmaServiceImpl.class) && additionalParameters.length == 0) {
         return new MediprimaUmaServiceImpl(sessionValidator, ValidatorFactory.getEhealthReplyValidator("be.ehealth.businessconnector.mediprimauma.ehealthreplyvalidator"));
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
