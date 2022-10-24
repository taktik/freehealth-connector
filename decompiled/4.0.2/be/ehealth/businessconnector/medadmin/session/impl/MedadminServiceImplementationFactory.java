package be.ehealth.businessconnector.medadmin.session.impl;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.ImplementationClassFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;

public class MedadminServiceImplementationFactory extends ImplementationClassFactory {
   public MedadminServiceImplementationFactory() {
   }

   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws ConnectorException {
      if (clazz.equals(MedadminServiceImpl.class) && additionalParameters.length == 0) {
         return new MedadminServiceImpl();
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
