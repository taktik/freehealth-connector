package be.ehealth.businessconnector.genericasync.session;

import be.ehealth.businessconnector.genericasync.exception.GenAsyncBusinessConnectorException;
import be.ehealth.businessconnector.genericasync.session.impl.GenAsyncServiceImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class GenAsyncServiceImplementationFactory extends ImplementationClassFactory {
   public GenAsyncServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(GenAsyncServiceImpl.class) && additionalParameters.length == 1) {
         return new GenAsyncServiceImpl(sessionValidator, additionalParameters[0]);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
