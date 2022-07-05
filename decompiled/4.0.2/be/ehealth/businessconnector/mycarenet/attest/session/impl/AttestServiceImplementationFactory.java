package be.ehealth.businessconnector.mycarenet.attest.session.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class AttestServiceImplementationFactory extends ImplementationClassFactory {
   public AttestServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(AttestServiceImpl.class) && additionalParameters.length == 0) {
         return new AttestServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
