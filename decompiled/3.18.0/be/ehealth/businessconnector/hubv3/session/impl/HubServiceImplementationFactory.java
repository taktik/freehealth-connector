package be.ehealth.businessconnector.hubv3.session.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class HubServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(HubServiceImpl.class) && additionalParameters.length == 0) {
         return new HubServiceImpl(sessionValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
