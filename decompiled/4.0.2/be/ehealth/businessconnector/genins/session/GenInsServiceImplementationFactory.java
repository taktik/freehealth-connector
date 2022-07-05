package be.ehealth.businessconnector.genins.session;

import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.session.impl.GenInsServiceImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class GenInsServiceImplementationFactory extends ImplementationClassFactory {
   public GenInsServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws GenInsBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(GenInsServiceImpl.class) && additionalParameters.length == 0) {
         return new GenInsServiceImpl();
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
