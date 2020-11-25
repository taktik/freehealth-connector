package be.ehealth.technicalconnector.generic.session.impl;

import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class GenericServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) {
      if (clazz.equals(GenericServiceImpl.class) && additionalParameters.length == 0) {
         return new GenericServiceImpl(sessionValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
