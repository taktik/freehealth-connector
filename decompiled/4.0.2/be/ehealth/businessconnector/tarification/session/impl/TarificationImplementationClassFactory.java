package be.ehealth.businessconnector.tarification.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class TarificationImplementationClassFactory extends ImplementationClassFactory {
   public TarificationImplementationClassFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(TarificationSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new TarificationSessionServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
