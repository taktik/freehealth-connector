package be.ehealth.businessconnector.mediprima.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class MediprimaImplementationClassFactory extends ImplementationClassFactory {
   public MediprimaImplementationClassFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, Crypto hokCrypto, Crypto encryptionCrypto, String... additionalParameters) throws TechnicalConnectorException, ConnectorException {
      if (clazz.equals(MediprimaConsultationSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new MediprimaConsultationSessionServiceImpl(sessionValidator, replyValidator);
      } else if (clazz.equals(MediprimaTarificationSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new MediprimaTarificationSessionServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported or the number of additional parameters(" + additionalParameters.length + ") are added ");
      }
   }
}
