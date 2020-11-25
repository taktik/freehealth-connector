package be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class MemberDataServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(MemberDataServiceImpl.class) && additionalParameters.length == 0) {
         return new MemberDataServiceImpl(sessionValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
