package be.ehealth.businessconnector.civicsv2.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class CivicsImplementationClassFactory extends ImplementationClassFactory {
   public CivicsImplementationClassFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, Crypto hokCrypto, Crypto encryptionCrypto, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(CivicsSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new CivicsSessionServiceImpl(sessionValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
