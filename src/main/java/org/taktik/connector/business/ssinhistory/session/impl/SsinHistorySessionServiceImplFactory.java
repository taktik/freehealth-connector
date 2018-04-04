package org.taktik.connector.business.ssinhistory.session.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.session.ImplementationClassFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public class SsinHistorySessionServiceImplFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(SsinHistorySessionServiceImpl.class) && additionalParameters.length == 0) {
         return (T) new SsinHistorySessionServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported or the number of additionalParameters was not correct ");
      }
   }
}
