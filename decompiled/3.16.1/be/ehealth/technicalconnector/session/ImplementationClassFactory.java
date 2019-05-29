package be.ehealth.technicalconnector.session;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

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
