package be.ehealth.businessconnector.consultrn.session.impl;

import be.ehealth.business.common.validator.ValidatorFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class ConsultrnImplementationClassFactory extends ImplementationClassFactory {
   private static final String PROP_CONSULTRN_VALIDATOR = "be.ehealth.businessconnector.consultrn.v3.validator";

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws TechnicalConnectorException {
      if (clazz.equals(ConsultrnSessionServiceImpl.class) && additionalParameters.length == 0) {
         return new ConsultrnSessionServiceImpl(sessionValidator, ValidatorFactory.getEhealthReplyValidator("be.ehealth.businessconnector.consultrn.v3.validator"));
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
