package be.ehealth.businessconnector.chapterIV.session.impl;

import be.ehealth.businessconnector.chapterIV.exception.ChapterIVBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class ChapterIVServiceImplementationFactory extends ImplementationClassFactory {
   public ChapterIVServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(ChapterIVServiceImpl.class) && additionalParameters.length == 0) {
         return new ChapterIVServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
