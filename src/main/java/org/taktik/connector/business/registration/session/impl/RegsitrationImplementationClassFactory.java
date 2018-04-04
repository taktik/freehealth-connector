package org.taktik.connector.business.registration.session.impl;

import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.session.ImplementationClassFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public class RegsitrationImplementationClassFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(RegistrationSessionImpl.class) && additionalParameters.length == 0) {
         return (T) new RegistrationSessionImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
