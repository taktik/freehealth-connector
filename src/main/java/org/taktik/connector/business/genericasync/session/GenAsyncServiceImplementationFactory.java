package org.taktik.connector.business.genericasync.session;

import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.session.impl.GenAsyncServiceImpl;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.session.ImplementationClassFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public class GenAsyncServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(GenAsyncServiceImpl.class) && additionalParameters.length == 1) {
         return (T) new GenAsyncServiceImpl(sessionValidator, additionalParameters[0]);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
