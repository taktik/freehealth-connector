package be.ehealth.businessconnector.consultrnv2.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class ConsultrnImplementationClassFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws ConnectorException {
      if (additionalParameters.length == 0) {
         if (clazz.equals(ConsultrnPersonServiceImpl.class)) {
            return new ConsultrnPersonServiceImpl(sessionValidator, replyValidator);
         }

         if (clazz.equals(ConsultrnCBSSPersonServiceImpl.class)) {
            return new ConsultrnCBSSPersonServiceImpl(sessionValidator, replyValidator);
         }
      }

      throw new UnsupportedOperationException("class " + clazz + " not supported or the number of additional parameters(" + additionalParameters.length + ") are added ");
   }
}
