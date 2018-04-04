package org.taktik.connector.technical.session;

import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public abstract class ImplementationClassFactory {
   /** @deprecated */
   @Deprecated
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, Crypto hokCrypto, Crypto encryptionCrypto, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      throw new UnsupportedOperationException("Do not use this deprecated method : createImplementationClass from the class ImplementationClassFactory");
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      return this.createImplementationClass(clazz, sessionValidator, replyValidator, SessionUtil.getHolderOfKeyCrypto(), SessionUtil.getEncryptionCrypto(), additionalParameters);
   }
}
