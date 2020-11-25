package be.ehealth.businessconnector.ehbox.v3.session.impl;

import be.ehealth.businessconnector.ehbox.v3.validator.ValidatorFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class EhboxServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(EhealthBoxServiceV3Impl.class) && additionalParameters.length == 0) {
         return new EhealthBoxServiceV3Impl(sessionValidator, ValidatorFactory.getValidator());
      } else if (clazz.equals(Eh2eBoxServiceV3Impl.class) && additionalParameters.length == 0) {
         return new Eh2eBoxServiceV3Impl(sessionValidator, ValidatorFactory.getValidator());
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
